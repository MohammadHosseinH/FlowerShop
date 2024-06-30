import java.io.*;
import java.util.ArrayList;

public class Product {
    private String name;
    private double price;
    private int inventory;
    private String imagePath;
    private int numberOfVotes;
    private double rate;
    public Product(String name, double price, int inventory, String imagePath, int numberOfVotes, double rate) throws IOException {
        this.name = name;
        this.price = price;
        this.inventory = inventory;
        this.imagePath = imagePath;
        this.numberOfVotes=numberOfVotes;
        this.rate=rate;
    }
    public void addProductInFile(File products) throws IOException {
        FileWriter fileWriter = new FileWriter(products,true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(name + "," + imagePath + "," + price + "," + inventory+","+ numberOfVotes+","+rate + "\n");
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
    public double getRate() {
        return rate;
    }
    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }
    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }
    //this method helps the manager to change product's name in program and file.
    public void changeName(File products, String name) throws IOException {
        FileReader fileReader= new FileReader(products);
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String currentLine;
        ArrayList<String> lines= new ArrayList<>();
        while ((currentLine=bufferedReader.readLine())!=null){
            if (currentLine.startsWith(this.name)){
                String[] info=currentLine.split(",");
                lines.add(name+","+info[1]+","+info[2]+","+ info[3]+","+info[4]+","+info[5]);
            }
            else
                lines.add(currentLine);
        }
        this.name = name;
        bufferedReader.close();
        FileWriter fileWriter = new FileWriter(products, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i < lines.size(); i++) {
            bufferedWriter.write(lines.get(i)+"\n");
        }
        bufferedWriter.close();
    }
    //this method helps the manager to change product's price in program and file.
    public void changePrice(File products ,double price) throws IOException {
        FileReader fileReader= new FileReader(products);
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String currentLine;
        this.price=price;
        ArrayList<String> lines= new ArrayList<>();
        String[] info;
        while ((currentLine=bufferedReader.readLine())!=null){
            info=currentLine.split(",");
            if (info[0].equals(name)){
                lines.add(info[0]+","+info[1]+","+this.price+","+info[3]+","+ info[4]+","+info[5]);
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
    //this method helps the manager to change product's inventory in program and file.
    //it also reduces product inventory when a costumer buys it.
    public void changeInventory(File products, int inventory) throws IOException {
        FileReader fileReader= new FileReader(products);
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String currentLine;
        this.inventory=inventory;
        ArrayList<String> lines= new ArrayList<>();
        String[] info;
        while ((currentLine=bufferedReader.readLine())!=null){
            info=currentLine.split(",");
            if (info[0].equals(name)){
                lines.add(info[0]+","+info[1]+","+info[2]+","+this.inventory+","+info[4]+","+info[5]);
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
    //this method change numberOfVotes and rate in the program and file when a costumer votes.
    public void adjustRate(File products,double newVote) throws IOException {
        this.numberOfVotes=this.numberOfVotes+1;
        this.rate=((this.rate*(this.numberOfVotes - 1))+newVote)/(double) this.numberOfVotes;
        FileReader fileReader= new FileReader(products);
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String currentLine;
        ArrayList<String> lines= new ArrayList<>();
        String[] info;
        while ((currentLine=bufferedReader.readLine())!=null){
            info=currentLine.split(",");
            if (info[0].equals(name)){
                lines.add(info[0]+","+info[1]+","+info[2]+","+info[3]+","+this.numberOfVotes+","+this.rate);
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
