package bank;

public class Misc {
    
    // COLOR CODES
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    
    // SHORTHAND FOR LOGGING STRING
    public void log(String stuff) { System.out.println(stuff); }
    
    // CHANGE TEXT TO GREEN/RED
    public void success(String stuff) { log("\n" + ANSI_BLUE + stuff + ANSI_RESET); }
    public void error(String stuff) { log("\n" + ANSI_RED + stuff + ANSI_RESET); }
}