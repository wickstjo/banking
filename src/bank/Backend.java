package bank;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Backend implements Serializable {
    
    // DECLARE ASSIST MODULE
    private final Misc misc;
    
    // DEFAULT DATA HASHMAP
    private Map<Integer, AccountWrapper> data = new HashMap<>();
    private final String file_path = "db.txt";
    
    // MIN-MAX VALUES FOR ACCOUNT NUMBER
    private final Integer min = 100;
    private final Integer max = 999;
    
    public Backend(Misc _misc) {
        
        // ATTACH MISC MODULE
        this.misc = _misc;
        
        // ATTEMPT LOADING DATA FROM STORAGE
        load();
    }
    
    // CHECK IF THERE ARE EMPTY ACCOUNT NUMBERS LEFT
    public boolean accounts_left() {
        
        // DEFAULT TO FALSE
        boolean answer = false;
        
        // IF THERE IS SPACE -- CHANGE TO TRUE
        if (this.data.size() < (max - min)) { answer = true; }
        
        return answer;
    }
    
    // ADD NEW USER
    public int add_user(String _name) {

        // GENERATE A RANDOM NUMBER
        int random_number = (int) (Math.random() * (this.max - this.min)) + this.min;

        // CHECK IF IT ALREADY EXISTS
        boolean check = exists(random_number);

        // KEEP REROLLING UNTIL IT DOESNT
        while(check == true) {
            random_number = (int) (Math.random() * (this.max - this.min)) + this.min;
            check = exists(random_number);
        }
        
        // CREATE A NEW INSTANCE OF EACH ACCOUNT SUBTYPE
        AccountWrapper accounts = new AccountWrapper(_name);

        // CREATE NEW ENTRY IN THE DATA HASHMAP
        this.data.put(random_number, accounts);
        
        return random_number;
    }
    
    // GET ALL REGISTERED ACCOUNT NUMBERS
    public ArrayList<Integer> get_accounts() {
        
        // INITIALIZE TEMP ARRAYLIST
        ArrayList<Integer> temp = new ArrayList<>();
        
        // LOOP IN ALL ACCOUNTS NUMBERS
        for (Integer key : data.keySet()) { temp.add(key); }

        return temp;
    }
    
    // GET USER SPECIFIC MAP INSTANCE
    public Checking get_checking(Integer _number) { return data.get(_number).get_checking(); }
    public Savings get_saving(Integer _number) { return data.get(_number).get_savings(); }
    public Credit get_credit(Integer _number) { return data.get(_number).get_credit(); }
    
    // CHECK IF AN ACCOUNT NUMBER EXISTS
    public boolean exists(Integer _number) { return data.containsKey(_number); }
    
    // ADD INTEREST TO ALL ACCOUNTS
    public void reward_interest() {
        
        // CHECK IF THERE ARE ANY ACCOUNTS
        if (!this.data.isEmpty()) {
        
            // LOOP THROUGH EACH ACCOUNT
            for (Integer account_number : this.data.keySet()) {

                // ADD INTEREST TO CHECKINGS ACCOUNT
                get_checking(account_number).add_interest();

                // ADD INTEREST TO SAVINGS ACCOUNT
                get_saving(account_number).add_interest();
                
                // ADD INTEREST TO CREDIT ACCOUNT
                get_credit(account_number).add_interest();
            }
        
        }
    }
    
    // LOAD DATA FROM FILE
    private void load() {
        try {
            
            // OPEN STREAMS
            FileInputStream from_file = new FileInputStream(new File(this.file_path));
            ObjectInputStream from_object = new ObjectInputStream(from_file);
            
            // SET THE DATA VARIABLE & LOG SUCCESS
            this.data = (Map<Integer, AccountWrapper>) from_object.readObject();
            misc.success("LOADED FROM DATAFILE!");
            
            // CLOSE STREAMS
            from_file.close();
            from_object.close();

        // IF SOMETHING GOES WRONG, LOG ERROR
        } catch(ClassNotFoundException | IOException ex) {
            misc.error("LOADING ERROR - USING DEFAULT DATASET!");
        }
    }
    
    // SAVE DATA TO FILE
    public void save() {
        try {
            
            // OPEN STREAMS
            FileOutputStream to_file = new FileOutputStream(new File(this.file_path));
            ObjectOutputStream to_object = new ObjectOutputStream(to_file);
            
            // WRITE TO FILE & LOG SUCCESS
            to_object.writeObject(this.data);
            misc.success("DATA SAVED!");
            
            // CLOSE STREAMS
            to_object.close();
            to_file.close();
            
        // IF SOMETHING GOES WRONG, LOG ERROR
        } catch(FileNotFoundException ex) {
            misc.error("SAVING ERROR! " + ex);
        } catch(IOException ex) {
            misc.error("SAVING ERROR! " + ex);
        }
    }
}