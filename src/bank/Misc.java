package bank;

import java.util.Map;

public class Misc {
    
    // SHORTHAND FOR LOGGING STRING
    public void log(String stuff) {
        System.out.println(stuff);
    }
    
    // CHECK FOR ACCOUNT NUMBER UNIQUENESS
    public boolean check(Integer number, Map<Integer, Account> users) {
        
        // DEFAULT AS FALSE
        boolean response = false;
        
        // LOOP THROUGH EACH KEY
        for (Integer key : users.keySet()) {
            
            // CHANGE RESPONSE TO TRUE IF IT EXISTS
            if (number == key) { response = true; }
        }
        
        return response;
    }
}