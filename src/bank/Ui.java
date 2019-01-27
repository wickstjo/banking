package bank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ui {
    
    // FETCH ASSIST MODULES
    private final Misc misc = new Misc();
    private final Scanner scan = new Scanner(System.in);
    
    // DECLARE ASSIST MODULES
    private final Backend backend;
    private final Timer timer;
    
    // CONSTRUCTOR
    public Ui(Backend _backend) {
        
        // SET THE BACKEND MODULE & START THE TIMER
        this.backend = _backend;
        
        // ATTACH THE TIMER OBJECT & START IT
        this.timer = new Timer(this.backend);
        start_timer();
    } 
    
    // START/STOP TIMER
    private void start_timer() { this.timer.start(); }
    private void stop_timer() { this.timer.interrupt(); }
    
    // MAIN MENU
    public void main_menu() {
        
        // LOG HEADER
        misc.log("MAIN MENU");
        
        // MENU OPTIONS
        Map<Integer, String> options = new HashMap<>();
            options.put(1, "VIEW REGISTERED ACCOUNTS");
            options.put(2, "ADD ACCOUNT");
            options.put(3, "SELECT ACCOUNT");
            options.put(9, "TERMINATE PROGRAM");
        
        // CREATE A ROW FOR EACH OPTION
        for (Integer key : options.keySet()) {
            misc.log("\t" + key + ". " + options.get(key));
        }
        
        // ASK WHAT TO DO NEXT
        String response = question("WHAT WOULD YOU LIKE TO DO?", "int");

        // CONVERT TO INTEGER & TRIGGER NEXT STEP
        switch (Integer.parseInt(response)) {
            case 1:
                view_accounts();
                main_menu();
            break;
                
            case 2:
                add_account();
                main_menu();
            break;
                
            case 3: select_account(); break;
            case 9: kill_app(); break;
                
            default:
                misc.error("OUT OF BOUNDS, TRY AGAIN!");
                main_menu();
            break;
        }
    }
        
    // QUESTION BLOCK
    private String question(String _question, String _whitelist) {
        
        // ASK THE QUESTION & SAVE THE ANSWER
        System.out.print("\n" + _question + "\n\t> ");
        String answer = scan.next();
        
        // CONSTRUCT THE WHITELIST & CHECK THE ANSWER TYPE
        ArrayList<String> whitelist = misc.create_whitelist(_whitelist);
        String check = misc.check_type(answer);
        
        // IF THE TYPE ISNT WHITELISTED
        if (whitelist.contains(check) == false) {
            
            // PROMPT ERROR & ASK AGAIN
            misc.error("INPUT ERROR!");
            answer = question(_question, _whitelist);
        }
        
        return answer;
    }
    
    // KILL APPLICATION
    private void kill_app() {
        misc.error("APPLICATION KILLED");
        stop_timer();
        System.exit(0);
    }
    
    // -------- MAIN MENU OPTIONS
    
    private void view_accounts() {
        
        // HEADER
        misc.log("\nALL REGISTERED ACCOUNTS");
        
        // FETCH ALL REGISTERED ACCOUNT NUMBERS
        ArrayList<Integer> accounts = backend.get_accounts();
        
        if (!accounts.isEmpty()) {
        
            // LOOP THROUGH & LOG ALL THE NUMBERS
            for (Integer account_number : accounts) {
                
                String name = backend.get_checking(account_number).get_owner();
                
                misc.log("\t" + account_number + "\t\t" + name);
            }
        
        } else { misc.log("\tNONE YET!"); }
        
        // ADD AESTHETIC LINEBREAK
        misc.log("");
    }
    
    private void add_account() {
        
        // CHECK IF THERES ACCOUNT NUMBERS LEFT
        boolean space = backend.accounts_left();
        
        // IF THERE IS, ADD NEW USER
        if (space == true) {

            // ASK THE QUESTION & ADD THE USER -- FORCE UPPERCASE
            String name = question("ENTER A USERNAME:", "str").toUpperCase();
            int account_number = backend.add_user(name);
            
            // LOG SUCCESS MESSAGE
            misc.success("ADDED '" + name + "' (" + account_number + ") TO THE HASHMAP!");
        
        // IF NOT, LOG ERROR
        } else { misc.error("HASHMAPS ARE FULL!"); }
    }
    
    private void select_account() {
        
        // ASK FOR AN ACCOUNT NUMBER & CONVERT IT TO AN INTEGER
        String response = question("ENTER VALID ACCOUNT NUMBER:", "int");
        Integer converted = Integer.parseInt(response);
        
        // CHECK IF IT EXISTS
        boolean check = backend.exists(converted);
        
        // IF IT DOESNT
        while (check == false) {
            
            // LOG ERROR & ASK AGAIN
            misc.error("ACCOUNT NOT FOUND, TRY AGAIN!");
            select_account();
        }

        // LOG SUCCESS MESSAGE & OPEN THE NEXT MENU
        misc.success("ACCOUNT FOUND!");
        account_menu(converted);
    }
    
    private void account_menu(Integer user) {
        
        // HEADER
        misc.log("ACCOUNT MENU (" + user + ")");
        
        // FETCH ACCOUNTS CHECKINGS/SAVINGS INSTANCES
        Checking checkings = backend.get_checking(user);
        Saving savings = backend.get_saving(user);
        Credit credits = backend.get_credit(user);
        
        // MENU OPTIONS
        Map<Integer, String> options = new HashMap<>();
            options.put(1, "ACCOUNT OVERVIEW");
            options.put(2, "CHECKINGS ACCOUNT");
            options.put(3, "SAVINGS ACCOUNT");
            options.put(4, "CREDIT ACCOUNT");
            options.put(8, "GO BACK");
            options.put(9, "TERMINATE PROGRAM");
        
        // CREATE A ROW FOR EACH OPTION
        for (Integer key : options.keySet()) {
            misc.log("\t" + key + ". " + options.get(key));
        }
        
        // ASK WHAT TO DO NEXT
        String response = question("WHAT WOULD YOU LIKE TO DO?", "int");
        
        switch (Integer.parseInt(response)) {
            case 1:
                account_overview(checkings, savings, credits);
                account_menu(user);
            break;
                
            case 2: misc.log(""); checking_menu(checkings, user); break;
            case 3: misc.log(""); savings_menu(savings, user); break;
            case 4: misc.log(""); credit_menu(credits, user); break;
            case 8: misc.log(""); main_menu(); break;
            case 9: kill_app(); break;
                
            default:
                misc.error("OUT OF BOUNDS, TRY AGAIN!");
                account_menu(user);
            break;
        }
    }
    
    // -------- USER MENU OPTIONS
    
    private void account_overview(Checking checking, Saving saving, Credit credit) {
        
        // LOG OUT RELEVANT VALUES -- ROUND NUMBERS TO TWO DECIMALS
        misc.log("\nACCOUNT OWNER:\t\t\t" + checking.get_owner());
        misc.log("----");
        checking.overview();
        misc.log("----");
        saving.overview();
        misc.log("----");
        credit.overview();
        misc.log("");
    }
    
    private void checking_menu(Checking checkings, Integer user) {
        
        // HEADER
        misc.log("CHECKINGS MENU");
        
        // MENU OPTIONS
        Map<Integer, String> options = new HashMap<>();
            options.put(1, "OVERVIEW");
            options.put(2, "WITHDRAW FUNDS");
            options.put(3, "DEPOSIT FUNDS");
            options.put(8, "GO BACK");
            options.put(9, "TERMINATE PROGRAM");
        
        // CREATE A ROW FOR EACH OPTION
        for (Integer key : options.keySet()) {
            misc.log("\t" + key + ". " + options.get(key));
        }
        
        // ASK WHAT TO DO NEXT
        String response = question("WHAT WOULD YOU LIKE TO DO?", "int");
        
        switch (Integer.parseInt(response)) {
            case 1:
                checking_overview(checkings);
                checking_menu(checkings, user);
            break;
            case 2:
                checking_withdraw(checkings);
                checking_menu(checkings, user);
            break;
            
            case 3: 
                checking_deposit(checkings);
                checking_menu(checkings, user);
            break;
            
            case 8: misc.log(""); account_menu(user); break;
            case 9: kill_app(); break;
                
            default:
                misc.error("OUT OF BOUNDS, TRY AGAIN!");
                checking_menu(checkings, user);
            break;
        }
    }
    
    private void savings_menu(Saving savings, Integer user) {
    
                // HEADER
        misc.log("SAVINGS MENU");
        
        // MENU OPTIONS
        Map<Integer, String> options = new HashMap<>();
            options.put(1, "OVERVIEW");
            options.put(2, "WITHDRAW FUNDS");
            options.put(3, "DEPOSIT FUNDS");
            options.put(8, "GO BACK");
            options.put(9, "TERMINATE PROGRAM");
        
        // CREATE A ROW FOR EACH OPTION
        for (Integer key : options.keySet()) {
            misc.log("\t" + key + ". " + options.get(key));
        }
        
        // ASK WHAT TO DO NEXT
        String response = question("WHAT WOULD YOU LIKE TO DO?", "int");
        
        switch (Integer.parseInt(response)) {
            case 1:
                savings_overview(savings);
                savings_menu(savings, user);
            break;
            case 2:
                savings_withdraw(savings);
                savings_menu(savings, user);
            break;
            
            case 3: 
                savings_deposit(savings);
                savings_menu(savings, user);
            break;
            
            case 8: misc.log(""); account_menu(user); break;
            case 9: kill_app(); break;
                
            default:
                misc.error("OUT OF BOUNDS, TRY AGAIN!");
                savings_menu(savings, user);
            break;
        }
    }

    private void credit_menu(Credit credits, Integer user) {
    
                // HEADER
        misc.log("CREDITS MENU");
        
        // MENU OPTIONS
        Map<Integer, String> options = new HashMap<>();
            options.put(1, "OVERVIEW");
            options.put(2, "WITHDRAW FUNDS");
            options.put(3, "DEPOSIT FUNDS");
            options.put(8, "GO BACK");
            options.put(9, "TERMINATE PROGRAM");
        
        // CREATE A ROW FOR EACH OPTION
        for (Integer key : options.keySet()) {
            misc.log("\t" + key + ". " + options.get(key));
        }
        
        // ASK WHAT TO DO NEXT
        String response = question("WHAT WOULD YOU LIKE TO DO?", "int");
        
        switch (Integer.parseInt(response)) {
            case 1:
                credit_overview(credits);
                credit_menu(credits, user);
            break;
            case 2:
                credit_withdraw(credits);
                credit_menu(credits, user);
            break;
            
            case 3: 
                credit_deposit(credits);
                credit_menu(credits, user);
            break;
            
            case 8: misc.log(""); account_menu(user); break;
            case 9: kill_app(); break;
                
            default:
                misc.error("OUT OF BOUNDS, TRY AGAIN!");
                credit_menu(credits, user);
            break;
        }
    }
    
    // -------- CHECKING OPTIONS
    
    private void checking_overview(Checking checking) {
        misc.log("");
        checking.overview();
        misc.log("");
    }
    
    private void checking_withdraw(Checking checking) {
        
        // ASK HOW MUCH & WITHDRAW
        String amount = question("WITHDRAW HOW MUCH:", "int, dbl");
        
        // CONVERT IT TO A DOUBLE
        double converted = misc.to_dbl(amount);
        
        // CHECK IF AMOUNT IS WITHDRAWABLE
        boolean check = checking.withdrawable(converted);
        
        // IF IT IS
        if (check == true) {
            
            // WITHDRAW & LOG SUCCESS
            checking.withdraw(converted);
            misc.success("YOU WITHDREW: " + amount);
        
        // IF NOT, LOG ERROR
        } else { misc.error("NOT ENOUGH FUNDS!"); }
    }
    
    private void checking_deposit(Checking checking) {
        
        // ASK HOW MUCH & DEPOSIT
        String amount = question("DEPOSIT HOW MUCH:", "int, dbl");
        
        // CONVERT IT TO A DOUBLE
        double converted = misc.to_dbl(amount);
            
        // WITHDRAW & LOG SUCCESS
        checking.deposit(converted);
        misc.success("YOU DEPOSITED: " + amount);
    }
    
    // -------- SAVING OPTIONS
    
    private void savings_overview(Saving saving) {
        misc.log("");
        saving.overview();
        misc.log("");
    }
    
    private void savings_withdraw(Saving saving) {
        
        // ASK HOW MUCH & WITHDRAW
        String amount = question("WITHDRAW HOW MUCH:", "int, dbl");
        
        // CONVERT IT TO A DOUBLE
        double converted = misc.to_dbl(amount);
        
        // CHECK IF AMOUNT IS WITHDRAWABLE
        boolean check = saving.withdrawable(converted);
        
        // IF IT IS
        if (check == true) {
            
            // WITHDRAW & LOG SUCCESS
            saving.withdraw(converted);
            misc.success("YOU WITHDREW: " + amount);
        
        // IF NOT, LOG ERROR
        } else { misc.error("NOT ENOUGH FUNDS!"); }
    }
    
    private void savings_deposit(Saving saving) {
        
        // ASK HOW MUCH & DEPOSIT
        String amount = question("DEPOSIT HOW MUCH:", "int, dbl");
        
        // CONVERT IT TO A DOUBLE
        double converted = misc.to_dbl(amount);
            
        // WITHDRAW & LOG SUCCESS
        saving.deposit(converted);
        misc.success("YOU DEPOSITED: " + amount);
    }
    
        // -------- CREDIT OPTIONS
    
    private void credit_overview(Credit credit) {
        misc.log("");
        credit.overview();
        misc.log("");
    }
    
    private void credit_withdraw(Credit credit) {
        
        // ASK HOW MUCH & WITHDRAW
        String amount = question("WITHDRAW HOW MUCH:", "int, dbl");
        
        // CONVERT IT TO A DOUBLE
        double converted = misc.to_dbl(amount);
        
        // CHECK IF AMOUNT IS WITHDRAWABLE
        boolean check = credit.withdrawable(converted);
        
        // IF IT IS
        if (check == true) {
            
            // WITHDRAW & LOG SUCCESS
            credit.withdraw(converted);
            misc.success("YOU WITHDREW: " + amount);
        
        // IF NOT, LOG ERROR
        } else { misc.error("ERROR! WITHDRAW LIMIT: " + credit.get_remaining()); }
    }
    
    private void credit_deposit(Credit credit) {
        
        // ASK HOW MUCH & DEPOSIT
        String amount = question("DEPOSIT HOW MUCH:", "int, dbl");
        
        // CONVERT IT TO A DOUBLE
        double converted = misc.to_dbl(amount);
            
        // CHECK IF AMOUNT IS DEPOSITABLE
        boolean check = credit.depositable(converted);
        
        // IF IT IS
        if (check == true) {

            // WITHDRAW & LOG SUCCESS
            credit.deposit(converted);
            misc.success("YOU DEPOSITED: " + amount);
            
        // IF NOT, LOG ERROR
        } else { misc.error("ERROR! DEPOSIT LIMIT: " + credit.tab()); }
    }
}