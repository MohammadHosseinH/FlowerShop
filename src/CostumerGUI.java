import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CostumerGUI extends ShopGUI implements ActionListener {
    //Fields and Components

    Font font = new Font("Adobe Arabic", Font.PLAIN, 18);
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
        //TODO
    }

    public void profilePage(){

        JPanel profilePanel = new JPanel(null);

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

        changePassword.setBounds(30, 450, 130 ,50);
        changePassword.setFont(font);

        exit.setBounds(330,450,130,50);
        exit.setFont(font);


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

    }

    public void shoppingCartPage(){
        //TODO
    }

    public void increaseBalancePage(){

        JPanel increaseBalancePanel = new JPanel(null);
        JLabel increaseBalanceMessage = new JLabel("مبلغ مورد نظر خود را وارد کنید");
        increaseBalanceMessage.setBounds(150 , 200 ,300, 30 );
        increaseBalanceMessage.setFont(font);

        addingBalance.setBounds(175,250, 150,50);

        confirmDeposit.setBounds(175, 400, 150,50);
        confirmDeposit.setFont(font);

        increaseBalancePanel.add(increaseBalanceMessage);
        increaseBalancePanel.add(addingBalance);
        increaseBalancePanel.add(confirmDeposit);
    }

    public void changePasswordPage(){
        JPanel changePasswordPanel = new JPanel(null);

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

        changePasswordPanel.add(currentPasswordLabel);
        changePasswordPanel.add(currentPassword);
        changePasswordPanel.add(newPasswordLabel);
        changePasswordPanel.add(newPassword);
        changePasswordPanel.add(confirm);
    }

    public void changePasswordAction() throws IOException {
        if (currentPassword.getText().equals(costumer.getPassword())){
            costumer.changePassword(userInfo, newPassword.getText());
        }
        else {
            showErrors("رمز عبور اشتباه است.");
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
            costumer.changeBalance(userInfo, Double.parseDouble(addingBalance.getText()));
        }
        else {
            showErrors("مبلغ وارد شده معتبر نیست.");
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changePassword){
            changePasswordPage();
        }
        if (e.getSource() == confirmNewPassword){
            try {
                changePasswordAction();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
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
        }
        if (e.getSource() == exit){
            welcomePage();
        }
    }
}
