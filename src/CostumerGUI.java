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
    JPanel menuPanel = new JPanel(null);
    JButton showProductsButton = new JButton("نمایش محصولات");
    JButton searchPageButton = new JButton("جستجو محصولات");
    JButton profilePageButton = new JButton("پروفایل");
    JButton shoppingCartButton = new JButton("سبد خرید");
    JButton searchButton = new JButton("جستجو");
    JTextField searchField = new JTextField();
    JButton addToShoppingCartButton = new JButton("افزودن به سبد خرید");
    JButton deleteFromShoppingCartButton = new JButton("حذف از سبد خرید");
    JButton changePassword = new JButton("تغییر رمز");
    JButton increaseBalance = new JButton("افزایش موجودی");
    JButton confirmNewPassword = new JButton("ثبت");
    JTextField newPassword = new JTextField();
    JTextField currentPassword = new JTextField();
    JTextField addingBalance = new JTextField();
    JButton confirmDeposit = new JButton("ثبت");
    JButton exit = new JButton("خروج");
    JButton rateButton = new JButton("امتیاز دهید");
    JButton confirmRate = new JButton("ثبت");
    JTextField costumerRate = new JTextField();
    JButton completeShopping = new JButton("نهایی کردن خرید");
    JButton filterByPriceButton = new JButton("مرتب سازی بر اساس قیمت");
    JButton filterByRateButton = new JButton("مرتب سازی بر اساس امتیاز");
    boolean isItShoppingCartList = false;
    boolean isItAShoppingCartProductInfo = false;
    JButton backButton = new JButton("بازگشت");
    boolean isItFirstAction = true;

    CostumerGUI(){
        menuPage();
        try {
            costumer.adjustShoppingCart(myStore.productFile,myStore.shoppingCartFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void menuPage(){
        menuPanel.setBackground(backGroundColor);
        showProductsButton.setFont(font);
        showProductsButton.setBounds(150,100,200,40);
        showProductsButton.setBackground(Color.WHITE);
        showProductsButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        menuPanel.add(showProductsButton);
        searchPageButton.setFont(font);
        searchPageButton.setBounds(150,170,200,40);
        searchPageButton.setBackground(Color.WHITE);
        searchPageButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        menuPanel.add(searchPageButton);
        profilePageButton.setFont(font);
        profilePageButton.setBounds(150,240,200,40);
        profilePageButton.setBackground(Color.WHITE);
        profilePageButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        menuPanel.add(profilePageButton);
        shoppingCartButton.setFont(font);
        shoppingCartButton.setBounds(150,310,200,40);
        shoppingCartButton.setBackground(Color.WHITE);
        shoppingCartButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        menuPanel.add(shoppingCartButton);
        filterByPriceButton.setFont(font);
        filterByPriceButton.setBounds(150,380,200,40);
        filterByPriceButton.setBackground(Color.WHITE);
        filterByPriceButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        menuPanel.add(filterByPriceButton);
        filterByRateButton.setFont(font);
        filterByRateButton.setBounds(150,450 ,200,40);
        filterByRateButton.setBackground(Color.WHITE);
        filterByRateButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        menuPanel.add(filterByRateButton);

        showProductsButton.addActionListener(this);
        searchPageButton.addActionListener(this);
        profilePageButton.addActionListener(this);
        shoppingCartButton.addActionListener(this);
        filterByPriceButton.addActionListener(this);
        filterByRateButton.addActionListener(this);
        this.getContentPane().removeAll();
        this.add(menuPanel);
        this.repaint();
        this.revalidate();

    }

    public void showProductPage(ArrayList<Product> givenProducts){
        MyScrollable mainScrollable = new MyScrollable("Main Scrollable");
        this.getContentPane().removeAll();
        mainScrollable.setLayout(new GridLayout(0, 1));
        productButtonsList = new ArrayList<>();
        for (Product p : givenProducts) {
            String name = p.getName();
            productButton = new JButton();
            productButton.setFont(font);
            productButton.setText(name);
            productButton.setLayout(new BorderLayout());
            productButton.setBackground(Color.WHITE);
            productButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
            productButton.setPreferredSize(new Dimension(200, 40));
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
        if(isItShoppingCartList && !costumer.getShoppingCart().isEmpty()){
            JLabel amountLabel = new JLabel("مبلغ خرید نهایی : " + costumer.totalPriceOfShoppingCart() + " تومان");
            amountLabel.setFont(font);
            amountLabel.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
            amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
            amountLabel.setVerticalAlignment(SwingConstants.CENTER);
            mainScrollable.add(amountLabel);
            mainScrollable.add(completeShopping);
            completeShopping.addActionListener(this);
            completeShopping.setFont(font);
            completeShopping.setBackground(Color.WHITE);
            completeShopping.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
            isItShoppingCartList = false;
            isItAShoppingCartProductInfo = true;
        }
        mainScrollable.add(backButton);
        backButton.addActionListener(this);
        backButton.setBackground(Color.WHITE);
        backButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        backButton.setFont(font);
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
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nameLabel.setFont(font);
        JLabel priceLabel = new JLabel("قیمت : " + product.getPrice() + " تومان");
        priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        priceLabel.setFont(font);
        JLabel inventoryLabel = new JLabel("موجودی انبار: " + product.getInventory() + " دسته");
        inventoryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        inventoryLabel.setFont(font);

        JPanel productInfoPanel = new JPanel(null);
        productInfoPanel.setBackground(backGroundColor);
        productInfoPanel.add(imageLabel);
        nameLabel.setBounds(250,260,150,20);
        productInfoPanel.add(nameLabel);
        priceLabel.setBounds(250,330,150,20);
        productInfoPanel.add(priceLabel);
        inventoryLabel.setBounds(250,400,150,20);
        productInfoPanel.add(inventoryLabel);
        rateButton.setFont(font);
        rateButton.setBackground(Color.WHITE);
        rateButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        rateButton.setBounds(270,450, 100,30);
        rateButton.addActionListener(this);
        productInfoPanel.add(rateButton);
        if(costumer.getShoppingCart().contains(currentProduct)){
            deleteFromShoppingCartButton.setBounds(130,450,120,30);
            deleteFromShoppingCartButton.setFont(font);
            deleteFromShoppingCartButton.setBackground(Color.WHITE);
            deleteFromShoppingCartButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
            productInfoPanel.add(deleteFromShoppingCartButton);
        }
        else {
            addToShoppingCartButton.setBounds(130, 450, 120, 30);
            addToShoppingCartButton.setFont(font);
            addToShoppingCartButton.setBackground(Color.WHITE);
            addToShoppingCartButton.setBorder(BorderFactory.createLineBorder(new Color(72, 61, 139)));
            productInfoPanel.add(addToShoppingCartButton);
        }
        backButton.setBounds(200,510,120,30);
        backButton.setFont(font);
        backButton.setBackground(Color.WHITE);
        backButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        productInfoPanel.add(backButton);
        if(isItFirstAction) {
            addToShoppingCartButton.addActionListener(this);
            deleteFromShoppingCartButton.addActionListener(this);
            isItFirstAction = false;
        }
        backButton.addActionListener(this);

        this.getContentPane().removeAll();
        this.add(productInfoPanel);
        this.repaint();
        this.revalidate();
    }

    public void ratePage(){
        JPanel ratePanel = new JPanel(null);
        ratePanel.setBackground(backGroundColor);

        JLabel editNameLabel = new JLabel("نمره مورد نظر خود را از 1 تا 10 وارد کنید");
        editNameLabel.setBounds(120,200,300,30);
        editNameLabel.setFont(font);

        costumerRate.setBounds(200,275, 100,30);
        costumerRate.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));

        confirmRate.setBounds(200,375,100,40);
        confirmRate.setFont(font);
        confirmRate.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        confirmRate.setBackground(Color.WHITE);


        ratePanel.add(editNameLabel);
        ratePanel.add(costumerRate);
        ratePanel.add(confirmRate);

        confirmRate.addActionListener(this);
        this.getContentPane().removeAll();
        this.add(ratePanel);
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
        confirmDeposit.addActionListener(this);

        increaseBalancePanel.add(increaseBalanceMessage);
        increaseBalancePanel.add(addingBalance);
        increaseBalancePanel.add(confirmDeposit);

        this.getContentPane().removeAll();
        this.add(increaseBalancePanel);
        this.repaint();
        this.revalidate();
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

        this.getContentPane().removeAll();
        this.add(changePasswordPanel);
        this.repaint();
        this.revalidate();
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
        String text = addingBalance.getText();
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isDigit(text.charAt(i))){
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
        this.add(menuPanel);
        this.repaint();
        this.revalidate();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == showProductsButton){
            isItAShoppingCartProductInfo = false;
            showProductPage(myStore.products);
        }
        else if (e.getSource() == searchPageButton){
            searchPage();
        }
        else if (e.getSource() == searchButton){
            try {
                search(searchField.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (e.getSource() == rateButton){
            ratePage();
        }
        else if (e.getSource() == confirmRate){
            try {
                currentProduct.adjustRate(myStore.productFile, Double.parseDouble(costumerRate.getText()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            this.getContentPane().removeAll();
            productsInfoPage();
            this.repaint();
            this.revalidate();
        }
        else if (e.getSource() == shoppingCartButton){
            isItShoppingCartList = true;
            showProductPage(costumer.getShoppingCart());
        }
        else if (e.getSource() == addToShoppingCartButton){
            costumer.addToShoppingCart(currentProduct);
            showMessages(currentProduct.getName() + " به سبد خرید افزوده شد.");
            back();
        }
        else if (e.getSource() == deleteFromShoppingCartButton){
            costumer.getShoppingCart().remove(currentProduct);
            showMessages(currentProduct.getName() + " از سبد خرید حذف شد.");
            back();
        }
        else if (e.getSource() == profilePageButton){
            profilePage();
        }
        else if (e.getSource() == changePassword){
            changePasswordPage();
        }
        else if (e.getSource() == confirmNewPassword){
            try {
                changePasswordAction();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            showMessages("رمز عبور با موفقیت تغییر کرد.");
            back();
        }
        else if (e.getSource() == increaseBalance){
            increaseBalancePage();
        }
        else if (e.getSource() == confirmDeposit){
            try {
                increaseBalanceAction();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            showMessages("اعتبار حساب شما افزایش یافت.");
            back();
        }
        else if(e.getSource()==completeShopping){
            double price= costumer.totalPriceOfShoppingCart();
            if(price>costumer.getBalance())
                showMessages("موجودی کافی نیست.");
            else {
                try {
                    resetProductInventory();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                costumer.setBalance(costumer.getBalance()-price);
                price=price*-1;
                try {
                    costumer.changeBalance(myStore.userInfo,price);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                showMessages("خرید با موفقیت انجام شد");
                this.getContentPane().removeAll();
                profilePage();
                this.repaint();
                this.revalidate();
            }
        }
        else if(e.getSource() == filterByPriceButton){
            myStore.sortingProductsByPrice();
            showProductPage(myStore.sortedProducts);
        }
        else if (e.getSource() == filterByRateButton) {
            myStore.sortingProductsByRate();
            showProductPage(myStore.sortedProducts);
        }
        else if (e.getSource() == exit){
            try {
                costumer.writeInShoppingCartFile(myStore.shoppingCartFile);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            this.dispose();
            new ShopGUI();
        }
        else if(e.getSource() == backButton){
            back();
        }
        for (int i = 0; i < productButtonsList.size(); i++){
            if (e.getSource() == productButtonsList.get(i)){
                if (isItAShoppingCartProductInfo) {
                    currentProduct = costumer.getShoppingCart().get(i);
                }
                else {
                    currentProduct = myStore.products.get(i);
                }
                isItAShoppingCartProductInfo = false;
                productsInfoPage();
                break;
            }
        }
    }
}
