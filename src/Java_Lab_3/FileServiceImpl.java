package Java_Lab_3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FileServiceImpl implements FileService {

    @Override
    public void receiveAllProducts(Storehouse storehouse, String path){
        try {
            ArrayList<ProductInfo> allProductsList = Files.readAllLines(Path.of(path), StandardCharsets.UTF_8)
                    .stream()
                    .map(line -> {
                        String[] product = line.split(",");
                        String productName = product[0].trim();
                        double productPrice = Double.parseDouble(product[1].trim());
                        int productQuantity = Integer.parseInt(product[2].trim());

                        return new ProductInfo(new Product(productName, productPrice), productQuantity);
                    })
                    .collect(Collectors.toCollection(ArrayList::new));

            storehouse.setAllProducts(allProductsList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void orderProducts(ProductInfo... args) {

    }

    @Override
    public void receiveProducts(Storehouse storehouse, ProductInfo... args) {

    }

    @Override
    public void sortByPrice() {

    }

    @Override
    public void printAllProducts(Storehouse storehouse) {
        storehouse.getAllProducts().forEach(p -> System.out.println(p.getProduct() +
                " " + p.getProduct().getPrice()
                + " \t quantity: " + "\t" + p.getQuantity()));
    }
}

