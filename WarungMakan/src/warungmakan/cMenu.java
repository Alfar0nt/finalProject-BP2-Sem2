package warungmakan;

public class cMenu {
    protected String nama;
    protected double harga;

    public cMenu(String n, double h) {
        this.nama = n; 
        this.harga = h;
    }
    public String getNama() { return nama; }
    public double getHarga() { return harga; }
}