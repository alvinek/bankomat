import java.util.Scanner;

class Init {
    static String fileDbName = "bank.txt";

    static Scanner scanner = new Scanner(System.in);

    public static void ConsoleOut(String s)
    {
        System.out.println(s);
    }

    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        
        currentDir += "\\" + fileDbName;

        FileDriver fileDriver = new FileDriver(currentDir);

        if(fileDriver.checkFileExist())
        {
            Bank bank = new Bank(fileDriver, fileDriver.readAccounts(), scanner);
            bank.BankMain();
        }
        else
        {
            Account newAccount = CreateAccount.CreateNewAccount(scanner);
            fileDriver.saveAccount(newAccount);
            Bank bank = new Bank(fileDriver, fileDriver.readAccounts(), scanner);
            bank.BankMain();
        }

        System.exit(0);
    }
}