import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

            if (currentUser == null) {
                SignUpOrSignIn();
                continue;
            }

            if (userWantsExit) {
                scan.close();
                System.exit(0);
            }

            if (!weSaidHello && currentUser != null) {
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
                AccountInfo();
                break;
            case 5:
                currentUser = null;
                break;
            case 6:
                userWantsExit = true;
                break;
            default:
                System.out.println("Zla opcja. Wcisnij enter, by kontynuowac");
                scan.nextLine();
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
        default:
            System.out.println("Zla opcja. Wcisnij enter, by kontynuowac");
            scan.nextLine();
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
        System.out.println("4. Szczegoly konta");
        System.out.println("4. Wyloguj sie");
        System.out.println("5. Wyjscie");
    }

    public void AccountInfo() {
        System.out.println("ID: " + currentUser.userId);
        System.out.println("Imie: " + currentUser.userName);
        System.out.println("Nazwisko: " + currentUser.userSurname);
        System.out.println("Nr konta: " + currentUser.userAccountNumber);
        System.out.println("Stan konta: " + currentUser.userAccountBalance);
    }

    public void BankInternal() {
        System.out.println("ID uzytkownika, ktoremu chcesz zrobic przelew: ");

        String Id = "";
        if (scan.hasNextInt())
            Id = scan.nextLine().trim();

        System.out.println("Suma, jaka chcesz przelac: ");

        String moneyTemp = "";
        if (scan.hasNextDouble())
            moneyTemp = scan.nextLine();

        double money = Double.parseDouble(moneyTemp.replace(',', '.').trim());

        Account destination = null;

        for (Account acc : accountList) {
            if (acc.userId.equals(Id))
                destination = acc;
        }

        if (destination == null) {
            System.out.println("Nie odnaleziono uzytkownika o podanym ID: " + Id);
            return;
        }

        if (currentUser.userAccountBalance < money) {
            System.out.println(
                    "Nie masz wystarczajacych srodkow na koncie, twoje srodki: " + currentUser.userAccountBalance);
            return;
        }

        updateBalance(currentUser.userId, currentUser.getBalance() - money);
        updateBalance(destination.userId, destination.getBalance() + money);

        file.saveAccountsState(accountList);
    }

    public void BankExternal() {
        System.out.println("Jaka sume chcesz przelac?");

        String moneyStr = "";
        if(scan.hasNextDouble()) moneyStr = scan.nextLine();
        double money = Double.parseDouble(moneyStr);

        System.out.println("Numer konta:");

        String accountNo = scan.nextLine();
        accountNo = accountNo.replaceAll("\\s+", "").trim();

        String regex = "\\d{26}";
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(accountNo);

        if(m.matches())
        {
            updateBalance(currentUser.userId, currentUser.getBalance()-money);
            file.saveAccountsState(accountList);
            System.out.println("Srodki zostaly przelane");
        }
        else
        {
            System.out.println("Podano niepoprawny numer konta, srodki nie zostana przelane");
        }

    }

    public void ChargeAccount() {
        System.out.println("Jaka sume chcesz doladowac?:");

        String tempBalance = "";

        if (scan.hasNextDouble())
            tempBalance = scan.nextLine();

        double balanceCharge = Double.parseDouble(tempBalance.replace(",", "."));

        updateBalance(currentUser.userId, currentUser.getBalance() + balanceCharge);

        file.saveAccountsState(accountList);
    }

    public void updateBalance(String userId, double balance) {
        int index = 0;

        for (Account acc : accountList) {
            if (acc.userId.equals(userId))
                break;
            index++;
        }

        accountList.get(index).setBalance(balance);
    }
}