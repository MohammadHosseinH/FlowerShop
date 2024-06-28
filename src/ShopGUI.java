import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopGUI {
    import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class ShopGUI extends JFrame implements ActionListener {
        //Fields and Components
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

        ShopGUI(){
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("گل فروشی");
            this.setSize(500, 600);
            this.setVisible(true);
            this.setResizable(false);

            welcomePage();

        }

        public void welcomePage(){
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

            welcomeLabel.setBounds(220,100,100,30);
            costumerSignupButton.setBounds(175,200,150,40);
            costumerLoginButton.setBounds(175,300, 150, 40);
            managerLoginButton.setBounds(175,400,150,40);

            costumerLoginButton.addActionListener(this);
            costumerSignupButton.addActionListener(this);
            managerLoginButton.addActionListener(this);


        }

        public void costumerSignupPage(){
            JPanel signupPanel = new JPanel(null);

            JLabel nameLabel = new JLabel("نام:");
            nameLabel.setFont(font);
            nameLabel.setBounds(400,50,100,100);
            signupPanel.add(nameLabel);
            JLabel userNameLabel = new JLabel("نام کاربری:");
            userNameLabel.setFont(font);
            userNameLabel.setBounds(370,120,100,100);
            signupPanel.add(userNameLabel);
            JLabel passwordLabel = new JLabel("رمز عبور:");
            passwordLabel.setFont(font);
            passwordLabel.setBounds(380,190,100,100);
            signupPanel.add(passwordLabel);
            JLabel phoneNumberLabel = new JLabel("شماره تلفن:");
            phoneNumberLabel.setFont(font);
            phoneNumberLabel.setBounds(370,260,100,100);
            signupPanel.add(phoneNumberLabel);
            JLabel addressLabel = new JLabel("نشانی منزل");
            addressLabel.setFont(font);
            addressLabel.setBounds(370,330,100,100);
            signupPanel.add(addressLabel);

            nameField.setBounds(100,90,220,30);
            signupPanel.add(nameField);
            userNameField.setBounds(100,160,220,30);
            signupPanel.add(userNameField);
            passwordField.setBounds(100,230,220,30);
            signupPanel.add(passwordField);
            phoneNumberField.setBounds(100,300,220,30);
            signupPanel.add(phoneNumberField);
            addressField.setBounds(100,370,220,30);
            signupPanel.add(addressField);

            confirmSignup.setBounds(200,450,100,50);
            confirmSignup.addActionListener(this);

            signupPanel.add(confirmSignup);
            this.getContentPane().removeAll();
            this.add(signupPanel);
            this.repaint();
            this.revalidate();

        }
        public void costumerSignup(){
            //TODO validate information and writing in file

            new CostumerGUI();

        }

        public void costumerLoginPage(){
            JPanel loginPanel = new JPanel(null);
            JLabel userNameLabel = new JLabel("نام کاربری:");
            userNameLabel.setFont(font);
            userNameLabel.setBounds(370,200,100,100);
            loginPanel.add(userNameLabel);
            JLabel passwordLabel = new JLabel("رمز عبور:");
            passwordLabel.setFont(font);
            passwordLabel.setBounds(380,280,100,100);
            loginPanel.add(passwordLabel);

            userNameField.setBounds(100,240,220,30);
            loginPanel.add(userNameField);
            passwordField.setBounds(100,320,220,30);
            loginPanel.add(passwordField);

            confirmCostumerLogin.setBounds(200,450,100,50);
            loginPanel.add(confirmCostumerLogin);
            confirmCostumerLogin.addActionListener(this);

            this.getContentPane().removeAll();
            this.add(loginPanel);
            this.repaint();
            this.revalidate();


        }

        public void costumerLogin(){
            //TODO checking if user exists and password is match

            new CostumerGUI();
        }

        public void managerLoginPage(){
            //TODO
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
                costumerSignup();
            }
            else if (e.getSource() == confirmCostumerLogin) {
                costumerLogin();
            }
        }
    }

}
