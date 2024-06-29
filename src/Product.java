import java.io.*;

public class Product {
    private String name;
    private double price;
    private int inventory;
    private String imagePath;
//    ArrayList<String> productsList = new ArrayList<>();
    public Product(String name, double price, int inventory, String imagePath) throws IOException {
        this.name = name;
        this.price = price;
        this.inventory = inventory;
        this.imagePath = imagePath;
    }
    public void addProductInFile(File products) throws IOException {
        FileWriter fileWriter = new FileWriter(products,true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(name + "," + imagePath + "," + price + "," + inventory);
        ShopGUI.arrangeProductArray(products).add(name + "," + imagePath + "," + price + "," + inventory);
        bufferedWriter.close();
    }

//    public ArrayList<String> setProductArray(File products) throws IOException {
//        FileReader fileReader =new FileReader(products);
//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//        String line;
//        int i = 0;
//        while ((line = bufferedReader.readLine()) != null){
//            productsList.set(i, line);
//            i++;
//        }
//        bufferedReader.close();
//        return productsList;
//    }

    public String getImagePath() {
        return imagePath;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getInventory() {
        return inventory;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public void changeName(File products, String name) throws IOException {
        for (int i = 0; i < ShopGUI.arrangeProductArray(products).size(); i++) {
            if (ShopGUI.arrangeProductArray(products).get(i).startsWith(this.name)){
                this.name = name;
                ShopGUI.arrangeProductArray(products).set(i , this.name + "," + imagePath + "," + price + "," + inventory);
                break;
            }
        }
        FileWriter fileWriter = new FileWriter(products, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i < ShopGUI.arrangeProductArray(products).size(); i++) {
            bufferedWriter.write(ShopGUI.arrangeProductArray(products).get(i));
        }
        bufferedWriter.close();
    }

    public void changePrice(File products ,double price) throws IOException {
        for (int i = 0; i < ShopGUI.arrangeProductArray(products).size(); i++) {
            if (ShopGUI.arrangeProductArray(products).get(i).startsWith(this.name)){
                this.price = price;
                ShopGUI.arrangeProductArray(products).set(i , name + "," + imagePath + "," + price + "," + inventory);
                break;
            }
        }
        FileWriter fileWriter = new FileWriter(products, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i < ShopGUI.arrangeProductArray(products).size(); i++) {
            bufferedWriter.write(ShopGUI.arrangeProductArray(products).get(i));
        }
        bufferedWriter.close();
    }

    public void setInventory(File products, int inventory) throws IOException {
        for (int i = 0; i < ShopGUI.arrangeProductArray(products).size(); i++) {
            if (ShopGUI.arrangeProductArray(products).get(i).startsWith(this.name)){
                this.inventory = inventory;
                ShopGUI.arrangeProductArray(products).set(i , name + "," + imagePath + "," + price + "," + inventory);
                break;
            }
        }
        FileWriter fileWriter = new FileWriter(products, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i < ShopGUI.arrangeProductArray(products).size(); i++) {
            bufferedWriter.write(ShopGUI.arrangeProductArray(products).get(i));
        }
        bufferedWriter.close();
    }
}
