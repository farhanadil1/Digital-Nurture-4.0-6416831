package SingletonPatternExample;

public class Logger {
	
	//private static instance
    private static Logger instance = null;

    //private constructor to restrict instantiation
    private Logger() {
        System.out.println("Logger instance created.");
    }

    //method to provide access to the instance
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    //logging method
    public void log(String message) {
        System.out.println("Log: " + message);
    }

}
