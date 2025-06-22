package SingletonPatternExample;

public class Main {
    public static void main(String[] args) {

        
        Logger logger1 = Logger.getInstance();
        logger1.log("Application started.");

      
        Logger logger2 = Logger.getInstance();
        logger2.log("Performing Tasks..");

        // Checking if both instances are the same
        if (logger1 == logger2) {
            System.out.println("logger1 and logger2 are both the same instance.");
        } else {
            System.out.println("Different instances not in Singleton.");
        }
    }
}
