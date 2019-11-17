import java.nio.file.Path;

class Main {
    static String fileDbName = "bank.txt";
    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        
        currentDir += "\\" + fileDbName;

        FileDriver fileDriver = new FileDriver(currentDir);

        if(fileDriver.checkFileExist())
        {
            
        }

    }
}