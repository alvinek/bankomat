import java.util.ArrayList;
import java.util.List;

public class Bank
{
    FileDriver file;
    List<Account> accountList = new ArrayList<Account>();

    public Bank(FileDriver file, List<Account> accounts)
    {
        this.file = file;
        this.accountList = accounts;
        BankMain();
    }

    public void BankMain()
    {

    }
}