package Java_Lab_3;

public interface FileService {
    void receiveAllProducts(Storehouse storehouse, String path);

    void printAllProducts(Storehouse storehouse);

    void orderProducts(Storehouse storehouse,String path, ProductInfo... args);

    void receiveProducts(Storehouse storehouse, String path, ProductInfo... args);

    void sortByPrice(String path);
    
}
