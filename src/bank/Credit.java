package bank;

public class Credit extends Account {
    
    // SET STATIC RATE & INTEREST ACCUMULATOR
    private final double rate = 0.2;
    private double interest = 0;
    
    // SET WITHDRAW LIMIT & REMAINING FUNDS
    private final double limit = 1000;
    private double remaining = this.limit;
    
    // CONSTRUCTOR -- INITIATE ACCOUNT CLASS
    public Credit (String _owner) { super(_owner); }
    
    // CHECK IF A WITHDRAW REQUEST IS IN RANGE
    public boolean withdrawable(double _amount) {
        
        // DEFAULT TO FALSE
        boolean answer = false;
        
        // CALCULATE THE NEW BALANCE
        double result = this.remaining - _amount;

        // IF ITS ACCEPTABLE, CHANGE ANSWER TO TRUE
        if (result >= 0) { answer = true; }
        
        return answer;
    }
    
    // CHECK IF A DEPOSIT REQUEST IS IN RANGE
    public boolean depositable(double _amount) {
        
        // DEFAULT TO FALSE
        boolean answer = false;

        // IF THE AMOUNT DOESNT SURPASS THE TAB
        if (_amount <= this.tab()) { answer = true; }
        
        return answer;
    }
    
    // WITHDRAW FUNDS -- REDUCE REMAINING
    @Override public void withdraw(double _amount) { this.remaining -= _amount; }
    
    // DEPOSIT FUNDS -- REDUCE INTEREST FIRST
    @Override public void deposit(double _amount) {
        
        // CHECK IF AMOUNT IS LARGER THAN CURRENT OWED INTEREST
        if (this.interest < _amount) {
            
            // SUBTRACT INTEREST FROM THE AMOUNT
            _amount -= this.interest;
            
            // SET INTEREST TO ZERO
            this.interest = 0;
            
            // INCREASE REMAINING WITHDRAW FUNDS
            this.remaining += _amount;
        
        // IF IT ISNT, REDUCE INTEREST
        } else { this.interest -= _amount; }
    }
    
    // ADD INTEREST
    @Override public void add_interest() { this.interest += this.tab() * this.rate; }

    // GETTERS
    public double tab() { return (this.limit - this.remaining) + this.interest; }
    public double get_remaining() { return this.remaining; }
    public double get_limit() { return this.limit; }
    public double get_interest() { return this.interest; }
}