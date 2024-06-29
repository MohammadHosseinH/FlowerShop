public class Manager extends User {
    private double salesAmount;
    public Manager(String userName, String name,String password, String phoneNumber, String address, double salesAmount){
        super(userName, name, password, phoneNumber, address);
        this.salesAmount=salesAmount;
    }
    public double getSalesAmount() {
        return salesAmount;
    }
}
