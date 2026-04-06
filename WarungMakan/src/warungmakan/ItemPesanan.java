package warungmakan;

public class ItemPesanan {
    private cBarang barang;
    private int jumlah;
    
    public ItemPesanan(cBarang barang, int jumlah) {
        this.barang = barang;
        this.jumlah = jumlah;
    }
    
    public cBarang getBarang() { return barang; }
    public int getJumlah() { return jumlah; }
    
    public double getSubtotal() {
        return barang.getHarga() * jumlah;
    }
    
    public void setJumlah(int jumlah) { 
        this.jumlah = jumlah; 
    }
}
