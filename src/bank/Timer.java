package bank;
import java.time.Clock;

public class Timer extends Thread {
    
    // DECLARE ASSIST MODULES
    Clock clock;
    Backend backend;
    Misc misc;
    
    // COOLDOWN IN MS
    Integer cooldown = 1000;
    
    // CONSTRUCTOR
    public Timer(Backend _backend) {
        clock = Clock.systemDefaultZone();
        this.backend = _backend;
        misc = new Misc();
    }
   
    // RUN THE PROCESS
    @Override public void run() {
        
        // LOOP UNTIL BROKEN
        while(true) {
            
            // REWARD ALL ACCOUNTS WITH INTEREST
            backend.reward_interest();
            
            // SLEEP FOR X
            try { Thread.sleep(this.cooldown);
            
            // WHEN INTERRUPTED -- EXIT THE LOOP
            } catch(InterruptedException ex) { break; }
        }
    }
}