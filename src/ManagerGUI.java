import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ManagerGUI extends ShopGUI implements ActionListener {
    //Fields and Components
    JButton showProductsButton = new JButton("نمایش محصولات");
    JButton showUsersButton = new JButton("نمایش کاربران");
    JButton addProductButton = new JButton("افزودن کالا");
    JTextField productNameField = new JTextField();
    JTextField priceField = new JTextField();
    JTextField inventoryField = new JTextField();
    JButton searchProductButton = new JButton("جستجوی کالا");
    JButton confirmAddProduct = new JButton("ثبت");
    JButton chooseImageButton = new JButton("افزودن عکس");
    JButton searchButton = new JButton("جست و جو");
    JTextField searchField = new JTextField();
    JButton editName = new JButton("تغییر");
    JButton editPrice = new JButton("تغییر");
    JButton editInventory = new JButton("تغییر");
    JButton deleteProduct= new JButton("حذف محصول");
    JFileChooser fileChooser = new JFileChooser("images");
    String path;

    ManagerGUI(){
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
        showUsersButton.setFont(font);
        showUsersButton.setBounds(150,200,200,40);
        showUsersButton.setBackground(Color.WHITE);
        showUsersButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));

        menuPanel.add(showUsersButton);
        addProductButton.setFont(font);
        addProductButton.setBounds(150,300,200,40);
        addProductButton.setBackground(Color.WHITE);
        addProductButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));

        menuPanel.add(addProductButton);
        searchProductButton.setFont(font);
        searchProductButton.setBounds(150,400,200,40);
        searchProductButton.setBackground(Color.WHITE);
        searchProductButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        menuPanel.add(searchProductButton);

        showProductsButton.addActionListener(this);
        showUsersButton.addActionListener(this);
        addProductButton.addActionListener(this);
        searchProductButton.addActionListener(this);

        this.getContentPane().removeAll();
        this.add(menuPanel);
        this.repaint();
        this.revalidate();
    }
    public void showProductPage(ArrayList<Product> givenProduct){
        MyScrollable mainScrollable = new MyScrollable("Main Scrollable");
        this.getContentPane().removeAll();
        mainScrollable.setLayout(new GridLayout(0, 1));
        productButtonsList = new ArrayList<>();
        for (Product p : givenProduct) {
            String name = p.getName();
            productButton = new JButton();
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

    public void addProductPage(){
        JPanel addProductPanel = new JPanel(null);
        addProductPanel.setBackground(backGroundColor);

        JLabel productInfo = new JLabel("مشخصات کالا");
        productInfo.setBounds(200, 100, 100, 30);
        productInfo.setFont(font);
        JLabel productName = new JLabel("نام کالا");
        productName.setBounds(100 , 175 , 50, 30);
        productName.setFont(font);
        productNameField.setBounds(200, 175, 200,30);
        JLabel productPrice = new JLabel("قیمت");
        productPrice.setBounds(100 , 225 , 50, 30);
        productPrice.setFont(font);
        priceField.setBounds(200,225,200,30);
        JLabel productInventory = new JLabel("موجودی");
        productInventory.setBounds(100 , 275 , 50, 30);
        productInventory.setFont(font);
        inventoryField.setBounds(200,275,200,30);

        chooseImageButton.setBounds(175, 350, 150, 30);
        chooseImageButton.setFont(font);
        chooseImageButton.setBackground(Color.WHITE);
        chooseImageButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
        chooseImageButton.addActionListener(this);
        confirmAddProduct.setBounds(175, 450, 150, 30);
        confirmAddProduct.setFont(font);
        confirmAddProduct.addActionListener(this);
        backButton.setBounds(175, 400, 150, 30);
        backButton.setFont(font);
        backButton.addActionListener(this);
        confirmAddProduct.setBackground(Color.WHITE);
        confirmAddProduct.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));

        addProductPanel.add(productInfo);
        addProductPanel.add(productName);
        addProductPanel.add(productPrice);
        addProductPanel.add(priceField);
        addProductPanel.add(productNameField);
        addProductPanel.add(productInventory);
        addProductPanel.add(inventoryField);
        addProductPanel.add(chooseImageButton);
        addProductPanel.add(confirmAddProduct);
        addProductPanel.add(backButton);

        this.getContentPane().removeAll();
        this.add(addProductPanel);
        this.repaint();
        this.revalidate();
    }

    public String chooseImage(){
        fileChooser = new JFileChooser("images");
        fileChooser.setAcceptAllFileFilterUsed(false);
        File file = null;
        int option = fileChooser.showOpenDialog(ManagerGUI.this);
        if (option == 0) {
            file = fileChooser.getSelectedFile();
        }
        return file.getName();
    }

    public void addProduct() throws IOException {
        String name = productNameField.getText();
        productNameField.setText("");
        double price = Double.parseDouble(priceField.getText());
        priceField.setText("");
        int inventory = Integer.parseInt(inventoryField.getText());
        inventoryField.setText("");
        int numberOfVotes=0;
        double rate=0.0;
        Product tempProduct = new Product(name,price,inventory,path,numberOfVotes,rate);
        myStore.products.add(tempProduct);
        tempProduct.addProductInFile(myStore.productFile);
        showMessages("کالا با موفقیت افزوده شد");
    }

    public void searchPage(){
        JPanel searchPanel = new JPanel(null);

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

    public void usersListPage(){
        MyScrollable mainScrollable = new MyScrollable("Main Scrollable");
        this.getContentPane().removeAll();
        mainScrollable.setLayout(new GridLayout(0, 1));

        for (Costumer c : myStore.costumers) {
            JLabel userLabel = new JLabel();
            String name = c.getName() + " , " + c.getUserName() + " , " + c.getPhoneNumber() + " , " + c.getAddress();
            userLabel.setText(name);
            userLabel.setLayout(new BorderLayout());
            userLabel.setBackground(Color.WHITE);
            userLabel.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));
            mainScrollable.add(userLabel);
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
        scrollPane.setViewport(viewport);
        this.setLayout(new BorderLayout());
        this.add(scrollPane);
        this.repaint();
        this.revalidate();
    }

    public void productsInfoPage(){
        BufferedImage bufferedImage = null;
        Product product = currentProduct;
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
        editName.setBounds(50,260,80,20);
        editName.setFont(font);
        editName.setBackground(Color.WHITE);
        editName.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));

        productInfoPanel.add(editName);
        editPrice.setBounds(50,330,80,20);
        editPrice.setFont(font);
        editPrice.setBackground(Color.WHITE);
        editPrice.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));

        productInfoPanel.add(editPrice);
        editInventory.setBounds(50,400,80,20);
        editInventory.setFont(font);
        editInventory.setBackground(Color.WHITE);
        editInventory.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));

        productInfoPanel.add(editInventory);
        backButton.setBounds(200,480,100,40);
        backButton.setFont(font);
        backButton.setBackground(Color.WHITE);
        backButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));

        productInfoPanel.add(backButton);
        deleteProduct.setBounds(50,480,100,40);
        deleteProduct.setFont(font);
        deleteProduct.setBackground(Color.WHITE);
        deleteProduct.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));

        productInfoPanel.add(deleteProduct);

        editName.addActionListener(this);
        editPrice.addActionListener(this);
        editInventory.addActionListener(this);
        backButton.addActionListener(this);
        deleteProduct.addActionListener(this);

        this.getContentPane().removeAll();
        this.add(productInfoPanel);
        this.repaint();
        this.revalidate();
    }
    public void deleteProduct() throws IOException {
        currentProduct.deleteProduct(myStore.productFile);
        myStore.products.remove(currentProduct);
        back();
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
            showProductPage(myStore.products);
        }
        if(e.getSource() == addProductButton){
            addProductPage();
        }
        if (e.getSource() == chooseImageButton) {
                path = "images\\" + chooseImage();
        }
        if (e.getSource() == editName){
            //TODO
        }
        if (e.getSource() == editPrice){
            //TODO
        }
        if(e.getSource() == editInventory){
            //TODO
        }
        if(e.getSource()== deleteProduct){
            try {
                deleteProduct();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource() == backButton){
            back();
        }
        if(e.getSource() == showUsersButton){
            usersListPage();
        }

        if (e.getSource() == confirmAddProduct){
            try {
                addProduct();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            back();
        }
        if (e.getSource() == searchProductButton){
            searchPage();
        }
        if (e.getSource() == searchButton){
            try {
                search(searchField.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
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
