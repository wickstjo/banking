package bank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Backend {
    
    // INITIALIZE THE CHECKINGS & SAVINGS HASHMAPS
    private final Map<Integer, Checking> checkings = new HashMap<>();
    private final Map<Integer, Saving> savings = new HashMap<>();
    private final Map<Integer, Credit> credits = new HashMap<>();
    
    // MIN-MAX VALUES FOR ACCOUNT NUMBER
    private final Integer min = 100;
    private final Integer max = 999;
    
    // CHECK IF THERE ARE EMPTY ACCOUNT NUMBERS LEFT
    public boolean accounts_left() {
        
        // DEFAULT TO FALSE
        boolean answer = false;
        
        // IF THERE IS SPACE -- CHANGE TO TRUE
        if (savings.size() < (max - min)) { answer = true; }
        
        return answer;
    }
    
    // ADD NEW USER
    public int add_user(String name) {
        
        // CREATE NEW INSTANCES
        Checking temp_checking = new Checking(name);
        Saving temp_saving = new Saving(name);
        Credit temp_credit = new Credit(name);

        // RANDOMIZE THE ACCOUNT NUMBER
        int random_number = (int) (Math.random() * (this.max - this.min)) + this.min;

        // CHECK IF IT ALREADY EXISTS
        boolean check = exists(random_number);

        // KEEP REROLLING UNTIL IT DOESNT
        while(check == true) {
            random_number = (int) (Math.random() * (this.max - this.min)) + this.min;
            check = exists(random_number);
        }

        // ADD THEM TO THEIR MAPS
        this.checkings.put(random_number, temp_checking);
        this.savings.put(random_number, temp_saving);
        this.credits.put(random_number, temp_credit);
        
        return random_number;
    }
    
    // GET ALL REGISTERED ACCOUNT NUMBERS
    public ArrayList<Integer> get_accounts() {
        
        // INITIALIZE TEMP ARRAYLIST
        ArrayList<Integer> temp = new ArrayList<>();
        
        // LOOP IN ALL ACCOUNTS NUMBERS
        for (Integer key : checkings.keySet()) { temp.add(key); }

        return temp;
    }
    
    // GET USER SPECIFIC MAP INSTANCE
    public Checking get_checking(Integer _number) { return checkings.get(_number); }
    public Saving get_saving(Integer _number) { return savings.get(_number); }
    public Credit get_credit(Integer _number) { return credits.get(_number); }
    
    // CHECK IF AN ACCOUNT NUMBER EXISTS
    public boolean exists(Integer _number) { return checkings.containsKey(_number); }
    
    // ADD INTEREST TO ALL ACCOUNTS
    public void reward_interest() {
        
        // CHECK IF THERE ARE ANY ACCOUNTS
        if (!this.checkings.isEmpty()) {
        
            // LOOP THROUGH EACH ACCOUNT
            for (Integer account_number : this.checkings.keySet()) {

                // ADD INTEREST TO CHECKINGS ACCOUNT
                this.checkings.get(account_number).add_interest();

                // ADD INTEREST TO SAVINGS ACCOUNT
                this.savings.get(account_number).add_interest();
                
                // ADD INTEREST TO CREDIT ACCOUNT
                this.credits.get(account_number).add_interest();
            }
        
        }
    }
}