package bank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ui {
    
    // FETCH ASSIST MODULES
    private final Misc misc = new Misc();
    private static Scanner scan = new Scanner(System.in);
    
    // DECLARE THE BACKEND MODULE
    private Backend backend;
    
    // CONSTRUCTOR -- SET THE BACKEND MODULE
    public Ui (Backend _backend) { this.backend = _backend; }
    
    // MENU RELATED QUESTION
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
    
    // MAIN MENU
    public void main_menu() {
        
        // MENU HEADER
        misc.log("MAIN MENU");
        
        // MENU OPTIONS
        Map<Integer, String> options = new HashMap<>();
            options.put(1, "View All Accounts");
            options.put(2, "Add User");
            options.put(3, "User Actions");
            options.put(9, "Terminate Program");
        
        // CREATE A ROW FOR EACH OPTION
        for (Integer key : options.keySet()) {
            misc.log("\t" + key + ". " + options.get(key));
        }
        
        // ASK WHAT TO DO NEXT
        String response = question("WHAT WOULD YOU LIKE TO DO?", "int");

        // CONVERT TO INTEGER & TRIGGER NEXT STEP
        switch (Integer.parseInt(response)) {
            case 1:
                backend.view_all();
                main_menu();
                break;
                
            case 2:
                String name = question("ENTER A USERNAME:", "str");
                backend.add_user(name);
                main_menu();
                break;
                
            case 3:
                user_selection();
                break;
                
            case 9:
                kill_app();
                break;
                
            default:
                misc.error("OUT OF BOUNDS, TRY AGAIN!");
                main_menu();
                break;
        }
    }
    
    // USER MENU
    private void user_selection() {
        
        // ASK FOR AN ACCOUNT NUMBER & CONVERT IT TO AN INTEGER
        String response = question("ENTER VALID ACCOUNT NUMBER:", "int");
        Integer converted = Integer.parseInt(response);
        
        // CHECK IF IT EXISTS
        boolean check = backend.exists(converted);
        
        // IF IT DOESNT -- ASK AGAIN
        while (check == false) { user_selection(); }

        // ADD LINEBREAK
        misc.log("");
        
        // OPEN USER ACTIONS MENU
        user_action(converted);
    }
    
    // USER ACTIONS MENU
    private void user_action(Integer user) {
        
        // MENU HEADER
        misc.log("USER ACTIONS (" + user + ")");
        
        // MENU OPTIONS
        Map<Integer, String> options = new HashMap<>();
            options.put(1, "Inspect Stats");
            options.put(2, "Withdraw Funds");
            options.put(3, "Deposit Funds");
            options.put(4, "Change Rate");
            options.put(8, "Back to Main Menu");
            options.put(9, "Terminate Program");
        
        // CREATE A ROW FOR EACH OPTION
        for (Integer key : options.keySet()) {
            misc.log("\t" + key + ". " + options.get(key));
        }
        
        // FETCH REQUESTED ACCOUNT
        Account target = backend.fetch_user(user);
        
        // ASK WHAT TO DO NEXT
        String response = question("WHAT WOULD YOU LIKE TO DO?", "int");
        
        switch (Integer.parseInt(response)) {
            case 1:
                target.inspect();
                user_action(user);
                break;
                
            case 2:
                response = question("HOW MUCH?", "int, dbl");
                target.withdraw(response);
                user_action(user);
                break;
                
            case 3:
                response = question("HOW MUCH?", "int, dbl");
                target.deposit(response);
                user_action(user);
                break;
                
            case 4:
                response = question("TO WHAT? (MORE THAN ZERO)", "int, dbl");
                target.change_rate(response);
                user_action(user);
                break;
                
            case 8:
                main_menu();
                break;
                
            case 9:
                kill_app();
                break;
                
            default:
                misc.error("// OUT OF BOUNDS, TRY AGAIN!");
                main_menu();
                break;
        }
    }
    
    // KILL APPLICATION
    private void kill_app() {
        misc.error("APPLICATION KILLED");
        System.exit(0);
    }
}