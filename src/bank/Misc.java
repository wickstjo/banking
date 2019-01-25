package bank;
import java.util.ArrayList;

public class Misc {
    
    // COLOR CODES
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    
    // SHORTHAND FOR LOGGING -- TOO USED TO JAVASCRIPT
    public void log(Object content) { System.out.println(content); }
    
    // LOG COLORED TEXT
    public void success(Object content) { log("\n" + ANSI_BLUE + content + ANSI_RESET + "\n"); }
    public void error(Object content) { log("\n" + ANSI_RED + content + ANSI_RESET + "\n"); }
    
        // CONSTRUCT TEXT TO ARRAYLIST
    public ArrayList<String> create_whitelist(String _input) {
        
        // INITIALIZE THE ARRAYLIST
        ArrayList<String> whitelist = new ArrayList<>();
        
        // REMOVE SPACES & CONVERT INPUT TO ARRAY
        String[] temp = _input.replaceAll(" ","").split(",");
        
        // LOOP THROUGH TEMP LIST
        for (Integer x = 0; x < temp.length; x++) {
            
            // ADD ITEM TO WHITELIST
            whitelist.add(temp[x]);
        }
        
        return whitelist;
    }
    
    // CHECK VARIABLE TYPE
    public String check_type(String _variable) {
        
        // PLACEHOLDER
        String type;
        
        // CHECK IF ITS AN INTEGER
        try {
            Integer.parseInt(_variable);
            type = "int";
            
        // IF NOT
        } catch(NumberFormatException e) {
            
            // CHECK IF ITS A DOUBLE
            try {
                Double.parseDouble(_variable); 
                type = "dbl";
            
            // IF NOT
            } catch(NumberFormatException f) {
                
                // CHECK IF ITS A STRING
                try {
                    String.format(_variable); 
                    type = "str";

                // IF NOT
                } catch(NumberFormatException g) {
                    type = "unknown type";
                }
            }
        }
        
        return type;
    }
    
    // CONVERT STRING TO DOUBLE
    public double to_dbl(String _amount) {
        
        // PLACEHOLDER
        double amount;
        
        // CHECK THE VARIABLE TYPE
        String check = check_type(_amount);
        
        // IF ITS A DOUBLE
        if (check == "dbl") { amount = Double.parseDouble(_amount);
            
        // IF ITS AN INTEGER
        } else { amount = Integer.parseInt(_amount); }
        
        return amount;
    }
}