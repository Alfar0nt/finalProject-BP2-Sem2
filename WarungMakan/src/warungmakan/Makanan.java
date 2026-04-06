package warungmakan;

public class Makanan extends Barang {
    private String jenis;
    
    public Makanan(String kode, String nama, double harga, String jenis) {
        super(kode, nama, harga);
        this.jenis = jenis;
    }
    
    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }
    
    @Override
    public String getKategori() { return "Makanan"; }
    
    @Override
    public String toString() {
        return "[" + kode + "] " + nama + " (" + jenis + ") - Rp" + harga;
    }
}
