package bank;

public class Bank {
    public static void main(String[] args) {
        
        // FETCH MODULES
        Backend backend = new Backend();
        Ui ui = new Ui(backend);
        
        // INITIALIZE THE INTERFACE
        ui.main_menu();
    }
}