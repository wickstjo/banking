package bank;

public class Bank {
    public static void main(String[] args) {
        
        // FETCH MODULES
        Misc misc = new Misc();
        Backend backend = new Backend(misc);
        Ui ui = new Ui(backend, misc);
        
        // START THE MAIN MENU
        ui.main_menu();
    }
}