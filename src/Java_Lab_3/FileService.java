package Java_Lab_3;
import java.io.*;


public class FileService implements FileServiceAction{

    BufferedReader read;

    @Override
    public void receiveOrder() {
        {
            try {
                read = new BufferedReader(new FileReader("warehouse.txt"));




                read.close();
            } catch (IOException e) {
                e.getMessage();
            }
        }

    }

    @Override
    public void createOder() {

    }
}

