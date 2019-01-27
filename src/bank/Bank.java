package bank;

public class Bank {
    public static void main(String[] args) {
        
        // FETCH MODULES
        Backend backend = new Backend();
        Ui ui = new Ui(backend);
        
        // START THE MAIN MENU
        ui.main_menu();
    }
}