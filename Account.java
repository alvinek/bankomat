import java.util.Random;

public class Account
{
    String userName = "";
    String userSurname = "";
    String userPassword = "";
    String userAccountNumber = "";
    double userAccountBalance = 0;

    public Account(String userName, String userSurname, String userPassword)
    {
        this.userName = userName;
        this.userSurname = userSurname;
        this.userPassword = userPassword;
        this.userAccountNumber = GetRandomAccountNumber();
        this.userAccountBalance = 0;
    }

    public static String GetRandomAccountNumber()
    {
        String constructedAccountNumber = "";
        Random rand = new Random();

        int firstTwoDecimals = rand.nextInt(40) + 10;

        constructedAccountNumber += firstTwoDecimals;
        
        for(int i = 0; i<=6; i++)
        {
            int nextFourDecimals = rand.nextInt(8999) + 1000;
            constructedAccountNumber += " " + nextFourDecimals;
        }

        return constructedAccountNumber;
    }
}