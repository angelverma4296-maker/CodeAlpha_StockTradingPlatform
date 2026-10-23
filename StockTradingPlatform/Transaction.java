import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String type;
    private String stockSymbol;
    private int quantity;
    private double price;
    private double totalAmount;
    private LocalDateTime dateTime;

    public Transaction(
            String type,
            String stockSymbol,
            int quantity,
            double price) {

        this.type = type;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.totalAmount = quantity * price;
        this.dateTime = LocalDateTime.now();
    }

    public String getType() {
        return type;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return String.format(
                "%-6s %-8s %-10d $%-10.2f $%-12.2f %s",
                type,
                stockSymbol,
                quantity,
                price,
                totalAmount,
                dateTime.format(formatter)
        );
    }
}