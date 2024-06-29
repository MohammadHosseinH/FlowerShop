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
        scrollPane.setViewport(viewport);
        this.setLayout(new BorderLayout());
        this.add(scrollPane);
        this.repaint();
        this.revalidate();
    }

    public void addProductPage(){
        //TODO
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
        productInfoPanel.add(imageLabel);
        nameLabel.setBounds(310,260,200,20);
        productInfoPanel.add(nameLabel);
        priceLabel.setBounds(320,330,200,20);
        productInfoPanel.add(priceLabel);
        inventoryLabel.setBounds(300,400,200,20);
        productInfoPanel.add(inventoryLabel);
        editName.setBounds(50,260,80,20);
        productInfoPanel.add(editName);
        editPrice.setBounds(50,330,80,20);
        productInfoPanel.add(editPrice);
        editInventory.setBounds(50,400,80,20);
        productInfoPanel.add(editInventory);
        backButton.setBounds(200,480,100,40);
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
        for (int i = 0; i < productButtonsList.size(); i++){
            if (e.getSource() == productButtonsList.get(i)){
                productButtonsList = new ArrayList<>();
                productsInfoPage(products.get(i));
                break;
            }
        }
    }
}
