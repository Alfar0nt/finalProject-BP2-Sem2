package warungmakan;

public class Minuman extends Barang {
    private String ukuran;
    
    public Minuman(String kode, String nama, double harga, String ukuran) {
        super(kode, nama, harga);
        this.ukuran = ukuran;
    }
    
    public String getUkuran() { return ukuran; }
    public void setUkuran(String ukuran) { this.ukuran = ukuran; }
    
    @Override
    public String getKategori() { return "Minuman"; }
    
    @Override
    public String toString() {
        return "[" + kode + "] " + nama + " (" + ukuran + ") - Rp" + harga;
    }
}
