package warungmakan;

public class cPelanggan {
    private String kodePelanggan;
    private String nama;
    private String alamat;
    private String nomorMeja;
    private double totalBelanja;
    private boolean pelangganTetap;
    
    public cPelanggan(String kodePelanggan, String nama, String alamat, String nomorMeja, boolean pelangganTetap) {
        this.kodePelanggan = kodePelanggan;
        this.nama = nama;
        this.alamat = alamat;
        this.nomorMeja = nomorMeja;
        this.totalBelanja = 0;
        this.pelangganTetap = pelangganTetap;
    }
    
    public String getKodePelanggan() { return kodePelanggan; }
    public String getNama() { return nama; }
    public String getAlamat() { return alamat; }
    public String getNomorMeja() { return nomorMeja; }
    public double getTotalBelanja() { return totalBelanja; }
    public boolean isPelangganTetap() { return pelangganTetap; }
    
    public void setNama(String nama) { this.nama = nama; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public void setNomorMeja(String nomorMeja) { this.nomorMeja = nomorMeja; }
    public void setPelangganTetap(boolean pelangganTetap) { this.pelangganTetap = pelangganTetap; }
    
    public void tambahBelanja(double jumlah) {
        this.totalBelanja += jumlah;
    }
    
    public double hitungDiskon(double totalBiaya) {
        if (pelangganTetap) {
            return totalBiaya * 0.1; // Diskon 10% untuk pelanggan tetap
        }
        return 0;
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s - Meja %s (%s) - Total: Rp%,-2.0f", 
            kodePelanggan, nama, nomorMeja, 
            pelangganTetap ? "Pelanggan Tetap" : "Biasa", 
            totalBelanja);
    }
}
