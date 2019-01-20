package bank;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
            options.put(1, "View all Accounts");
            options.put(2, "User Actions");
            options.put(3, "Application Actions");
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
                select_user_menu();
                break;
                
            case 3:
                actions_menu();
                break;
                
            case 9:
                misc.error("\n// APPLICATION KILLED");
                System.exit(0);
                break;
                
            default:
                misc.error("\n// OUT OF BOUNDS, TRY AGAIN!");
                main_menu();
                break;
        }
    }
    
    // USER MENU
    public void select_user_menu() {
        
        // ASK FOR AN ACCOUNT NUMBER & CHECK IF IT EXISTS
        Integer response = question("ENTER A VALID ACCOUNT NUMBER:");
        boolean check = backend.exists(response);
        
        // WHILE IT DOESNT
        while (check == false) {
            
            // ASK FOR THE USER TO TRY AGAIN
            response = question("NOT FOUND, TRY AGAIN:");
            check = backend.exists(response);
        }

        // LOG SUCCESS
        misc.success("// ACCOUNT FOUND!");
    }
    
    public void actions_menu() {
        misc.success("// ACTIONS MENU");
    }
}