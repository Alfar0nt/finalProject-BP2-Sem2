package warungmakan;

public class cMakanan extends cMenu {
    private String kategori;
    public cMakanan(String n, double h, String k) {
        super(n, h); // Memanggil constructor parent
        this.kategori = k;
    }
    public String toString() {
        return "[" + kategori + "] " + nama + " - Rp" + harga;
    }
}