package Java_Lab_3;
import java.io.*;


public class FileService implements FileServiceAction{

    BufferedReader read;

    {
        try {
            read = new BufferedReader(new FileReader("warehouse.txt"));
        } catch (IOException e) {
            e.getMessage();
        }
    }

    @Override
    public void receiveOrder() {

    }

    @Override
    public void createOder() {

    }
}

