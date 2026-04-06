package warungmakan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LaporanManager {
    private ArrayList<Pesanan> daftarTransaksiHarian;
    private HashMap<String, Double> totalPemasukanPerBarang;
    private double totalPemasukanHarian;
    
    public LaporanManager() {
        this.daftarTransaksiHarian = new ArrayList<>();
        this.totalPemasukanPerBarang = new HashMap<>();
        this.totalPemasukanHarian = 0;
    }
    
    public void tambahTransaksi(Pesanan pesanan) {
        if (pesanan != null && pesanan.isSelesai()) {
            daftarTransaksiHarian.add(pesanan);
            totalPemasukanHarian += pesanan.getTotalBiaya();
            
            // Update pemasukan per barang
            for (ItemPesanan item : pesanan.getDaftarItem()) {
                String namaBarang = item.getBarang().getNama();
                double subtotal = item.getSubtotal();
                
                totalPemasukanPerBarang.put(namaBarang, 
                    totalPemasukanPerBarang.getOrDefault(namaBarang, 0.0) + subtotal);
            }
        }
    }
    
    public void tampilkanLaporanHarian() {
        System.out.println("\n=== LAPORAN PEMASUKAN HARIAN ===");
        System.out.println("Warung Makan Wong Solo");
        System.out.println("Tanggal: " + java.time.LocalDate.now());
        System.out.println("==========================================");
        
        System.out.printf("Total Pendapatan : Rp%,-2.0f%n", totalPemasukanHarian);
        System.out.println("==========================================");
        
        if (totalPemasukanPerBarang.isEmpty()) {
            System.out.println("Belum ada transaksi hari ini.");
            return;
        }
        
        // Sort by revenue descending
        ArrayList<Map.Entry<String, Double>> sortedEntries = new ArrayList<>(totalPemasukanPerBarang.entrySet());
        sortedEntries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        
        System.out.println("Rekap Pemasukan per Menu:");
        int rank = 1;
        for (Map.Entry<String, Double> entry : sortedEntries) {
            System.out.printf("%d. %-20s : Rp%,-2.0f%n", 
                rank++, entry.getKey(), entry.getValue());
        }
        
        System.out.println("==========================================");
        System.out.println("Total Transaksi: " + daftarTransaksiHarian.size());
        
        if (!daftarTransaksiHarian.isEmpty()) {
            double rataRata = totalPemasukanHarian / daftarTransaksiHarian.size();
            System.out.printf("Rata-rata per transaksi: Rp%,-2.0f%n", rataRata);
        }
        
        System.out.println("==========================================");
    }
    
    public void tampilkanDetailTransaksi() {
        System.out.println("\n=== DETAIL TRANSAKSI HARIAN ===");
        
        if (daftarTransaksiHarian.isEmpty()) {
            System.out.println("Belum ada transaksi hari ini.");
            return;
        }
        
        System.out.println("--------------------------------------------------");
        System.out.printf("%-12s %-20s %-15s %-10s%n", 
            "NO. TRX", "PEMBELI", "JUMLAH ITEM", "TOTAL");
        System.out.println("--------------------------------------------------");
        
        for (Pesanan pesanan : daftarTransaksiHarian) {
            System.out.printf("%-12s %-20s %-15d Rp%,-10.0f%n", 
                pesanan.getNomorTransaksi(),
                pesanan.getNamaPembeli(),
                pesanan.getDaftarItem().size(),
                pesanan.getTotalBiaya());
        }
        
        System.out.println("--------------------------------------------------");
        System.out.printf("%-47s Rp%,-10.0f%n", "TOTAL", totalPemasukanHarian);
        System.out.println("--------------------------------------------------");
    }
    
    public void resetHarian() {
        daftarTransaksiHarian.clear();
        totalPemasukanPerBarang.clear();
        totalPemasukanHarian = 0;
    }
    
    public double getTotalPemasukanHarian() { return totalPemasukanHarian; }
    public int getJumlahTransaksi() { return daftarTransaksiHarian.size(); }
}
