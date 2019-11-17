import java.util.Random;

public class Account
{
    String userName = "";
    String userSurname = "";
    String userPassword = "";
    String userAccountNumber = "";
    double userAccountBalance = 0;

    public Account(String userName, String userSurname, String userPassword, String userAccountNumber)
    {
        this.userName = userName;
        this.userSurname = userSurname;
        this.userPassword = userPassword;
        this.userAccountNumber = userAccountNumber;
        this.userAccountBalance = 0;
    }

    public static String RandomAccountNumberGenerator()
    {
        Random gen = new Random();
        
        return "";
    }
}