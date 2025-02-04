import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
//This is the super class of our GUI classes
public class ShopGUI extends JFrame implements ActionListener {
    //Fields and Components
    Product currentProduct;
    ArrayList<JButton> productButtonsList = new ArrayList<>();
    ArrayList<JButton> shoppingCartButtonsList = new ArrayList<>();
    Store myStore = new Store();
    Manager manager = new Manager("Admin", "admin1", "hello", "09126027358", "Tehran", 0);
    Costumer costumer = new Costumer(null, null, null, null, null, 0);
    Font font = new Font("Adobe Arabic", Font.PLAIN, 18);
    JButton costumerLoginButton = new JButton("ورود کاربر");
    JButton costumerSignupButton = new JButton("ثبت نام کاربر");
    JButton managerLoginButton = new JButton("ورود مدیر");
    JTextField nameField = new JTextField();
    JTextField userNameField = new JTextField();
    JTextField passwordField = new JTextField();
    JTextField phoneNumberField = new JTextField();
    JTextField addressField = new JTextField();
    JButton confirmSignup = new JButton("ثبت نام");
    JButton confirmCostumerLogin = new JButton("ورود");
    JButton confirmManagerLogin = new JButton("ورود");
    Color backGroundColor = new Color(192, 192, 192);
    Color borderColor = new Color(72, 61, 139);

    JButton backButton = new JButton("بازگشت");
    JButton productButton = new JButton();


    ShopGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("گل فروشی");
        this.setSize(500, 600);
        this.setVisible(true);
        this.setResizable(false);

