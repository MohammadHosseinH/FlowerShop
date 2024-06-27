public class User {
    private String name;
    private String userName;
    private String password;
    private String phoneNumber;
    private  String address;
    public User(String userName, String name,String password, String phoneNumber, String address){
        this.userName=userName;
        this.name=name;
        this.password=password;
        this.phoneNumber=phoneNumber;
        this.address=address;
    }

    public String getUserName() {
        return userName;
    }
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
