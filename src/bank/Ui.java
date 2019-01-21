package bank;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ui {
    
    // FETCH ASSIST MODULES
    Misc misc = new Misc();
    static Scanner scan = new Scanner(System.in);
    
    // DECLARE THE BACKEND MODULE
    Backend backend;
    
    // CONSTRUCTOR -- SET THE BACKEND MODULE
    public Ui (Backend _backend) { this.backend = _backend; }
    
    // QUESTION BLOCK
    public Integer question(String _question) {
        
        // ASK THE QUESTION & RETURN THE RESPONSE
        System.out.print("\n" + _question + "\n\t> ");
        return scan.nextInt();
    }
    
    // MENU HEADER
    public void header(String header) { misc.log("\n// " + header); }
    
    // MAIN MENU
    public void main_menu() {
        
        // MENU HEADER
        header("MAIN MENU");
        
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
        Integer response = question("WHAT WOULD YOU LIKE TO DO?");
        
        switch (response) {
            case 1:
                backend.view_all();
                main_menu();
                break;
                
            case 2:
                System.out.print("\nENTER A USERNAME:\n\t> ");
                String _name = scan.next();
                backend.add_user(_name);
                main_menu();
                break;
                
            case 3:
                user_selection();
                break;
                
            case 9:
                misc.error("// APPLICATION KILLED");
                System.exit(0);
                break;
                
            default:
                misc.error("// OUT OF BOUNDS, TRY AGAIN!");
                main_menu();
                break;
        }
    }
    
    // USER MENU
    public void user_selection() {
        
        // ASK FOR AN ACCOUNT NUMBER & CHECK IF IT EXISTS
        Integer response = question("ENTER ACCOUNT NUMBER:");
        boolean check = backend.exists(response);
        
        // WHILE IT DOESNT
        while (check == false) {
            
            // CHECK IF RESPONSE IS ZERO
            if (response != 0) {
            
                // ASK FOR THE USER TO TRY AGAIN
                response = question("NOT FOUND, TRY AGAIN OR '0' TO GO BACK:");
                check = backend.exists(response);
            
            // PROMPT MAIN MENU IF ZERO IS ENTERED
            } else { main_menu(); }
        }

        // OPEN USER ACTIONS MENU
        user_action(response);
    }
    
    // USER ACTIONS MENU
    public void user_action(Integer user) {
        
        // FETCH REQUESTED ACCOUNT
        Account target = backend.fetch_user(user);
        
        // MENU HEADER
        header("USER ACTIONS (" + user + ")");
        
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
        
        // ASK WHAT TO DO NEXT
        Integer response = question("WHAT WOULD YOU LIKE TO DO?");
        
        switch (response) {
            case 1:
                target.inspect();
                user_action(user);
                break;
                
            case 2:
                response = question("HOW MUCH?");
                target.withdraw(response);
                user_action(user);
                break;
                
            case 3:
                response = question("HOW MUCH?");
                target.deposit(response);
                user_action(user);
                break;
                
            case 4:
                response = question("TO WHAT? (MORE THAN ZERO)");
                target.change_rate(response);
                user_action(user);
                break;
                
            case 8:
                main_menu();
                break;
                
            case 9:
                misc.error("// APPLICATION KILLED");
                System.exit(0);
                break;
                
            default:
                misc.error("// OUT OF BOUNDS, TRY AGAIN!");
                main_menu();
                break;
        }
    }
}