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
                misc.log("OWNER:\t\t\t" + this.owner);
            break;
                
            // RATE
            case "rate":
                misc.log("RATE:\t\t\t" + this.rate);
            break;
                
            // BALANCE
            case "balance":
                misc.log("BALANCE:\t\t" + this.balance);
            break;
            
            // LOG ERROR WHEN REQUEST ISNT FOUND
            default:
                misc.log("VARIABLE NOT FOUND!");
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
        
        // ADD LINEBREAK
        misc.log("");
    }
    
    // WITHDRAW MONEY
    public void withdraw(String _amount) {
        
        // CONVERT STRING TO DOUBLE
        double amount = misc.to_dbl(_amount);
        
        // IF BALANCE IS HIGHER TO REQUESTED AMOUNT
        if (this.balance >= amount) {
            this.balance -= amount;
            misc.success("YOU WITHDREW: " + amount);
            
        // OTHERWISE LOG ERROR
        } else { misc.error("NOT ENOUGH BALANCE!"); }
    }
    
    // DEPOSIT MONEY
    public void deposit(String _amount) {
        
        // CONVERT STRING TO DOUBLE
        double amount = misc.to_dbl(_amount);
        
        // ADD TO EXISTING BALANCE
        this.balance += amount;
        
        // LOG MESSAGE
        misc.success("YOU DEPOSITED: " + amount);
    }
    
    // CHANGE RATE
    public void change_rate(String _rate) {
        
        // CONVERT STRING TO DOUBLE
        double rate = misc.to_dbl(_rate);
        
        // IF THE REQUEST IS HIGHER THAN ZERO
        if (rate >= 0) {
            
            // ADD TO EXISTING BALANCE
            this.rate = rate;

            // LOG MESSAGE
            misc.success("RATE CHANGED TO: " + rate);
            
        // OTHERWISE, LOG ERROR
        } else { misc.error("RATE CANNOT BE NEGATIVE!"); }
    }
    
    // ADD INTEREST
    public void add_interest() {
        this.balance += this.balance * this.rate;
        misc.success("ADDED INTEREST!");
    }
}