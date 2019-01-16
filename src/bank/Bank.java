package bank;

public class Bank {
    public static void main(String[] args) {
        
        // FETCH THE BACKEND MODULE
        Backend backend = new Backend();
        
        // FILL THE MAP WITH TESTUSERS
        backend.fill();
    }
}