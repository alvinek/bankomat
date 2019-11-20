import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {
    FileDriver file;
    List<Account> accountList = new ArrayList<Account>();
    public Account currentUser;
    boolean weSaidHello = false;
    public Scanner scan;
    boolean userWantsExit = false;

    public Bank(FileDriver file, List<Account> accounts, Scanner scan) {
        this.file = file;
        this.accountList = accounts;
        this.scan = scan;
    }

    public void BankMain() {
        while (true) {
            if (userWantsExit) {
                scan.close();
                System.exit(0);
            }

            if (accountList.isEmpty())
                break;
            if (currentUser == null)
                SignUpOrSignIn();

            if (userWantsExit) {
                scan.close();
                System.exit(0);
            }

            if (!weSaidHello) {
                System.out.println("Witamy, " + currentUser.userName + " " + currentUser.userSurname);
                System.out.println("Stan konta: " + currentUser.userAccountBalance + " zl");
            }

            PrintMenu();

            String selectionString = "";

            if (scan.hasNextInt())
                selectionString = scan.nextLine(); // nie uzywam nextInt bo nie łyka entera
            
            
            int selectionInt = Integer.parseInt(selectionString); // zamiast tego upewniam się z pomocą nextInt że to
                                                                  // int, a potem parsuje

            switch (selectionInt) {
            case 1:
                BankInternal();
                break;
            case 2:
                BankExternal();
                break;
            case 3:
                ChargeAccount();
                break;
            case 4:
                currentUser = null;
                break;
            case 5:
                userWantsExit = true;
                break;
            }
        }

        scan.close();
        System.exit(0);
    }

    public void BankLogin() {
        currentUser = null;
        weSaidHello = false;

        while (true) {
            System.out.println("Podaj ID: ");
            String id = scan.nextLine();

            System.out.println("Podaj haslo: ");
            String password = scan.nextLine();

            for (Account acc : accountList) {
                if (acc.userId.equals(id) && acc.userPassword.equals(password)) {
                    currentUser = acc;
                    break;
                }
                if (acc.userId.equals(id) && !acc.userPassword.equals(password)) {
                    System.out.println("Zle haslo!");
                    break;
                }
            }
            if (currentUser != null)
                break;

            if (currentUser == null) {
                System.out.println("Niepowodzenie. Sprobuj ponownie");
                scan.nextLine();
                continue;
            }
        }
    }

    public void SignUpOrSignIn() {
        System.out.println("1. Zaloguj sie");
        System.out.println("2. Zarejestruj sie");
        System.out.println("3. Wyjscie");

        String userCaseString = "";

        if (scan.hasNextInt())
            userCaseString = scan.nextLine();

        int userCaseInt = Integer.parseInt(userCaseString);

        switch (userCaseInt) {
        case 1:
            BankLogin();
            break;
        case 2:
            BankSignUp();
            break;
        case 3:
            userWantsExit = true;
            break;
        }

    }

    public void BankSignUp() {
        Account newAccount = CreateAccount.CreateNewAccount(scan);
        file.saveAccount(newAccount);
        reloadAccounts();
    }

    public void reloadAccounts() {
        accountList = file.readAccounts();
    }

    public void PrintMenu() {
        System.out.println("1. Wykonaj przelew wewnetrzny");
        System.out.println("2. Wykonaj przelew zewnetrzny");
        System.out.println("3. Doladuj konto");
        System.out.println("4. Wyloguj sie");
        System.out.println("5. Wyjscie");
    }

    public void BankInternal() {

    }

    public void BankExternal() {

    }

    public void ChargeAccount() {

    }
}