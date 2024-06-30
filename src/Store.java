import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Store {
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Product> sortedProducts = new ArrayList<>();
    ArrayList<Costumer> costumers = new ArrayList<>();
    File userInfo = new File("userInfo.txt");
    File shoppingCartFile = new File("shoppingCart.txt");
    File productFile = new File("productsFile.txt");


    public void setUsersArray() throws IOException {
        FileReader fileReader =new FileReader(userInfo);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        int i = 0;
        while ((line = bufferedReader.readLine()) != null){
            String[] lines = line.split(",");
            Costumer tempUser = new Costumer(lines[1],lines[0],lines[2],lines[3],lines[4], Double.parseDouble(lines[5]));
            costumers.add(tempUser);
        }
        bufferedReader.close();
    }
    public void setProductsArray() throws IOException {
        FileReader fileReader =new FileReader(productFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        int i = 0;
        while ((line = bufferedReader.readLine()) != null){
            String[] lines = line.split(",");
            Product tempProduct = new Product(lines[0] , Double.parseDouble(lines[2]) , Integer.parseInt(lines[3]) , lines[1],Integer.parseInt(lines[4]),Double.parseDouble(lines[5]));
            products.add(tempProduct);
        }
        bufferedReader.close();
    }
    //bubble sort algorithm.
    public void sortingProductsByPrice(){
        sortedProducts = products;
        int n = sortedProducts.size();
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (sortedProducts.get(j).getPrice() > sortedProducts.get(maxIndex).getPrice()) {
                    maxIndex = j;
                }
            }
            // Swap arr[i] and arr[minIndex]
            Product temp = sortedProducts.get(i);
            sortedProducts.set(i,sortedProducts.get(maxIndex));
            sortedProducts.set(maxIndex,temp);
        }
    }
    public void sortingProductsByRate(){
        sortedProducts = products;
        int n = sortedProducts.size();
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (sortedProducts.get(j).getRate() > sortedProducts.get(maxIndex).getRate()) {
                    maxIndex = j;
                }
            }
            Product temp = sortedProducts.get(i);
            sortedProducts.set(i, sortedProducts.get(maxIndex));
            sortedProducts.set(maxIndex, temp);
        }
    }
}
