public class Stock {

    private String symbol;
    private String companyName;
    private double price;
    private double previousPrice;

    public Stock(String symbol, String companyName, double price) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.price = price;
        this.previousPrice = price;
    }

    // Getters
    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getPrice() {
        return price;
    }

    public double getPreviousPrice() {
        return previousPrice;
    }

    // Update stock price
    public void updatePrice(double newPrice) {
        previousPrice = price;
        price = newPrice;
    }

    // Calculate percentage change
    public double getPercentageChange() {

        if (previousPrice == 0) {
            return 0;
        }

        return ((price - previousPrice) / previousPrice) * 100;
    }

    // Calculate price change
    public double getPriceChange() {
        return price - previousPrice;
    }

    @Override
    public String toString() {
        return String.format(
                "%-8s %-20s $%-10.2f %+.2f%%",
                symbol,
                companyName,
                price,
                getPercentageChange()
        );
    }
}

       

