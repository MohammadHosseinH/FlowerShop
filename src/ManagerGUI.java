import javax.imageio.ImageIO;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ManagerGUI extends ShopGUI implements ActionListener {
    //Fields and Components
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Costumer> costumers = new ArrayList<>();
    ArrayList<JButton> productButtonsList = new ArrayList<>();
    String currentPanel;
    JButton showProductsButton = new JButton("نمایش محصولات");
    JButton showUsersButton = new JButton("نمایش کاربران");
    JButton addProductButton = new JButton("افزودن کالا");
    JButton searchProductButton = new JButton("جستجوی کالا");
    JButton backButton = new JButton("بازگشت");
    JButton productButton = new JButton();
    JButton confirmAddProduct = new JButton("ثبت");
    JButton searchButton = new JButton("جست و جو");
    JTextField searchField = new JTextField();

    JButton editName = new JButton("تغییر");
    JButton editPrice = new JButton("تغییر");
    JButton editInventory = new JButton("تغییر");
    MyScrollable mainScrollable = new MyScrollable("Main Scrollable");

    ManagerGUI(){
        try {
            setProductsArray(productFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    public void showProductPage(){
        currentPanel = "show product list";
        this.getContentPane().removeAll();
        mainScrollable.setLayout(new GridLayout(0, 1));

        for (Product p : products) {
            String name = p.getName();
            productButton.setText(name);
            productButton.setLayout(new BorderLayout());
            productButton.setBackground(Color.WHITE);
            productButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));

            productButton.add(new JLabel(productButton.getName()));
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
        JTextField enteredProductName = new JTextField();
        enteredProductName.setBounds(200, 175, 200,30);

        JLabel productPrice = new JLabel("قیمت");
        productPrice.setBounds(100 , 225 , 50, 30);
        productPrice.setFont(font);
        JTextField enteredProductPrice = new JTextField();
        enteredProductPrice.setBounds(200,225,200,30);

        JLabel productInventory = new JLabel("موجودی");
        productInventory.setBounds(100 , 275 , 50, 30);
        productInventory.setFont(font);
        JTextField enteredProductInventory = new JTextField();
        enteredProductInventory.setBounds(200,275,200,30);


        //it should change to select image
        JButton addProductImage = new JButton("افزودن عکس");
        addProductImage.setBounds(175, 350, 150, 40);
        addProductImage.setFont(font);
        addProductImage.setBackground(Color.WHITE);
        addProductImage.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));


        confirmAddProduct.setBounds(175, 425, 150, 40);
        confirmAddProduct.setFont(font);
        confirmAddProduct.addActionListener(this);
        confirmAddProduct.setBackground(Color.WHITE);
        confirmAddProduct.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));



        addProductPanel.add(productInfo);
        addProductPanel.add(productName);
        addProductPanel.add(productPrice);
        addProductPanel.add(enteredProductPrice);
        addProductPanel.add(enteredProductName);
        addProductPanel.add(productInventory);
        addProductPanel.add(enteredProductInventory);
        addProductPanel.add(addProductImage);
        addProductPanel.add(confirmAddProduct);

        this.getContentPane().removeAll();
        this.add(addProductPanel);
        this.repaint();
        this.revalidate();
    }

    public void searchPage(){
        JPanel searchPanel = new JPanel(null);

        JLabel searchLabel = new JLabel("عبارت مورد نظر را تایپ کنید");
        searchLabel.setFont(font);
        searchLabel.setBounds(150,150, 300,30);

        searchField.setBounds(150, 250,200,30);

        searchButton.setFont(font);
        searchButton.setBounds(175, 350, 150,40);
        searchButton.setBackground(Color.WHITE);
        searchButton.setBorder(BorderFactory.createLineBorder(new Color(72,61,139)));


        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        this.getContentPane().removeAll();
        this.add(searchPanel);
        this.repaint();
        this.revalidate();
    }

    public boolean search() throws IOException {
        return true;
    }

    public void usersListPage(){
        //TODO
    }

    public void productsInfoPage(Product product){
        currentPanel = "product info";
        BufferedImage bufferedImage = null;
        System.out.println(product.getImagePath());
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

        editName.addActionListener(this);
        editPrice.addActionListener(this);
        editInventory.addActionListener(this);
        backButton.addActionListener(this);

        this.getContentPane().removeAll();
        this.add(productInfoPanel);
        this.repaint();
        this.revalidate();
    }

    public void setProductsArray(File productsFile) throws IOException {
        FileReader fileReader =new FileReader(productsFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        int i = 0;
        while ((line = bufferedReader.readLine()) != null){
            String[] lines = line.split(",");
            Product tempProduct = new Product(lines[0] , Double.parseDouble(lines[2]) , Integer.parseInt(lines[3]) , lines[1]);
            products.add(tempProduct);
        }
        bufferedReader.close();
    }

    public void back(){
        this.getContentPane().removeAll();
        if(currentPanel.equals("show product list")) {
            menuPage();
        }
        else if(currentPanel.equals("product info")){
            showProductPage();
        }
        this.repaint();
        this.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == showProductsButton){
            showProductPage();
        }
        if(e.getSource() == addProductButton){
            addProductPage();
        }
        if (e.getSource() == searchProductButton){
            searchPage();
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
        if(e.getSource() == backButton){
            back();
        }

        if (e.getSource() == confirmAddProduct){
            //TODO
        }
        if (e.getSource() == searchProductButton){
            searchPage();
        }
        if (e.getSource() == searchButton){
            try {
                if (search()){
                    //TODO
                }
                else {
                    showErrors("کالا مورد نظر یافت نشد.");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        for (int i = 0; i < productButtonsList.size(); i++){
            if (e.getSource() == productButtonsList.get(i)){
                productButtonsList = new ArrayList<>();
                productsInfoPage(products.get(i));
                break;
            }
        }
    }
}
