package warungmakan;

import java.util.ArrayList;

public class cPesanan {
    private String nomorTransaksi;
    private String namaPembeli;
    private ArrayList<ItemPesanan> daftarItem;
    private double totalBiaya;
    private boolean selesai;
    
    public cPesanan(String nomorTransaksi, String namaPembeli) {
        this.nomorTransaksi = nomorTransaksi;
        this.namaPembeli = namaPembeli;
        this.daftarItem = new ArrayList<>();
        this.totalBiaya = 0;
        this.selesai = false;
    }
    
    public void tambahItem(cBarang barang, int jumlah) {
        if (barang != null && jumlah > 0) {
            daftarItem.add(new ItemPesanan(barang, jumlah));
            hitungTotal();
        }
    }
    
    public void hapusItem(int index) {
        if (index >= 0 && index < daftarItem.size()) {
            daftarItem.remove(index);
            hitungTotal();
        }
    }
    
    private void hitungTotal() {
        totalBiaya = 0;
        for (ItemPesanan item : daftarItem) {
            totalBiaya += item.getSubtotal();
        }
    }
    
    public void tampilkanDaftarBelanja() {
        System.out.println("\n=== DAFTAR BELANJA ===");
        System.out.println("No. Transaksi: " + nomorTransaksi);
        System.out.println("Pembeli: " + namaPembeli);
        System.out.println("--------------------------------------------------");
        System.out.printf("%-3s %-20s %-8s %-10s %-10s%n", "No", "Nama Barang", "Jumlah", "Harga", "Subtotal");
        System.out.println("--------------------------------------------------");
        
        for (int i = 0; i < daftarItem.size(); i++) {
            ItemPesanan item = daftarItem.get(i);
            cBarang barang = item.getBarang();
            System.out.printf("%-3d %-20s %-8d Rp%,-8.0f Rp%,-10.0f%n", 
                (i + 1), 
                barang.getNama(), 
                item.getJumlah(), 
                barang.getHarga(), 
                item.getSubtotal());
        }
        
        System.out.println("--------------------------------------------------");
        System.out.printf("%-40s Rp%,-10.0f%n", "TOTAL SEMENTARA:", totalBiaya);
        System.out.println("--------------------------------------------------");
    }
    
    public boolean isSelesai() { return selesai; }
    public void setSelesai(boolean selesai) { this.selesai = selesai; }
    
    public String getNomorTransaksi() { return nomorTransaksi; }
    public String getNamaPembeli() { return namaPembeli; }
    public double getTotalBiaya() { return totalBiaya; }
    public ArrayList<ItemPesanan> getDaftarItem() { return daftarItem; }
    
    public void cetakStruk(double uangBayar, double diskon) {
        double totalAkhir = totalBiaya - diskon;
        double kembalian = uangBayar - totalAkhir;
        
        System.out.println("\n================================");
        System.out.println("   STRUK WARUNG MAKAN WONG SOLO");
        System.out.println("================================");
        System.out.println("No. Transaksi: " + nomorTransaksi);
        System.out.println("Pembeli      : " + namaPembeli);
        System.out.println("--------------------------------");
        
        for (ItemPesanan item : daftarItem) {
            cBarang barang = item.getBarang();
            System.out.printf("%-20s %2d x Rp%,-8.0f Rp%,-10.0f%n", 
                barang.getNama(), 
                item.getJumlah(), 
                barang.getHarga(), 
                item.getSubtotal());
        }
        
        System.out.println("--------------------------------");
        System.out.printf("%-20s Rp%,-10.0f%n", "TOTAL BELANJA", totalBiaya);
        if (diskon > 0) {
            System.out.printf("%-20s Rp%,-10.0f%n", "DISKON", diskon);
        }
        System.out.printf("%-20s Rp%,-10.0f%n", "TOTAL BAYAR", totalAkhir);
        System.out.printf("%-20s Rp%,-10.0f%n", "UANG BAYAR", uangBayar);
        System.out.printf("%-20s Rp%,-10.0f%n", "KEMBALIAN", kembalian);
        System.out.println("================================");
        System.out.println("      TERIMA KASIH DATANG KEMBALI      ");
        System.out.println("================================");
    }
    
    public void cetakStruk(double uangBayar) {
        cetakStruk(uangBayar, 0);
    }
}
