import java.io.File;

public class FileDriver
{
    private File file;

    public FileDriver(String fileLocation)
    {
        file = new File(fileLocation);
    }

    public boolean checkFileExist()
    {
        return file.exists();
    }
}