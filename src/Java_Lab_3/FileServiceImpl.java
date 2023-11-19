package Java_Lab_3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public void orderProducts(Storehouse storehouse, String path, ProductInfo... args) {
        try {
            ArrayList<ProductInfo> allProducts = Files.lines(Paths.get(path), StandardCharsets.UTF_8)
                    .map(line -> {
                        String[] product =  line.split(",");
                        String productName = product[0].trim();
                        String productPrice = product[1].trim();
                        String productQuantity = product[2].trim();
                        return new ProductInfo(new Product(productName, Double.parseDouble(productPrice)), Integer.parseInt(productQuantity));
                    })
                    .collect(Collectors.toCollection(ArrayList::new));
            for (ProductInfo arg : args) {
                allProducts.stream()
                        .filter(productInfo -> productInfo.getProduct().getName().equals(arg.getProduct().getName()))
                        .forEach(productInfo -> {
                            productInfo.setQuantity(arg.getQuantity()); // Встановлюємо нову кількість продукту
                        });
            }
            Files.write(Paths.get(path), allProducts.stream()
                    .map(productInfo -> productInfo.getProduct().getName() + ", " + productInfo.getProduct().getPrice() + ", " + productInfo.getQuantity()) // Записуємо стару ціну продукту та нову кількість
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void receiveProducts(Storehouse storehouse, String path, ProductInfo... args) {
        try {
            ArrayList<ProductInfo> allProducts = Files.lines(Paths.get(path), StandardCharsets.UTF_8)
                    .map(line -> {
                        String[] product =  line.split(",");
                        String productName = product[0].trim();
                        String productPrice = product[1].trim();
                        String productQuantity = product[2].trim();
                        return new ProductInfo(new Product(productName, Double.parseDouble(productPrice)), Integer.parseInt(productQuantity));
                    })
                    .collect(Collectors.toCollection(ArrayList::new));
            for (ProductInfo arg : args) {
                allProducts.stream()
                        .filter(productInfo -> productInfo.getProduct().getName().equals(arg.getProduct().getName()))
                        .forEach(productInfo -> {
                            ProductInfo storeProductInfo = storehouse.getProductInfo(productInfo.getProduct().getName()); // Отримуємо ProductInfo з магазину
                            storeProductInfo.setQuantity(storeProductInfo.getQuantity() + arg.getQuantity()); // Додаємо нову кількість продукту до існуючої кількості
                        });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sortByPrice(String path) {
        try {
            ArrayList<ProductInfo> allProducts = Files.lines(Paths.get(path), StandardCharsets.UTF_8)
                    .map(line -> {
                        String[] product =  line.split(",");
                        String productName = product[0].trim();
                        String productPrice = product[1].trim();
                        String productQuantity = product[2].trim();
                        return new ProductInfo(new Product(productName, Double.parseDouble(productPrice)), Integer.parseInt(productQuantity));
                    })
                    .collect(Collectors.toCollection(ArrayList::new));
            allProducts.sort((productInfo1, productInfo2) -> {
                if (productInfo1.getProduct().getPrice() > productInfo2.getProduct().getPrice()) {
                    return 1;
                } else if (productInfo1.getProduct().getPrice() < productInfo2.getProduct().getPrice()) {
                    return -1;
                } else {
                    return 0;
                }
            });
            Files.write(Paths.get(path), allProducts.stream()
                    .map(productInfo -> productInfo.getProduct().getName() + ", " + productInfo.getProduct().getPrice() + ", " + productInfo.getQuantity())
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void printAllProducts(Storehouse storehouse) {
        storehouse.getAllProducts().forEach(p -> System.out.println(p.getProduct() +
                " " + p.getProduct().getPrice()
                + " \t quantity: " + "\t" + p.getQuantity()));
    }


}

