import java.util.Scanner;

public class TestObserverPattern {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();
        Observer mobileApp = new MobileApp();
        Observer webApp = new WebApp();

        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter stock Name (or 'exit' to quit): ");
            String stockSymbol = scanner.nextLine();
            if (stockSymbol.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Enter stock price: ");
            double stockPrice;
            try {
                stockPrice = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid price input.");
                continue;
            }

            stockMarket.setStockData(stockSymbol, stockPrice);
        }

        scanner.close();
    }
}
