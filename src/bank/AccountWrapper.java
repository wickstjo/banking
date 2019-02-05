package bank;
import java.io.Serializable;

public class AccountWrapper implements Serializable {
    
    // DECLARE ACCOUNT TYPES
    private final Checking checking;
    private final Savings savings;
    private final Credit credit;
    
    // CREATE NEW INSTANCE OF EACH TYPE
    public AccountWrapper(String _name) {
        this.checking = new Checking(_name);
        this.savings = new Savings(_name);
        this.credit = new Credit(_name);
    }
    
    // GETTERS
    public Checking get_checking() { return this.checking; }
    public Savings get_savings() { return this.savings; }
    public Credit get_credit() { return this.credit; }    
}