package warungmakan;

public class cMakanan extends cBarang {
    private String jenis;
    
    public cMakanan(String kode, String nama, double harga, String jenis) {
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
