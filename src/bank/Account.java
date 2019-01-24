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
                misc.log("Rate:\t\t\t" + this.rate);
            break;
                
            // BALANCE
            case "balance":
                misc.log("Balance:\t\t" + this.balance);
            break;
            
            // LOG ERROR WHEN REQUEST ISNT FOUND
            default:
                misc.log("Request not found!");
            break; 
        }
    }

    // INSPECT CURRENT STATS
    public void inspect() {
        
        // HEADER
        misc.log("\n// INSPECT CURRENT STATS");
        
        // LOG EACH VALUE
        get("owner");
        get("balance");
        get("rate");
    }
    
    // WITHDRAW MONEY
    public void withdraw(String _amount) {
        
        // CONVERT TO DOUBLE
        double amount = Double.parseDouble(_amount);
        
        // IF BALANCE IS HIGHER TO REQUESTED AMOUNT
        if (this.balance >= amount) {
            this.balance -= amount;
            misc.success("YOU WITHDREW: " + amount);
            
        // OTHERWISE LOG ERROR
        } else { misc.error("NOT ENOUGH BALANCE!"); }
    }
    
    // DEPOSIT MONEY
    public void deposit(double _amount) {
        
        // ADD TO EXISTING BALANCE
        this.balance += _amount;
        
        // LOG MESSAGE
        misc.success("YOU DEPOSITED: " + _amount);
    }
    
    // CHANGE RATE
    public void change_rate(double _rate) {
        
        // IF THE REQUEST IS HIGHER THAN ZERO
        if (_rate >= 0) {
            
            // ADD TO EXISTING BALANCE
            this.rate += _rate;

            // LOG MESSAGE
            misc.success("RATE CHANGED TO: " + _rate);
            
        // OTHERWISE, LOG ERROR
        } else { misc.error("RATE CANNOT BE NEGATIVE!"); }
    }
    
    // ADD INTEREST
    public void add_interest() {
        this.balance += this.balance * this.rate;
        misc.success("ADDED INTEREST!");
    }
}