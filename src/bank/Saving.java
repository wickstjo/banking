package bank;

public class Saving extends Account {

    // SET STATIC RATE
    private final double rate = 0.1;
    
    // SET DEFAULT VALUES
    private double earnings = 0;
    
    // CONSTRUCTOR -- INITIATE ACCOUNT CLASS
    public Saving (String _owner) { super(_owner); }
    
    // GET EARNINGS
    public double get_earnings() { return this.earnings; }
    
    // ADD INTEREST -- OVERRIDDEN
    @Override public void add_interest() {
        
        // FIGURE OUT THE REWARD
        double reward = this.get_balance() * this.rate;
        
        // ADD AMOUNT TO EARNINGS COUNTER
        earnings += reward;
        
        // INCREASE ACCOUNT BALANCE
        this.deposit(reward);
    }
}