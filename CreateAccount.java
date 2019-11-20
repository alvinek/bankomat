import java.util.Scanner;

public class CreateAccount {

    public static Account CreateNewAccount() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Witamy w BETA Bank! Prosze zaloz swoje konto");
        System.out.println("UWAGA! Kazdy srednik (;) zostanie zamieniony na spacje!");

        System.out.println("Twoje imie: ");

        String name = scanner.nextLine();

        System.out.println("Twoje nazwisko: ");

        String surname = scanner.nextLine();

        System.out.println("Twoje haslo: ");

        String password = scanner.nextLine();

        name = name.replace(';', ' ');
        surname = surname.replace(';', ' ');
        password = password.replace(';', ' ');

        Account account = new Account(name, surname, password);

        System.out.println("Utworzono konto: ");

        System.out.println("Imie: " + account.userName);
        System.out.println("Nazwisko: " + account.userSurname);
        System.out.println("Haslo: ####");
        System.out.println("Numer konta: " + account.userAccountNumber);

        scanner.close();

        return account;
    }
}