import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

    public class Costumer extends User {
        private double balance;
        private Stack<Product> shoppingCart;

        public Costumer(String name, String userName, String password, String phoneNumber, String address, double balance) {
            super(name, userName, password, phoneNumber, address);
            this.balance = balance;
            this.shoppingCart = new Stack<>();
        }

        public double getBalance() {
            return balance;
        }

        public Stack<Product> getShoppingCart() {
            return shoppingCart;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public void addToShoppingCart(Product product) {
            shoppingCart.push(product);
        }

        public void writeInInfoFile(File usersInfoFile) throws IOException {
            FileWriter fileWriter = new FileWriter(usersInfoFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(super.getUserName() + "," + super.getName() + "," + super.getPassword() + "," + super.getPhoneNumber() + "," + super.getAddress() + "," + getBalance());
            bufferedWriter.close();
        }

        public void writeInShoppingCartFile(File shoppingCartFile, Stack<Product> product) throws IOException {
            if (Validator.isUserExistInShoppingCart(shoppingCartFile, super.getUserName())) {
                FileReader fileReader = new FileReader(shoppingCartFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String currentLine;
                String[] info;
                ArrayList<String> lines = new ArrayList<>();
                while ((currentLine = bufferedReader.readLine()) != null) {
                    info = currentLine.split(",");
                    if (info[0].equals(super.getUserName())) {
                        String temp = currentLine.substring(info[0].length() + 1);
                        while (product.isEmpty()) {
                            currentLine = currentLine + "," + product.peek().getName();
                        }
                        currentLine = super.getUserName() + "," + currentLine + "," + temp;
                    } else
                        lines.add(currentLine);
                }
                bufferedReader.close();
                    FileWriter fileWriter = new FileWriter(shoppingCartFile, false);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    for (int i = 0; i < lines.size(); i++) {
                        bufferedWriter.write(lines.get(i));
                    }
                    bufferedWriter.close();
            } else {
                FileWriter fileWriter = new FileWriter(shoppingCartFile, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                String info;
                info = super.getUserName();
                while (!shoppingCart.isEmpty()) {
                    info = info + "," + shoppingCart.peek().getName();
                }
                bufferedWriter.write(info);
                bufferedWriter.close();
            }
        }

        public void changePassword(File userInfo, String newPassword) throws IOException {
            super.setPassword(newPassword);
            FileReader fileReader = new FileReader(userInfo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String currentLine;
            String[] info;
            ArrayList<String> lines = new ArrayList<>();
            String temp;
            while ((currentLine = bufferedReader.readLine()) != null) {
                info = currentLine.split(",");
                if (info[0].equals(super.getUserName())) {
                    temp = info[0] + "," + info[1] + "," + newPassword + "," + info[3] + "," + info[4] + "," + info[5];
                    lines.add(temp);
                } else {
                    lines.add(currentLine);
                }
                bufferedReader.close();
                FileWriter fileWriter = new FileWriter(userInfo, false);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                for (int i = 0; i < lines.size(); i++) {
                    bufferedWriter.write(lines.get(i));
                }
                bufferedWriter.close();
            }
        }

        public void changeBalance(File userInfo, double addingBalance) throws IOException {
            setBalance(getBalance() + addingBalance);
            FileReader fileReader = new FileReader(userInfo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String currentLine;
            String[] info;
            ArrayList<String> lines = new ArrayList<>();
            String temp;
            while ((currentLine = bufferedReader.readLine()) != null) {
                info = currentLine.split(",");
                if (info[0].equals(super.getUserName())) {
                    temp = info[0] + "," + info[1] + "," + info[2] + "," + info[3] + "," + info[4] + "," + getBalance();
                    lines.add(temp);
                } else {
                    lines.add(currentLine);
                }
                bufferedReader.close();
                FileWriter fileWriter = new FileWriter(userInfo, false);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                for (int i = 0; i < lines.size(); i++) {
                    try {
                        bufferedWriter.write(lines.get(i));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                bufferedWriter.close();
            }
        }
    }
//  public double getPriceOfShoppingCart(){

// }




   /* public ArrayList<String> getShoppingCart(File shoppingCart, String userName) throws IOException {
        FileReader fileReader= new FileReader(shoppingCart);
        BufferedReader bufferedReader= new BufferedReader(fileReader);
        String currentLine;
        String[] info;
        ArrayList<String> wantedShoppingCart= new ArrayList<>();
        while ((currentLine=bufferedReader.readLine())!=null){
            info=currentLine.split(",");
            if(info[0].equals(userName)){
                for (int i = 1; i <info.length ; i++) {
                    wantedShoppingCart.add(info[i]);
                }
                return wantedShoppingCart;
            }
        }
    }*/