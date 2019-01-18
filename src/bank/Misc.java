package bank;

public class Misc {
    
    // COLOR CODES
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    
    // SHORTHAND FOR LOGGING STRING
    public void log(String stuff) { System.out.println(stuff); }
    
    // LOG GREEN TEXT
    public void success(String stuff) { System.out.println(ANSI_GREEN + stuff + ANSI_RESET); }
    
    // LOG RED TEXT
    public void error(String stuff) { System.out.println(ANSI_RED + stuff + ANSI_RESET); }
}