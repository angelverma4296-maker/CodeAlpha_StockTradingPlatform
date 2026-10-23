import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TradingPlatform {

    private Map<String, Stock> market;
    private Map<Integer, User> users;

    public TradingPlatform() {

        market = new HashMap<>();
        users = new HashMap<>();

        initializeMarket();
    }

    // Add default stocks
    private void initializeMarket() {

        market.put(
                "AAPL",
                new Stock("AAPL", "Apple Inc.", 225.00)
        );

        market.put(
                "MSFT",
                new Stock("MSFT", "Microsoft", 510.00)
        );

        market.put(
                "GOOGL",
                new Stock("GOOGL", "Alphabet", 178.00)
        );

        market.put(
                "AMZN",
                new Stock("AMZN", "Amazon", 230.00)
        );

        market.put(
                "TSLA",
                new Stock("TSLA", "Tesla", 340.00)
        );

        market.put(
                "META",
                new Stock("META", "Meta Platforms", 750.00)
        );

        market.put(
                "NVDA",
                new Stock("NVDA", "NVIDIA", 180.00)
        );
    }

    // Display market
    public void displayMarket() {

        System.out.println();
        System.out.println("==============================================================");
        System.out.println("                       STOCK MARKET");
        System.out.println("==============================================================");

        System.out.printf(
                "%-8s %-20s %-12s %-12s%n",
                "Symbol",
                "Company",
                "Price",
                "Change"
        );

        System.out.println(
                "--------------------------------------------------------------"
        );

        for (Stock stock : market.values()) {

            System.out.printf(
                    "%-8s %-20s $%-10.2f %+.2f%%%n",
                    stock.getSymbol(),
                    stock.getCompanyName(),
                    stock.getPrice(),
                    stock.getPercentageChange()
            );
        }

        System.out.println(
                "=============================================================="
        );
    }

    // Find stock
    public Stock getStock(String symbol) {

        return market.get(
                symbol.toUpperCase()
        );
    }

    // Add user
    public void addUser(User user) {

        users.put(
                user.getUserId(),
                user
        );
    }

    // Get user
    public User getUser(int userId) {

        return users.get(userId);
    }

    // Update stock price
    public boolean updateStockPrice(
            String symbol,
            double newPrice) {

        Stock stock = getStock(symbol);

        if (stock == null) {
            return false;
        }

        if (newPrice <= 0) {
            return false;
        }

        stock.updatePrice(newPrice);

        return true;
    }

    // Save portfolio to file
    public void savePortfolio(
            User user,
            String fileName) {

        try {

            PrintWriter writer =
                    new PrintWriter(
                            new FileWriter(fileName)
                    );

            Portfolio portfolio =
                    user.getPortfolio();

            writer.println(
                    "STOCK TRADING PLATFORM"
            );

            writer.println(
                    "======================"
            );

            writer.println(
                    "User ID: " + user.getUserId()
            );

            writer.println(
                    "Name: " + user.getName()
            );

            writer.println(
                    "Email: " + user.getEmail()
            );

            writer.println();

            writer.println(
                    "Cash: " + portfolio.getCash()
            );

            writer.println();

            writer.println(
                    "HOLDINGS"
            );

            writer.println(
                    "--------"
            );

            for (String symbol :
                    portfolio.getHoldings().keySet()) {

                int quantity =
                        portfolio.getHoldings()
                                .get(symbol);

                double avgPrice =
                        portfolio.getAveragePrices()
                                .get(symbol);

                writer.printf(
                        "%s | Quantity: %d | Average Price: %.2f%n",
                        symbol,
                        quantity,
                        avgPrice
                );
            }

            writer.println();

            writer.println(
                    "TRANSACTIONS"
            );

            writer.println(
                    "------------"
            );

            for (Transaction transaction :
                    portfolio.getTransactions()) {

                writer.println(transaction);
            }

            writer.close();

            System.out.println();
            System.out.println(
                    "Portfolio saved successfully!"
            );

            System.out.println(
                    "File: " + fileName
            );

        } catch (IOException e) {

            System.out.println(
                    "Error while saving portfolio."
            );

            System.out.println(
                    e.getMessage()
            );
        }
    }

    public Map<String, Stock> getMarket() {
        return market;
    }
}
