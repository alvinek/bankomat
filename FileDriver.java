import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileDriver
{
    private File file;
    private char seperator = ';';

    public FileDriver(String fileLocation)
    {
        file = new File(fileLocation);
    }

    public boolean checkFileExist()
    {
        try
        {
            if(file.createNewFile())
            {
                System.out.println("Utworzono nowy plik");
                return false;
            }
            else
            {
                return true;
            }
        }
        catch(IOException e)
        {
            System.out.println("Wystapil problem z wczytywaniem pliku: " + e);
            return false;
        }
    }

    public boolean saveAccount(Account account)
    {
        String constructString = 
        account.userId + seperator + 
        account.userName + seperator + 
        account.userSurname + seperator + 
        account.userPassword + seperator + 
        account.userAccountNumber + seperator + 
        account.userAccountBalance;

        boolean success = false;

        try(PrintStream out = new PrintStream(new FileOutputStream(file))){
            out.println(constructString);
            out.flush();
            out.close();
            success = true;
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Wystapil blad przy zapisywaniu");
            success = false;
        }

        return success;
    }

    public List<Account> readAccounts()
    {
        List<String> linesFromFile = readLines();
        List<Account> linesParsed = new ArrayList<Account>();

        for(String line : linesFromFile)
        {
            try
            {
                String[] split = line.split(String.valueOf(seperator));

                String userId = String.valueOf(split[0]);
                String userName = String.valueOf(split[1]);
                String userSurname = String.valueOf(split[2]);
                String userPassword = String.valueOf(split[3]);
                String userAccountNumber = String.valueOf(split[4]);
                double balance = Double.parseDouble(split[5]);

                Account account = new Account(userId, userName, userSurname, userPassword, userAccountNumber, balance);
                linesParsed.add(account);
            }
            catch(Exception e)
            {
                System.out.println("Wystapil blad przy wczytywaniu danych z pliku: " + e);
            }
        }

        return linesParsed;
    }

    public List<String> readLines()
    {
        try(Stream<String> lines = Files.lines(file.toPath())) {
            return lines.collect(Collectors.toList());
        }
        catch(IOException e)
        {
            System.out.println("Nie udalo sie wczytac pliku: " + e);
        }
        return null;
    }

    public File getFile()
    {
        return file;
    }
    
}