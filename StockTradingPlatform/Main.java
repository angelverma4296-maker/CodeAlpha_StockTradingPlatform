import java.util.Scanner;

public class Main {

    private static Scanner scanner =
            new Scanner(System.in);

    public static void main(String[] args) {

        TradingPlatform platform =
                new TradingPlatform();

        // Create default user
        User user = new User(
                1,
                "John Doe",
                "john@example.com",
                10000.00
        );

        platform.addUser(user);

        System.out.println();
        System.out.println("================================================");
        System.out.println("        STOCK TRADING PLATFORM");
        System.out.println("================================================");

        System.out.println(
                "Welcome, " + user.getName() + "!"
        );

        boolean running = true;

        while (running) {

            displayMenu();

            int choice =
                    getIntInput("Enter your choice: ");

            switch (choice) {

                case 1:
                    platform.displayMarket();
                    break;

                case 2:
                    buyStock(platform, user);
                    break;

                case 3:
                    sellStock(platform, user);
                    break;

                case 4:
                    user.getPortfolio()
                            .displayPortfolio(
                                    platform.getMarket()
                            );
                    break;

                case 5:
                    user.getPortfolio()
                            .displayTransactionHistory();
                    break;

                case 6:
                    user.displayUserInfo();
                    break;

                case 7:
                    updateStockPrice(platform);
                    break;

                case 8:
                    platform.savePortfolio(
                            user,
                            "portfolio.txt"
                    );
                    break;

                case 9:

                    System.out.println();
                    System.out.println(
                            "Thank you for using the Stock Trading Platform!"
                    );

                    running = false;
                    break;

                default:

                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }

        scanner.close();
    }

    // Display menu
    private static void displayMenu() {

        System.out.println();
        System.out.println("================================================");
        System.out.println("                    MENU");
        System.out.println("================================================");

        System.out.println("1. View Market");
        System.out.println("2. Buy Stock");
        System.out.println("3. Sell Stock");
        System.out.println("4. View Portfolio");
        System.out.println("5. Transaction History");
        System.out.println("6. User Information");
        System.out.println("7. Update Stock Price");
        System.out.println("8. Save Portfolio");
        System.out.println("9. Exit");

        System.out.println("================================================");
    }

    // Buy stock
    private static void buyStock(
            TradingPlatform platform,
            User user) {

        System.out.println();
        System.out.println("============== BUY STOCK ==============");

        String symbol =
                getStringInput(
                        "Enter stock symbol: "
                ).toUpperCase();

        Stock stock =
                platform.getStock(symbol);

        if (stock == null) {

            System.out.println(
                    "Stock not found."
            );

            return;
        }

        System.out.printf(
                "Current price of %s: $%.2f%n",
                symbol,
                stock.getPrice()
        );

        int quantity =
                getIntInput(
                        "Enter quantity: "
                );

        user.getPortfolio()
                .buyStock(
                        stock,
                        quantity
                );
    }

    // Sell stock
    private static void sellStock(
            TradingPlatform platform,
            User user) {

        System.out.println();
        System.out.println("============== SELL STOCK ==============");

        String symbol =
                getStringInput(
                        "Enter stock symbol: "
                ).toUpperCase();

        Stock stock =
                platform.getStock(symbol);

        if (stock == null) {

            System.out.println(
                    "Stock not found."
            );

            return;
        }

        System.out.printf(
                "Current price of %s: $%.2f%n",
                symbol,
                stock.getPrice()
        );

        int quantity =
                getIntInput(
                        "Enter quantity: "
                );

        user.getPortfolio()
                .sellStock(
                        stock,
                        quantity
                );
    }

    // Update price
    private static void updateStockPrice(
            TradingPlatform platform) {

        System.out.println();
        System.out.println(
                "========== UPDATE STOCK PRICE =========="
        );

        String symbol =
                getStringInput(
                        "Enter stock symbol: "
                ).toUpperCase();

        Stock stock =
                platform.getStock(symbol);

        if (stock == null) {

            System.out.println(
                    "Stock not found."
            );

            return;
        }

        System.out.printf(
                "Current price: $%.2f%n",
                stock.getPrice()
        );

        double newPrice =
                getDoubleInput(
                        "Enter new price: "
                );

        boolean updated =
                platform.updateStockPrice(
                        symbol,
                        newPrice
                );

        if (updated) {

            System.out.println(
                    "Stock price updated successfully."
            );

            System.out.printf(
                    "%s new price: $%.2f%n",
                    symbol,
                    newPrice
            );

        } else {

            System.out.println(
                    "Unable to update stock price."
            );
        }
    }

    // Integer input
    private static int getIntInput(
            String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }

    // Double input
    private static double getDoubleInput(
            String message) {

        while (true) {

            try {

                System.out.print(message);

                return Double.parseDouble(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid amount."
                );
            }
        }
    }

    // String input
    private static String getStringInput(
            String message) {

        System.out.print(message);

        return scanner.nextLine();
    }
}