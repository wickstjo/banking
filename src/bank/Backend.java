package bank;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Backend {
    
    // USERS MAP
    Map<Integer, Account> users;
    
    // DEFAULT VALUES FOR NEW ACCOUNTS
    double default_rate = 0.035;
    double default_balance = 0;
    
    // FETCH THE MISC MODULE
    Misc misc = new Misc();
    
    // CONSTRUCTOR -- INITIALIZE THE MAP
    public Backend () { this.users = new HashMap<>(); }
    
    // FILL THE ACCOUNT MAP WITH TEST-USERS
    public void fill() {
        
        // USERNAMES
        List<String> names = Arrays.asList("foo", "bar", "biz");
        
        // LOOP THROUGH THE NAMES & ADD NEW ACCOUNT INSTANCE
        for(int x = 0; x < names.size(); x++) {
            add_user(names.get(x));
        }
    }
    
    // ADD NEW USER
    public void add_user(String name) {
        
        // CREATE NEW ACCOUNT INSTANCE
        Account user = new Account (name, default_rate, default_balance);
        
        // ADD IT TO THE MAP
        this.users.put(0, user);
        
        // LOG SUCCESS MESSAGE
        misc.log("User '" + name + "' added!");
    }
}