        welcomePage();
    }

    // Pre-login and Pre-signup page
    public void welcomePage() {
        try {
            myStore.setProductsArray();
            myStore.setUsersArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JPanel menuPanel = new JPanel(null);
        JLabel welcomeLabel = new JLabel("خوش آمدید");
        this.add(menuPanel);
        menuPanel.add(costumerLoginButton);
        menuPanel.add(costumerSignupButton);
        menuPanel.add(managerLoginButton);
        menuPanel.add(welcomeLabel);
        welcomeLabel.setFont(font);
        costumerLoginButton.setFont(font);
        costumerSignupButton.setFont(font);
        managerLoginButton.setFont(font);

        menuPanel.setBackground(backGroundColor);
        costumerLoginButton.setBackground(Color.WHITE);
        costumerLoginButton.setBorder(BorderFactory.createLineBorder(borderColor));
        costumerSignupButton.setBackground(Color.WHITE);
        costumerSignupButton.setBorder(BorderFactory.createLineBorder(borderColor));
        managerLoginButton.setBackground(Color.WHITE);
        managerLoginButton.setBorder(BorderFactory.createLineBorder(borderColor));


        welcomeLabel.setBounds(220, 100, 100, 30);
        costumerSignupButton.setBounds(175, 200, 150, 40);
        costumerLoginButton.setBounds(175, 300, 150, 40);
        managerLoginButton.setBounds(175, 400, 150, 40);

        costumerLoginButton.addActionListener(this);
        costumerSignupButton.addActionListener(this);
        managerLoginButton.addActionListener(this);


    }

    public void costumerSignupPage() {
        JPanel signupPanel = new JPanel(null);
        signupPanel.setBackground(backGroundColor);

        JLabel nameLabel = new JLabel("نام:");
        nameLabel.setFont(font);
        nameLabel.setBounds(400, 50, 100, 100);
        signupPanel.add(nameLabel);
        JLabel userNameLabel = new JLabel("نام کاربری:");
        userNameLabel.setFont(font);
        userNameLabel.setBounds(370, 120, 100, 100);
        signupPanel.add(userNameLabel);
        JLabel passwordLabel = new JLabel("رمز عبور:");
        passwordLabel.setFont(font);
        passwordLabel.setBounds(380, 190, 100, 100);
        signupPanel.add(passwordLabel);
        JLabel phoneNumberLabel = new JLabel("شماره تلفن:");
        phoneNumberLabel.setFont(font);
        phoneNumberLabel.setBounds(370, 260, 100, 100);
        signupPanel.add(phoneNumberLabel);
        JLabel addressLabel = new JLabel("نشانی منزل");
        addressLabel.setFont(font);
        addressLabel.setBounds(370, 330, 100, 100);
        signupPanel.add(addressLabel);

        nameField.setBounds(100, 90, 220, 30);
        signupPanel.add(nameField);
        userNameField.setBounds(100, 160, 220, 30);
        signupPanel.add(userNameField);
        passwordField.setBounds(100, 230, 220, 30);
        signupPanel.add(passwordField);
        phoneNumberField.setBounds(100, 300, 220, 30);
        signupPanel.add(phoneNumberField);
        addressField.setBounds(100, 370, 220, 30);
        signupPanel.add(addressField);

        confirmSignup.setBounds(200, 450, 100, 50);
        confirmSignup.setBackground(Color.WHITE);
        confirmSignup.setBorder(BorderFactory.createLineBorder(new Color(72, 61, 139)));

        confirmSignup.addActionListener(this);

        signupPanel.add(confirmSignup);
        this.getContentPane().removeAll();
        this.add(signupPanel);
        this.repaint();
        this.revalidate();

    }

    public void costumerSignup() throws IOException {
        String name = nameField.getText();
        String userName = userNameField.getText();
        String passWord = passwordField.getText();
        String phoneNumber = phoneNumberField.getText();
        String address = addressField.getText();
        if ((!Validator.isUserExist(myStore.userInfo, userName)) && Validator.validatePassword(passWord) && Validator.checkPhoneNumber(phoneNumber)) {
            costumer = new Costumer(userName, name, passWord, phoneNumber, address, 0);
            costumer.writeInInfoFile(myStore.userInfo);
            this.dispose();
            new CostumerGUI();
        } else
            showMessages(" این نام کاربری توسط کاربر دیگری انتخاب شده است." + "<br>" +
                    " یا طول رمز عبور باید بیشتر از هفت باشد و شامل حروف، عدد و کارکتر باشد. " + "<br>" +
                    " یا شماره ی تلفن وارد شده اشتباه است.");

    }

    public void costumerLoginPage() {
        JPanel loginPanel = new JPanel(null);
        JLabel userNameLabel = new JLabel("نام کاربری:");
        loginPanel.setBackground(backGroundColor);
        userNameLabel.setFont(font);
        userNameLabel.setBounds(370, 200, 100, 100);
        loginPanel.add(userNameLabel);
        JLabel passwordLabel = new JLabel("رمز عبور:");
        passwordLabel.setFont(font);
        passwordLabel.setBounds(380, 280, 100, 100);
        loginPanel.add(passwordLabel);

        userNameField.setBounds(100, 240, 220, 30);
        loginPanel.add(userNameField);
        passwordField.setBounds(100, 320, 220, 30);
        loginPanel.add(passwordField);

        confirmCostumerLogin.setBounds(200, 450, 100, 50);
        confirmCostumerLogin.setBackground(Color.WHITE);
        confirmCostumerLogin.setBorder(BorderFactory.createLineBorder(new Color(72, 61, 139)));
        loginPanel.add(confirmCostumerLogin);
        confirmCostumerLogin.addActionListener(this);

        this.getContentPane().removeAll();
        this.add(loginPanel);
        this.repaint();
        this.revalidate();


    }

    public void costumerLogin() throws IOException {
        String userName = userNameField.getText();
        String password = passwordField.getText();
        if (Validator.logInCheck(myStore.userInfo, userName, password)) {
            String[] info = showInfo(myStore.userInfo, userName);
            costumer = new Costumer(info[0], info[1], info[2], info[3], info[4], Double.parseDouble(info[5]));
            this.dispose();
            new CostumerGUI();
        } else
            showMessages("رمز عبور یا نام کاربری اشتباه است.");
    }

    //this method is for getting info for specific user from file
    public String[] showInfo(File infoFile, String userName) throws IOException {
        FileReader fileReader = new FileReader(infoFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String currentLine;
        String[] info;
        while ((currentLine = bufferedReader.readLine()) != null) {
            info = currentLine.split(",");
            if (info[0].equals(userName))
                return info;
        }
        bufferedReader.close();
        return null;
    }

    public void managerLoginPage() {
        JPanel loginPanel = new JPanel(null);
        loginPanel.setBackground(backGroundColor);
        JLabel userNameLabel = new JLabel("نام کاربری:");
        userNameLabel.setFont(font);
        userNameLabel.setBounds(370, 200, 100, 100);
        loginPanel.add(userNameLabel);
        JLabel passwordLabel = new JLabel("رمز عبور:");
        passwordLabel.setFont(font);
        passwordLabel.setBounds(380, 280, 100, 100);
        loginPanel.add(passwordLabel);

        userNameField.setBounds(100, 240, 220, 30);
        loginPanel.add(userNameField);
        passwordField.setBounds(100, 320, 220, 30);
        loginPanel.add(passwordField);

        confirmManagerLogin.setBounds(200, 450, 100, 50);
        confirmManagerLogin.setBackground(Color.WHITE);
        confirmManagerLogin.setBorder(BorderFactory.createLineBorder(new Color(72, 61, 139)));
        loginPanel.add(confirmManagerLogin);
        confirmManagerLogin.addActionListener(this);

        this.getContentPane().removeAll();
        this.add(loginPanel);
        this.repaint();
        this.revalidate();
    }

    public void managerLogin() {
        if (userNameField.getText().equals(manager.getUserName()) && passwordField.getText().equals(manager.getPassword())) {
            this.dispose();
            new ManagerGUI();
        } else {
            showMessages("رمز عبور یا نام کاربری اشتباه است.");
        }
    }

    public void showMessages(String text){
        JFrame errorFrame= new JFrame("Message");
        errorFrame.setSize(500,250);
        //used GridBagLayout to show label in center
        JPanel errorPanel= new JPanel(new GridBagLayout());
        errorPanel.setBackground(Color.WHITE);
        JLabel showError= new JLabel("<html>" + text + "</html>");
        showError.setFont(font);
        errorPanel.add(showError);
        errorFrame.add(errorPanel);
        errorFrame.setVisible(true);
        errorFrame.setResizable(false);
    }
    public void resetProductInventory() throws IOException {
        ArrayList<Product> items = costumer.getShoppingCart();
        for (int i = 0; i < items.size(); i++){
            items.get(i).changeInventory(myStore.productFile, items.get(i).getInventory()-1);
        }
    }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == costumerLoginButton){
                costumerLoginPage();
            }
            else if (e.getSource() == costumerSignupButton){
                costumerSignupPage();
            }
            else if(e.getSource() == managerLoginButton){
                managerLoginPage();
            }
            else if (e.getSource() == confirmSignup){
                try {
                    costumerSignup();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else if (e.getSource() == confirmCostumerLogin) {
                try {
                    costumerLogin();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else if (e.getSource() == confirmManagerLogin){
                managerLogin();
            }
        }
    }

