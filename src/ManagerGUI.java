import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    JButton showProductsButton = new JButton("نمایش محصولات");
    JButton showUsersButton = new JButton("نمایش کاربران");
    JButton addProductButton = new JButton("افزودن کالا");
    JButton searchProductButton = new JButton("جستجوی کالا");
    JButton backButton = new JButton("بازگشت");
    JButton productButton = new JButton();
    JButton confirmAddProduct = new JButton("ثبت");
    JButton searchButton = new JButton("جست و جو");
    JTextField searchField = new JTextField();

    ManagerGUI(){
        try {
            setProductArray(productFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        menuPage();

    }

    public void menuPage(){
        JPanel menuPanel = new JPanel(null);
        showProductsButton.setFont(font);
        showProductsButton.setBounds(150,100,200,40);
        menuPanel.add(showProductsButton);
        showUsersButton.setFont(font);
        showUsersButton.setBounds(150,200,200,40);
        menuPanel.add(showUsersButton);
        addProductButton.setFont(font);
        addProductButton.setBounds(150,300,200,40);
        menuPanel.add(addProductButton);
        searchProductButton.setFont(font);
        searchProductButton.setBounds(150,400,200,40);
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
        this.getContentPane().removeAll();
        MyScrollable mainScrollable = new MyScrollable("Main Scrollable");
        mainScrollable.setLayout(new GridLayout(0, 1));

        for (Product p : products) {
            String name = p.getName();
            productButton.setName(name);
            productButton.setBorder(BorderFactory.createLineBorder(Color.blue));
            productButton.setLayout(new BorderLayout());
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
        scrollPane.setViewport(viewport);
        this.setLayout(new BorderLayout());
        this.add(scrollPane);
        this.repaint();
        this.revalidate();
    }

    public void addProductPage(){
        JPanel addProductPanel = new JPanel(null);

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

        confirmAddProduct.setBounds(175, 425, 150, 40);
        confirmAddProduct.setFont(font);


        addProductPanel.add(productInfo);
        addProductPanel.add(productName);
        addProductPanel.add(productPrice);
        addProductPanel.add(enteredProductPrice);
        addProductPanel.add(enteredProductName);
        addProductPanel.add(productInventory);
        addProductPanel.add(enteredProductInventory);
        addProductPanel.add(addProductImage);
        addProductPanel.add(confirmAddProduct);
    }

    public void searchPage(){
        JPanel searchPanel = new JPanel(null);

        JLabel searchLabel = new JLabel("عبارت مورد نظر را تایپ کنید");
        searchLabel.setFont(font);
        searchLabel.setBounds(150,150, 300,30);

        searchField.setBounds(150, 250,200,30);

        searchButton.setFont(font);
        searchButton.setBounds(175, 350, 150,40);

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
    }

    public boolean search() throws IOException {
        boolean flag = false;
        for (int i = 0; i < arrangeProductArray(productFile).size(); i++) {
            if (arrangeProductArray(productFile).get(i).contains(searchField.getText())){
                flag = true;
            }
        }
        return flag;
    }

    public void usersListPage(){
        //TODO
    }

    public void productsInfoPage(Product product){
        //TODO
    }

    public void setProductArray(File productsFile) throws IOException {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == showProductsButton){
            showProductPage();
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
                productsInfoPage(products.get(i));
            }
        }
    }
}


@SuppressWarnings("serial")
class MyScrollable extends JComponent implements Scrollable {
    public static final int VP_WIDTH = 200;
    private static final int ROW_COUNT = 40;

    public MyScrollable(String name) {
        super.setName(name);
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        Component[] comps = getComponents();
        if (comps.length > 0) {
            int height = ROW_COUNT * comps[0].getPreferredSize().height;
            return new Dimension(VP_WIDTH, height);
        }

        return super.getPreferredSize();
    }

    @Override
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        if (orientation == SwingConstants.HORIZONTAL) {
            return VP_WIDTH;
        }
        Component[] comps = getComponents();
        if (comps.length > 0) {
            return comps[0].getHeight() * (3 * ROW_COUNT / 4);
        }

        return getSize().height / 3;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true;
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        if (orientation == SwingConstants.HORIZONTAL) {
            return VP_WIDTH;
        }
        Component[] comps = getComponents();
        if (comps.length > 0) {
            return comps[0].getHeight();
        }
        return getSize().height / 3;
    }

}
