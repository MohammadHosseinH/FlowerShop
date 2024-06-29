import java.io.*;
import java.util.ArrayList;

public class Product {
    private String name;
    private double price;
    private int inventory;
    private String imagePath;
    public Product(String name, double price, int inventory, String imagePath) throws IOException {
        this.name = name;
        this.price = price;
        this.inventory = inventory;
        this.imagePath = imagePath;
    }
    public void addProductInFile(File products) throws IOException {
        FileWriter fileWriter = new FileWriter(products,true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(name + "," + imagePath + "," + price + "," + inventory + "\n");
        bufferedWriter.close();
    }

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
    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public void changeName(File products, String name) throws IOException {
        FileReader fileReader= new FileReader(products);
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String currentLine;
        this.name = name;
        ArrayList<String> lines= new ArrayList<>();
        while ((currentLine=bufferedReader.readLine())!=null){
            if (currentLine.startsWith(this.name)){
                String[] info=currentLine.split(",");
                lines.add(name+","+info[1]+","+info[2]+","+ info[3]);
            }
            else
                lines.add(currentLine);
        }
        bufferedReader.close();
        FileWriter fileWriter = new FileWriter(products, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i < lines.size(); i++) {
            bufferedWriter.write(lines.get(i)+"\n");
        }
        bufferedWriter.close();
    }
    public void changePrice(File products ,double price) throws IOException {
        FileReader fileReader= new FileReader(products);
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String currentLine;
        this.price=price;
        ArrayList<String> lines= new ArrayList<>();
        String[] info;
        while ((currentLine=bufferedReader.readLine())!=null){
            info=currentLine.split(",");
            if (info[2].equals(price)){
                lines.add(info[0]+","+info[1]+","+this.price+","+info[3]);
            }
            else
                lines.add(currentLine);
        }
        bufferedReader.close();
        FileWriter fileWriter = new FileWriter(products, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i < lines.size(); i++) {
            bufferedWriter.write(lines.get(i)+"\n");
        }
        bufferedWriter.close();
    }

    public void changeInventory(File products, int inventory) throws IOException {
        FileReader fileReader= new FileReader(products);
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String currentLine;
        this.inventory=inventory;
        ArrayList<String> lines= new ArrayList<>();
        String[] info;
        while ((currentLine=bufferedReader.readLine())!=null){
            info=currentLine.split(",");
            if (info[2].equals(price)){
                lines.add(info[0]+","+info[1]+","+info[2]+","+this.inventory);
            }
            else
                lines.add(currentLine);
        }
        bufferedReader.close();
        FileWriter fileWriter = new FileWriter(products, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i < lines.size(); i++) {
            bufferedWriter.write(lines.get(i)+"\n");
        }
        bufferedWriter.close();
    }
}
