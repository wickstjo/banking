package bank;

public class Checking extends Account {

    // SET STATIC RATE
    private final double rate = 0.01;
    
    // TOTAL WITHDRAWS/DEPOSITS
    double withdraws = 0;
    double deposits = 0;
    
    // CONSTRUCTOR -- INITIATE ACCOUNT CLASS
    public Checking (String _owner) { super(_owner); }
    
    // TOTAL WITHDRAWS/DEPOSITS GETTERS
    public double get_withdraws() { return this.withdraws; }
    public double get_deposits() { return this.deposits; }
    
    // ADD INTEREST -- OVERRIDDEN
    @Override public void add_interest() {
        
        // FIGURE OUT THE REWARD & ADD IT TO THE ACCOUNT BALANCE
        double reward = this.get_balance() * this.rate;
        this.deposit(reward);
    }

    // WITHDRAW FUNDS -- OVERRIDDEN
    @Override public void withdraw(double _amount) {
        
        // FIGURE OUT NEW BALANCE
        double new_balance = this.get_balance() - _amount;
        
        // INCREASE COUNT & SET RECALIBRATED BALANCE
        this.withdraws += _amount;
        this.set_balance(new_balance);
    }
    
    // DEPOSIT FUNDS -- OVERRIDDEN
    @Override public void deposit(double _amount) { 
    
        // FIGURE OUT NEW BALANCE
        double new_balance = this.get_balance() + _amount;
        
        // INCREASE COUNT & SET RECALIBRATED BALANCE
        this.deposits += _amount;
        this.set_balance(new_balance);
        
    }
    
    // STATISTICAL OVERVIEW
    @Override public void overview() {
        misc.log("CHECKINGS BALANCE:\t\t" + misc.round(this.get_balance(), 2));
        misc.log("TOTAL WITHDRAWS:\t\t" + misc.round(this.get_withdraws(), 2));
        misc.log("TOTAL DEPOSITS:\t\t\t" + misc.round(this.get_deposits(), 2));
    }
}