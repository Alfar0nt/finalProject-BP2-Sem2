package warungmakan;

public abstract class Barang {
    protected String nama;
    protected double harga;
    protected String kode;
    
    public Barang(String kode, String nama, double harga) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
    }
    
    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    
    public void setNama(String nama) { this.nama = nama; }
    public void setHarga(double harga) { this.harga = harga; }
    
    public abstract String getKategori();
    public abstract String toString();
}
