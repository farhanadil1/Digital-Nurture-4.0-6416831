import java.util.Scanner;

public class FinancialForecast {

    //recursive method to calculate future value
    public static double calculateFutureValue(double currentValue, double growthRate, int years) {
        if (years == 0) {
            return currentValue;
        }
        return calculateFutureValue(currentValue, growthRate, years - 1) * (1 + growthRate);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //input from user
        System.out.print("Enter initial amount: ");
        double initialValue = scanner.nextDouble();

        System.out.print("Enter annual growth rate (e.g., 0.05 for 5%): ");
        double growthRate = scanner.nextDouble();

        System.out.print("Enter number of years: ");
        int years = scanner.nextInt();

       
        double futureValue = calculateFutureValue(initialValue, growthRate, years);

      
        System.out.printf("Future value after %d years: %.2f\n", years, futureValue);

        scanner.close();
    }
}
