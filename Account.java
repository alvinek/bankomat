import java.util.Random;

public class Account {
    String userId = "";
    String userName = "";
    String userSurname = "";
    String userPassword = "";
    String userAccountNumber = "";
    double userAccountBalance = 0;

    public Account(String userName, String userSurname, String userPassword) {

        this.userId = getRandomUserId();
        this.userName = userName;
        this.userSurname = userSurname;
        this.userPassword = userPassword;
        this.userAccountNumber = getRandomAccountNumber();
        this.userAccountBalance = 0;
    }

    public Account(String userId, String userName, String userSurname, String userPassword, String userAccountNumber,
            double userAccountBalance) {

        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userPassword = userPassword;
        this.userAccountNumber = userAccountNumber;
        this.userAccountBalance = userAccountBalance;
    }

    public void setBalance(double balance) {
        this.userAccountBalance = balance;
    }

    public double getBalance() {
        return userAccountBalance;
    }

    private String getRandomAccountNumber() {
        String constructedAccountNumber = "";
        Random rand = new Random();

        int firstTwoDecimals = rand.nextInt(40) + 10;

        constructedAccountNumber += firstTwoDecimals;

        for (int i = 0; i <= 6; i++) {
            int nextFourDecimals = rand.nextInt(8999) + 1000;
            constructedAccountNumber += " " + nextFourDecimals;
        }

        return constructedAccountNumber;
    }

    private String getRandomUserId() {
        String constructedUserId = "";

        Random rand = new Random();

        int randomUserId = rand.nextInt(8999) + 1000;

        constructedUserId += randomUserId;

        return constructedUserId;
    }
}