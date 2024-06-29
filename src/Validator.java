import java.io.*;

public class Validator {
    public static boolean isUserExist(File usersInfo , String userName) throws IOException {
        FileReader fileReader = new FileReader(usersInfo);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null){
            //information in this file is written in the following order
            //userName, name, password, phoneNumber, address, balance
            String[] info = line.split(",");
            if (info[0].equals(userName)){
                return true;
            }
        }
        bufferedReader.close();
        return false;
    }

    public static boolean isUserExistInShoppingCart(File shoppingCartFile, String userName) throws IOException {
        FileReader fileReader = new FileReader(shoppingCartFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null){
            if (line.startsWith(userName)){
                return true;
            }
        }
        bufferedReader.close();
        return false;
    }

    public static boolean isProductExist(File productsInfo, String name) throws IOException {
        FileReader fileReader = new FileReader(productsInfo);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null){
            //information in this file is written in the following order
            //name, imagePath, price, inventory
            String[] info = line.split(",");
            if (info[0].equals(name)){
                return true;
            }
        }
        bufferedReader.close();
        return false;
    }

    public static boolean checkPhoneNumber(String phoneNumber) throws IOException {
        if (!phoneNumber.startsWith("09")){
            return false;
        }
        if (phoneNumber.length() != 11){
            return false;
        }
        char[] numbers = phoneNumber.toCharArray();
        for (int i = 0; i < numbers.length; i++) {
            if (!Character.isDigit(numbers[i])){
                return false;
            }
        }
        return true;
    }

    public static boolean validatePassword(String password){

        char[] numbers = password.toCharArray();
        for (int i = 0; i < numbers.length; i++) {
            if (Character.isDigit(numbers[i])){
                break;
            }
            if (i == numbers.length - 1){
                return false;
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            if (Character.isLetter(numbers[i])){
                break;
            }
            if (i == numbers.length - 1){
                return false;
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == '@' || numbers[i] == '&' || numbers[i] == '*' || numbers[i] == '-' || numbers[i] == '.' || numbers[i] == '_'){
                break;
            }
            if (i == numbers.length - 1){
                return false;
            }
        }

        if (password.length() < 8){
            return false;
        }

        return true;
    }

    public static boolean logInCheck(File usersInfo, String userName,String password) throws IOException {
        FileReader fileReader = new FileReader(usersInfo);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null){
            //information in this file is written in the following order
            //userName, name, password, phoneNumber, address, balance
            String[] info = line.split(",");
            if (info[0].equals(userName)){
                if (info[2].equals(password)){
                    return true;
                }
            }
        }
        bufferedReader.close();
        return false;
    }
}
