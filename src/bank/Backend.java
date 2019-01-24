package bank;
import java.util.HashMap;
import java.util.Map;

public class Backend {
    
    // USERS MAP
    private Map<Integer, Account> users;
    
    // DEFAULT VALUES FOR NEW ACCOUNTS
    private double default_rate = 0.035;
    private double default_balance = 0;
    
    // MIN-MAX VALUES FOR ACCOUNT NUMBER
    private Integer min = 100;
    private Integer max = 999;
    
    // FETCH THE MISC MODULE
    private Misc misc = new Misc();
    
    // CONSTRUCTOR -- INITIALIZE THE MAP
    public Backend () { this.users = new HashMap<>(); }
    
    // ADD NEW USER
    public void add_user(String name) {
        
        // CREATE NEW ACCOUNT INSTANCE
        Account user = new Account (name, default_rate, default_balance);
        
        // RANDOMIZE A ACCOUNT NUMBER
        int random_number = (int) (Math.random() * (this.max - this.min)) + this.min;
        
        // CHECK IF IT ALREADY EXISTS
        boolean check = exists(random_number);
        
        // KEEP REROLLING UNTIL IT DOESNT
        while(check == true) {
            random_number = (int) (Math.random() * (this.max - this.min)) + this.min;
            check = exists(random_number);
        }
        
        // ADD IT TO THE MAP
        this.users.put(random_number, user);
        
        // LOG SUCCESS MESSAGE
        misc.success("USER '" + name + "' WAS ADDED!");
    }
    
    // RETURN ACCOUNT INSTANCE
    public Account fetch_user(Integer _number) {
        return users.get(_number);
    }
    
    // VIEW ALL ACCOUNT NUMBERS
    public void view_all() {
        
        misc.log("\n// VIEW ALL ACCOUNTS");
        
        // LOOP THROUGH EACH ACCOUNT NUMBER
        for (Integer key : users.keySet()) {
            
            misc.log("\t> " + key);
        }
        
        // ADD SPACE SEPARATOR
        misc.log("");
    }
    
    public boolean exists(Integer number) {
        return users.containsKey(number);
    }
}