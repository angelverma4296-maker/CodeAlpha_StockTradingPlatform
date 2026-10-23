public class User {

    private int userId;
    private String name;
    private String email;
    private Portfolio portfolio;

    public User(
            int userId,
            String name,
            String email,
            double initialBalance) {

        this.userId = userId;
        this.name = name;
        this.email = email;
        this.portfolio = new Portfolio(initialBalance);
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void displayUserInfo() {

        System.out.println();
        System.out.println("================ USER INFORMATION ================");
        System.out.println("User ID : " + userId);
        System.out.println("Name    : " + name);
        System.out.println("Email   : " + email);
        System.out.printf(
                "Cash    : $%.2f%n",
                portfolio.getCash()
        );
        System.out.println("===================================================");
    }
}
