import java.io.*;
import java.util.ArrayList;

    public class Costumer extends User {
        private double balance;
        private ArrayList<Product> shoppingCart = new ArrayList<>();

        public Costumer(String name, String userName, String password, String phoneNumber, String address, double balance) {
            super(name, userName, password, phoneNumber, address);
            this.balance = balance;
            this.shoppingCart = getShoppingCart();
        }

        public double getBalance() {
            return balance;
        }

        public ArrayList<Product> getShoppingCart() {
            return shoppingCart;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public void addToShoppingCart(Product product){
            shoppingCart.add(product);
        }

        public void writeInInfoFile(File usersInfoFile) throws IOException {
            FileWriter fileWriter = new FileWriter(usersInfoFile,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(super.getUserName() + "," + super.getName() + "," + super.getPassword() + "," + super.getPhoneNumber() + "," + super.getAddress() + "," + getBalance()+"\n");
            bufferedWriter.close();
        }
        //to adjust shoppingCart when user login again.
        public void adjustShoppingCart(File product, File shoppingCart) throws IOException {
            FileReader fileReader= new FileReader(shoppingCart);
            BufferedReader bufferedReader= new BufferedReader(fileReader);
            String currentLine;
            String[] info;
            String[] items=null;
            while ((currentLine=bufferedReader.readLine())!=null){
                info=currentLine.split(",");
                if(info[0].equals(super.getUserName()))
                    items=info;
            }
            bufferedReader.close();
            //error handling
            //start from one because at index one is userName
            if(items!=null) {
                for (int i = 1; i < items.length; i++) {
                    FileReader fileReader1 = new FileReader(product);
                    BufferedReader bufferedReader1 = new BufferedReader(fileReader);
                    while ((currentLine = bufferedReader1.readLine()) != null) {
                        info = currentLine.split(",");
                        if (info[0].equals(items[i])) {
                            this.shoppingCart.add(new Product(info[0], Double.parseDouble(info[2]), Integer.parseInt(info[3]), info[1],Integer.parseInt(info[4]),Double.parseDouble(info[5])));
                        }
                    }
                    bufferedReader1.close();
                }
            }
        }
        public Double totalPriceOfShoppingCart(){
            double totalPrice=0.0;
            for (int i = 0; i < shoppingCart.size(); i++) {
                totalPrice+= shoppingCart.get(i).getPrice();
            }
            return totalPrice;
        }
        public void writeInShoppingCartFile(File shoppingCartFile) throws IOException {
            if (Validator.isUserExistInShoppingCart(shoppingCartFile, super.getUserName())) {
                FileReader fileReader = new FileReader(shoppingCartFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String currentLine;
                String[] info;
                ArrayList<String> lines = new ArrayList<>();
                while ((currentLine = bufferedReader.readLine()) != null) {
                    info = currentLine.split(",");
                    if (info[0].equals(super.getUserName())) {
                        for (int i = 0; i < shoppingCart.size(); i++) {
                            currentLine+= shoppingCart.get(i).getName();
                        }
                        lines.add(super.getUserName()+currentLine);
                    }
                    else
                        lines.add(currentLine);
                }
                bufferedReader.close();
                FileWriter fileWriter = new FileWriter(shoppingCartFile, false);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                for (int i = 0; i < lines.size(); i++) {
                    bufferedWriter.write(lines.get(i)+"\n");
                }
                bufferedWriter.close();
            }
            else {
                FileWriter fileWriter = new FileWriter(shoppingCartFile, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                String info;
                info = super.getUserName();
                while (!shoppingCart.isEmpty()) {
                    for (int i = 0; i < shoppingCart.size(); i++) {
                        info = info + "," + shoppingCart.get(i).getName();
                    }
                }
                bufferedWriter.write(info+"\n");
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
                    bufferedWriter.write(lines.get(i)+"\n");
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
                        bufferedWriter.write(lines.get(i)+"\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                bufferedWriter.close();
            }
        }
    }