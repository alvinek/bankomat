import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank
{
    FileDriver file;
    List<Account> accountList = new ArrayList<Account>();
    public Account currentUser;
    boolean weSaidHello = false;
    public Scanner scan;


    public Bank(FileDriver file, List<Account> accounts, Scanner scan)
    {
        this.file = file;
        this.accountList = accounts;
        this.scan = scan;
    }

    public void BankMain()
    {
        if(currentUser == null) BankLogin();

        if(!weSaidHello)
        {
            System.out.println("Witamy, " + currentUser.userName + " " + currentUser.userSurname);
            System.out.println("Stan konta: " + currentUser.userAccountBalance + " zl");
        }

        PrintMenu();

        String selectionString = "";

        if(scan.hasNextInt())
            selectionString = scan.nextLine(); // nie uzywam nextInt bo nie łyka entera

        int selectionInt = Integer.parseInt(selectionString); // zamiast tego upewniam się z pomocą nextInt że to int, a potem parsuje

        switch(selectionInt)
        {
            case 1:
                BankInternal();
                break;
            case 2:
                BankExternal();
                break;
            case 3:
                ChargeAccount();
                break;
        }
    }

    public void BankLogin()
    {
        currentUser = null;
        weSaidHello = false;

        while(true)
        {
            System.out.println("Podaj ID: ");
            String id = scan.next();

            System.out.println("Podaj haslo: ");
            String password = scan.next();

            for(Account acc : accountList)
            {
                if(acc.userId.equals(id) && acc.userPassword.equals(password))
                {
                    currentUser = acc;
                    break;
                }
                if(acc.userId.equals(id) && !acc.userPassword.equals(password))
                {
                    System.out.println("Zle haslo!");
                    break;
                }
            }
            if(currentUser != null) break;

            if(currentUser == null) 
            {
                System.out.println("Niepowodzenie. Sprobuj ponownie");
                scan.nextLine();
                continue;
            }
        }
    }

    public void PrintMenu()
    {
        System.out.println("1. Wykonaj przelew wewnetrzny");
        System.out.println("2. Wykonaj przelew zewnetrzny");
        System.out.println("3. Doladuj konto");
    }

    public void BankInternal()
    {

    }

    public void BankExternal()
    {

    }

    public void ChargeAccount()
    {

    }
}