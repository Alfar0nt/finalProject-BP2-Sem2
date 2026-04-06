package warungmakan;

public class cBuyer {
    private int id;
    private String name;
    private String address;

    public cBuyer(int i, String n, String a) {
        this.id = i; 
        this.name = n; 
        this.address = a;
    }
    public String getName() { return name; }
    public String ToString() {
        return "(" + id + ") " + name + " [" + address + "]";
    }
}