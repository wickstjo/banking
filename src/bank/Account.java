package bank;

public class Account {

    // ACCOUNT VARS
    private final String owner;
    private double rate;
    private double balance;
    
    // FETCH THE MISC MODULE
    Misc misc = new Misc();

    // CONSTRUCTOR
    public Account (String _owner, double _rate, double _balance) {
        this.owner = _owner;
        this.rate = _rate;
        this.balance = _balance;
    }
    
    // GET VARIABLE VALUE
    public void get(String var) {
        
        switch(var) {
            
            // OWNER
            case "owner":
                misc.log("Owner:\t\t\t" + this.owner);
            break;
                
            // RATE
            case "rate":
                misc.log("Current Rate:\t\t\t" + this.rate);
            break;
                
            // BALANCE
            case "balance":
                misc.log("Current Balance:\t\t\t" + this.balance);
            break;
            
            // LOG ERROR WHEN REQUEST ISNT FOUND
            default:
                misc.log("Request not found!");
            break; 
        }
    }
    
    // WITHDRAW MONEY
    public void withdraw(double _amount) {
        
        // IF BALANCE IS HIGHER TO REQUESTED AMOUNT
        if (this.balance >= _amount) {
            this.balance -= _amount;
            misc.log("Withdrew:\t\t\t\t" + _amount);
            
        // OTHERWISE LOG ERROR
        } else { misc.log("Not enough Balance!"); }
        
        // EITHER WAY, LOG BALANCE
        get("balance");
    }
    
    // DEPOSIT MONEY
    public void deposit(double _amount) {
        
        // ADD TO EXISTING BALANCE
        this.balance += _amount;
        
        // LOG MESSAGE
        misc.log("Deposit:\t\t\t\t" + _amount);
        get("balance");
    }
    
    // CHANGE RATE
    public void change_rate(double _rate) {
        
        // IF THE REQUEST IS HIGHER THAN ZERO
        if (_rate >= 0) {
            
            // ADD TO EXISTING BALANCE
            this.rate += _rate;

            // LOG MESSAGE
            misc.log("Rate Changed to:\t\t\t\t" + _rate);
            
        // OTHERWISE, LOG ERROR
        } else { misc.log("Rate cannot be Negative!"); }
    }
    
    // ADD INTEREST
    public void add_interest() {
        this.balance += this.balance * this.rate;
        misc.log("Added Interest!");
    }
}