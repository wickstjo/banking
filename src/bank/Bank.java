package bank;

public class Bank {
    public static void main(String[] args) {
        
        // FETCH MODULES
        Backend backend = new Backend();
        Ui ui = new Ui(backend);
        
        // FILL THE MAP WITH TESTUSERS
        backend.fill();
        
        // INITIALIZE THE INTERFACE
        ui.main_menu();
    }
}