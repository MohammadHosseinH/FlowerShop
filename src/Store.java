import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Store {
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Costumer> costumers = new ArrayList<>();
    File userInfo = new File("userInfo.txt");
    File shoppingCart = new File("shoppingCart.txt");
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
            Product tempProduct = new Product(lines[0] , Double.parseDouble(lines[2]) , Integer.parseInt(lines[3]) , lines[1]);
            products.add(tempProduct);
        }
        bufferedReader.close();
    }
}
