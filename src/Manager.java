public class Manager extends User {
    private double salesAmount;
    public Manager(String name, String userName,String password, String phoneNumber, String address, double salesAmount){
        super(name, userName, password, phoneNumber, address);
        this.salesAmount=salesAmount;
    }
    public double getSalesAmount() {
        return salesAmount;
    }
}
