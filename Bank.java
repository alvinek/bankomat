import java.util.ArrayList;
import java.util.List;

public class Bank
{
    FileDriver file;
    List<Account> accountList = new ArrayList<Account>();

    public Bank(FileDriver file)
    {
        this.file = file;
        BankMain();
    }

    public void BankMain()
    {

    }
}