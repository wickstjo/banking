package bank;
import java.io.Serializable;

public abstract class Account implements Serializable {

    // DECLARE INSTANCE VARS
    private final String owner;
    private double balance = 0;
    
    // FETCH MISC MODULE FOR OVERVIEW METHODS
    public final Misc misc = new Misc();

    // CONSTRUCTOR -- SET OWNER
    public Account (String _owner) { this.owner = _owner; }
    
    // GLOBAL GETTER METHODS
    public String get_owner() { return this.owner; }
    public double get_balance() { return this.balance; }
    
    // SET BALANCE -- FOR CHECKING RECALIBRATION
    public void set_balance(double _sum) { this.balance = _sum; }
    
    // WITHDRAW/DEPOSIT METHODS
    public void withdraw(double _amount) { this.balance -= _amount; }
    public void deposit(double _amount) { this.balance += _amount; }
    
    // CHECK IF X AMOUNT IS WITHDRAWABLE
    public boolean withdrawable(double _amount) {
        
        // DEFAULT TO FALSE
        boolean status = false;
        
        // IF THE AMOUNT IS SMALLER OR EQUAL TO CURRENT BALANCE, CHANGE TO TRUE
        if (balance >= _amount) { status = true; }
        
        return status;
    }
    
    // ADD INTEREST
    public abstract void add_interest();
    
    // STATISTICAL OVERVIEW
    public abstract void overview();
}