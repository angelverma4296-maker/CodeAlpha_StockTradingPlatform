import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Portfolio {

    private double cash;

    // Stock symbol -> quantity
    private Map<String, Integer> holdings;

    // Stock symbol -> average purchase price
    private Map<String, Double> averagePrices;

    private List<Transaction> transactions;

    public Portfolio(double initialCash) {
        this.cash = initialCash;
        this.holdings = new HashMap<>();
        this.averagePrices = new HashMap<>();
        this.transactions = new ArrayList<>();
    }

    // Get current cash
    public double getCash() {
        return cash;
    }

    // Buy stock
    public boolean buyStock(Stock stock, int quantity) {

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than 0.");
            return false;
        }

        double totalCost = stock.getPrice() * quantity;

        if (totalCost > cash) {
            System.out.println("Insufficient funds.");
            System.out.printf("Required: $%.2f%n", totalCost);
            System.out.printf("Available: $%.2f%n", cash);
            return false;
        }

        String symbol = stock.getSymbol();

        int oldQuantity = holdings.getOrDefault(symbol, 0);

        double oldAveragePrice =
                averagePrices.getOrDefault(symbol, 0.0);

        int newQuantity = oldQuantity + quantity;

        double newAveragePrice =
                ((oldQuantity * oldAveragePrice)
                        + (quantity * stock.getPrice()))
                        / newQuantity;

        holdings.put(symbol, newQuantity);
        averagePrices.put(symbol, newAveragePrice);

        cash -= totalCost;

        Transaction transaction = new Transaction(
                "BUY",
                symbol,
                quantity,
                stock.getPrice()
        );

        transactions.add(transaction);

        System.out.println();
        System.out.println("Stock purchased successfully!");
        System.out.println("Stock: " + symbol);
        System.out.println("Quantity: " + quantity);
        System.out.printf("Price: $%.2f%n", stock.getPrice());
        System.out.printf("Total Cost: $%.2f%n", totalCost);

        return true;
    }

    // Sell stock
    public boolean sellStock(Stock stock, int quantity) {

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than 0.");
            return false;
        }

        String symbol = stock.getSymbol();

        int ownedQuantity = holdings.getOrDefault(symbol, 0);

        if (ownedQuantity < quantity) {
            System.out.println("You do not own enough shares.");

            System.out.println(
                    "Available shares: " + ownedQuantity
            );

            return false;
        }

        double totalValue =
                stock.getPrice() * quantity;

        int remainingQuantity =
                ownedQuantity - quantity;

        if (remainingQuantity == 0) {

            holdings.remove(symbol);
            averagePrices.remove(symbol);

        } else {

            holdings.put(symbol, remainingQuantity);
        }

        cash += totalValue;

        Transaction transaction = new Transaction(
                "SELL",
                symbol,
                quantity,
                stock.getPrice()
        );

        transactions.add(transaction);

        System.out.println();
        System.out.println("Stock sold successfully!");
        System.out.println("Stock: " + symbol);
        System.out.println("Quantity: " + quantity);
        System.out.printf("Price: $%.2f%n", stock.getPrice());
        System.out.printf("Total Received: $%.2f%n", totalValue);

        return true;
    }

    // Display portfolio
    public void displayPortfolio(Map<String, Stock> market) {

        System.out.println();
        System.out.println("==============================================================");
        System.out.println("                       MY PORTFOLIO");
        System.out.println("==============================================================");

        if (holdings.isEmpty()) {

            System.out.println("No stocks currently owned.");

        } else {

            System.out.printf(
                    "%-8s %-10s %-15s %-15s %-15s%n",
                    "Symbol",
                    "Quantity",
                    "Avg Price",
                    "Current Price",
                    "Profit/Loss"
            );

            System.out.println(
                    "---------------------------------------------------------------------"
            );

            double totalInvested = 0;
            double currentValue = 0;

            for (String symbol : holdings.keySet()) {

                Stock stock = market.get(symbol);

                int quantity = holdings.get(symbol);

                double avgPrice =
                        averagePrices.get(symbol);

                double currentPrice =
                        stock.getPrice();

                double invested =
                        avgPrice * quantity;

                double value =
                        currentPrice * quantity;

                double profitLoss =
                        value - invested;

                totalInvested += invested;
                currentValue += value;

                System.out.printf(
                        "%-8s %-10d $%-14.2f $%-14.2f %+.2f%n",
                        symbol,
                        quantity,
                        avgPrice,
                        currentPrice,
                        profitLoss
                );
            }

            System.out.println(
                    "---------------------------------------------------------------------"
            );

            double totalProfitLoss =
                    currentValue - totalInvested;

            System.out.printf(
                    "Total Invested:  $%.2f%n",
                    totalInvested
            );

            System.out.printf(
                    "Current Value:   $%.2f%n",
                    currentValue
            );

            System.out.printf(
                    "Profit/Loss:     %+.2f%n",
                    totalProfitLoss
            );
        }

        System.out.println();
        System.out.printf(
                "Available Cash: $%.2f%n",
                cash
        );

        System.out.printf(
                "Total Portfolio Value: $%.2f%n",
                getTotalPortfolioValue(market)
        );

        System.out.println(
                "=============================================================="
        );
    }

    // Calculate total portfolio value
    public double getTotalPortfolioValue(
            Map<String, Stock> market) {

        double total = cash;

        for (String symbol : holdings.keySet()) {

            Stock stock = market.get(symbol);

            int quantity = holdings.get(symbol);

            total += stock.getPrice() * quantity;
        }

        return total;
    }

    // Display transaction history
    public void displayTransactionHistory() {

        System.out.println();
        System.out.println("==============================================================");
        System.out.println("                    TRANSACTION HISTORY");
        System.out.println("==============================================================");

        if (transactions.isEmpty()) {

            System.out.println("No transactions found.");

        } else {

            System.out.printf(
                    "%-6s %-8s %-10s %-12s %-14s %s%n",
                    "Type",
                    "Symbol",
                    "Quantity",
                    "Price",
                    "Total",
                    "Date"
            );

            System.out.println(
                    "--------------------------------------------------------------------------"
            );

            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }

        System.out.println(
                "=============================================================="
        );
    }

    // Get holdings
    public Map<String, Integer> getHoldings() {
        return holdings;
    }

    // Get average prices
    public Map<String, Double> getAveragePrices() {
        return averagePrices;
    }

    // Get transactions
    public List<Transaction> getTransactions() {
        return transactions;
    }
}
