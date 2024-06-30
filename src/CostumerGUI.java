import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CostumerGUI extends ShopGUI implements ActionListener {
    //Fields and Components
    JButton showProductsButton = new JButton("نمایش محصولات");
    JButton searchPageButton = new JButton("جستجو محصولات");
    JButton profilePageButton = new JButton("پروفایل");
    JButton shoppingCartButton = new JButton("سبد خرید");
    JButton searchButton = new JButton("جستجو");
    JTextField searchField = new JTextField();
    JButton addToShoppingCartButton = new JButton("افزودن به سبد خرید");
    JButton changePassword = new JButton("تغییر رمز");
    JButton increaseBalance = new JButton("افزایش موجودی");
    JButton confirmNewPassword = new JButton("ثبت");
    JTextField newPassword = new JTextField();
    JTextField currentPassword = new JTextField();
    JTextField addingBalance = new JTextField();
    JButton confirmDeposit = new JButton("ثبت");
    JButton exit = new JButton("خروج");


    CostumerGUI(){
        menuPage();
    }

    public void menuPage(){
        JPanel menuPanel = new JPanel(null);
        menuPanel.setBackground(backGroundColor);
        showProductsButton.setFont(font);
        showProductsButton.setBounds(150,100,200,40);
        showProductsButton.setBackground(Color.WHITE);
        showProductsButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        menuPanel.add(showProductsButton);
        searchPageButton.setFont(font);
        searchPageButton.setBounds(150,200,200,40);
        searchPageButton.setBackground(Color.WHITE);
        searchPageButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        menuPanel.add(searchPageButton);
        profilePageButton.setFont(font);
        profilePageButton.setBounds(150,300,200,40);
        profilePageButton.setBackground(Color.WHITE);
        profilePageButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        menuPanel.add(profilePageButton);
        shoppingCartButton.setFont(font);
        shoppingCartButton.setBounds(150,400,200,40);
        shoppingCartButton.setBackground(Color.WHITE);
        shoppingCartButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        menuPanel.add(shoppingCartButton);

        showProductsButton.addActionListener(this);
        searchPageButton.addActionListener(this);
        profilePageButton.addActionListener(this);
        shoppingCartButton.addActionListener(this);

        this.getContentPane().removeAll();
        this.add(menuPanel);
        this.repaint();
        this.revalidate();

    }

    public void showProductPage(){
        MyScrollable mainScrollable = new MyScrollable("Main Scrollable");
        this.getContentPane().removeAll();
        mainScrollable.setLayout(new GridLayout(0, 1));
        productButtonsList = new ArrayList<>();
        for (Product p : myStore.products) {
            String name = p.getName();
            productButton = new JButton();
            productButton.setText(name);
            productButton.setLayout(new BorderLayout());
            productButton.setBackground(Color.WHITE);
            productButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));

            mainScrollable.add(productButton);
            productButton.addActionListener(this);
            productButtonsList.add(productButton);
        }

        JViewport viewport = new JViewport() {
            @Override
            public void doLayout() {
                super.doLayout();
            }

        };
        viewport.setView(mainScrollable);

        JScrollPane scrollPane = new JScrollPane() {
            @Override
            public void doLayout() {
                super.doLayout();
            }
        };
        mainScrollable.add(backButton);
        backButton.addActionListener(this);
        backButton.setBackground(Color.WHITE);
        backButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        scrollPane.setBackground(backGroundColor);

        scrollPane.setViewport(viewport);
        this.setLayout(new BorderLayout());
        this.add(scrollPane);
        this.repaint();
        this.revalidate();
    }
    public void searchPage(){
        JPanel searchPanel = new JPanel(null);
        searchPanel.setBackground(backGroundColor);

        JLabel searchLabel = new JLabel("عبارت مورد نظر را تایپ کنید");
        searchLabel.setFont(font);
        searchLabel.setBounds(150,150, 300,30);

        searchField.setBounds(150, 250,200,30);

        searchButton.setFont(font);
        searchButton.setBackground(Color.WHITE);
        searchButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        searchButton.setBounds(175, 350, 150,30);
        searchButton.addActionListener(this);
        backButton.setBounds(175, 400, 150,30);
        backButton.addActionListener(this);
        backButton.setBackground(Color.WHITE);
        backButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(backButton);

        this.getContentPane().removeAll();
        this.add(searchPanel);
        this.repaint();
        this.revalidate();
    }

    public void search(String searchedName) throws IOException {
        boolean isProductExist = false;
        for (Product product : myStore.products) {
            if (product.getName().equals(searchedName)) {
                isProductExist = true;
                currentProduct = product;
                productsInfoPage();
            }
        }
        if(!isProductExist)
            showMessages("کالا مورد نظر یافت نشد.");
    }

    public void profilePage(){

        JPanel profilePanel = new JPanel(null);
        profilePanel.setBackground(backGroundColor);

        JLabel profileLabel = new JLabel("پروفایل کاربری");
        profileLabel.setBounds(200, 50, 100, 50);
        profileLabel.setFont(font);

        JLabel name = new JLabel("نام");
        name.setBounds(75, 150, 100, 30);
        name.setFont(font);
        JLabel customerName = new JLabel(costumer.getName());
        customerName.setBounds(250, 150,300,30);
        customerName.setFont(font);

        JLabel userName = new JLabel("نام کاربری");
        userName.setBounds(75, 200, 100, 30);
        userName.setFont(font);
        JLabel customerUserName = new JLabel(costumer.getUserName());
        customerUserName.setBounds(250, 200,300,30);
        customerUserName.setFont(font);

        JLabel phoneNumber = new JLabel("شماره تلفن");
        phoneNumber.setBounds(75, 250, 100,30);
        phoneNumber.setFont(font);
        JLabel customerPhoneNumber = new JLabel(costumer.getPhoneNumber());
        customerPhoneNumber.setBounds(250, 250,300,30);
        customerPhoneNumber.setFont(font);

        JLabel address = new JLabel("آدرس");
        address.setBounds(75, 300, 100,30);
        address.setFont(font);
        JLabel customerAddress = new JLabel(costumer.getAddress());
        customerAddress.setBounds(250, 300,300,30);
        customerAddress.setFont(font);

        JLabel balance = new JLabel("موجودی");
        balance.setBounds(75, 350, 100,30);
        balance.setFont(font);
        JLabel customerBalance = new JLabel(String.valueOf(costumer.getBalance()));
        customerBalance.setBounds(250, 350,300,30);
        customerBalance.setFont(font);


        increaseBalance.setBounds(180, 450, 130 ,50);
        increaseBalance.setFont(font);
        increaseBalance.setBackground(Color.WHITE);
        increaseBalance.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        increaseBalance.addActionListener(this);

        changePassword.setBounds(30, 450, 130 ,50);
        changePassword.setFont(font);
        changePassword.setBackground(Color.WHITE);
        changePassword.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        changePassword.addActionListener(this);

        exit.setBounds(330,450,130,50);
        exit.setFont(font);
        exit.setBackground(Color.WHITE);
        exit.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        exit.addActionListener(this);

        profilePanel.add(profileLabel);
        profilePanel.add(name);
        profilePanel.add(customerName);
        profilePanel.add(userName);
        profilePanel.add(customerUserName);
        profilePanel.add(phoneNumber);
        profilePanel.add(customerPhoneNumber);
        profilePanel.add(address);
        profilePanel.add(customerAddress);
        profilePanel.add(balance);
        profilePanel.add(customerBalance);
        profilePanel.add(exit);
        profilePanel.add(changePassword);
        profilePanel.add(increaseBalance);

        this.getContentPane().removeAll();
        this.add(profilePanel);
        this.repaint();
        this.revalidate();

    }

    public void shoppingCartPage(){
        //TODO
    }

    public void productsInfoPage(){
        Product product = currentProduct;
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(product.getImagePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(175,50,150,150);
        Image image = bufferedImage.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(image);
        imageLabel.setIcon(imageIcon);
        JLabel nameLabel = new JLabel("نام محصول : " + product.getName());
        JLabel priceLabel = new JLabel("قیمت : " + product.getPrice() + " تومان");
        JLabel inventoryLabel = new JLabel("موجودی انبار: " + product.getInventory() + " دسته");

        JPanel productInfoPanel = new JPanel(null);
        productInfoPanel.setBackground(backGroundColor);
        productInfoPanel.add(imageLabel);
        nameLabel.setBounds(310,260,200,20);
        productInfoPanel.add(nameLabel);
        priceLabel.setBounds(320,330,200,20);
        productInfoPanel.add(priceLabel);
        inventoryLabel.setBounds(300,400,200,20);
        productInfoPanel.add(inventoryLabel);
        addToShoppingCartButton.setBounds(200,450,100,30);
        addToShoppingCartButton.setFont(font);
        addToShoppingCartButton.setBackground(Color.WHITE);
        addToShoppingCartButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        productInfoPanel.add(addToShoppingCartButton);
        backButton.setBounds(200,510,100,30);
        backButton.setFont(font);
        backButton.setBackground(Color.WHITE);
        backButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        productInfoPanel.add(backButton);
        addToShoppingCartButton.addActionListener(this);
        backButton.addActionListener(this);

        this.getContentPane().removeAll();
        this.add(productInfoPanel);
        this.repaint();
        this.revalidate();
    }


    public void increaseBalancePage(){

        JPanel increaseBalancePanel = new JPanel(null);
        increaseBalancePanel.setBackground(backGroundColor);
        JLabel increaseBalanceMessage = new JLabel("مبلغ مورد نظر خود را وارد کنید");
        increaseBalanceMessage.setBounds(150 , 200 ,300, 30 );
        increaseBalanceMessage.setFont(font);

        addingBalance.setBounds(175,250, 150,50);

        confirmDeposit.setBounds(175, 400, 150,50);
        confirmDeposit.setFont(font);
        confirmDeposit.setBackground(Color.WHITE);
        confirmDeposit.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));


        increaseBalancePanel.add(increaseBalanceMessage);
        increaseBalancePanel.add(addingBalance);
        increaseBalancePanel.add(confirmDeposit);
    }

    public void changePasswordPage(){
        JPanel changePasswordPanel = new JPanel(null);
        changePasswordPanel.setBackground(backGroundColor);

        JLabel currentPasswordLabel = new JLabel("رمز فعلی خود را وارد کنید");
        currentPasswordLabel.setBounds(170 , 100 ,200, 30);
        currentPasswordLabel.setFont(font);

        currentPassword.setBounds(200, 150, 100, 30);

        JLabel newPasswordLabel = new JLabel("رمز جدید خود را وارد کنید");
        newPasswordLabel.setBounds(170 , 250, 200,30);
        newPasswordLabel.setFont(font);

        newPassword.setBounds(200, 300, 100, 30);

        JButton confirm = new JButton("ثبت");
        confirm.setBounds(180 , 400, 140 ,50);
        confirm.setFont(font);
        confirm.setBackground(Color.WHITE);
        confirm.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));


        changePasswordPanel.add(currentPasswordLabel);
        changePasswordPanel.add(currentPassword);
        changePasswordPanel.add(newPasswordLabel);
        changePasswordPanel.add(newPassword);
        changePasswordPanel.add(confirm);
    }

    public void changePasswordAction() throws IOException {
        if (currentPassword.getText().equals(costumer.getPassword())){
            costumer.changePassword(myStore.userInfo, newPassword.getText());
        }
        else {
            showMessages("رمز عبور اشتباه است.");
        }
    }
    public void increaseBalanceAction() throws IOException {
        boolean flag = true;
        for (int i = 0; i < addingBalance.getText().length(); i++) {
            if (!Character.isDigit(addingBalance.getText().charAt(i))){
                flag = false;
            }
        }
        if (flag){
            costumer.changeBalance(myStore.userInfo, Double.parseDouble(addingBalance.getText()));
        }
        else {
            showMessages("مبلغ وارد شده معتبر نیست.");
        }

    }

    public void back(){
        this.getContentPane().removeAll();
        menuPage();
        this.repaint();
        this.revalidate();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == showProductsButton){
            showProductPage();
        }
        if (e.getSource() == searchPageButton){
            searchPage();
        }
        if (e.getSource() == searchButton){
            try {
                search(searchField.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == profilePageButton){
            profilePage();
        }
        if (e.getSource() == changePassword){
            changePasswordPage();
        }
        if (e.getSource() == confirmNewPassword){
            try {
                changePasswordAction();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            showMessages("رمز عبور با موفقیت تغییر کرد.");
            back();
        }
        if (e.getSource() == increaseBalance){
            increaseBalancePage();
        }
        if (e.getSource() == confirmDeposit){
            try {
                increaseBalanceAction();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            showMessages("اعتبار حساب شما افزایش یافت.");
            back();

        }
        if (e.getSource() == exit){
            welcomePage();
        }
        if(e.getSource() == backButton){
            back();
        }
        for (int i = 0; i < productButtonsList.size(); i++){
            if (e.getSource() == productButtonsList.get(i)){
                currentProduct = myStore.products.get(i);
                productsInfoPage();
                break;
            }
        }
    }
}
