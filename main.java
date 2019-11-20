
class Main {
    static String fileDbName = "bank.txt";

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
            Bank bank = new Bank(fileDriver, fileDriver.readAccounts());
            bank.BankMain();
        }
        else
        {
            Account newAccount = CreateAccount.CreateNewAccount();
            fileDriver.saveAccount(newAccount);
            Bank bank = new Bank(fileDriver, fileDriver.readAccounts());
            bank.BankMain();
        }

        System.exit(0);
    }
}