package warungmakan;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager {
    private ArrayList<Barang> daftarBarang;
    private Scanner scanner;
    
    public MenuManager() {
        this.daftarBarang = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        initializeDefaultMenu();
    }
    
    private void initializeDefaultMenu() {
        daftarBarang.add(new Makanan("M001", "Ayam Bakar Solo", 25000, "Paket"));
        daftarBarang.add(new Makanan("M002", "Ayam Penyet", 23000, "Paket"));
        daftarBarang.add(new Makanan("M003", "Gurami Bakar", 45000, "Ala Carte"));
        daftarBarang.add(new Makanan("M004", "Kangkung", 12000, "Sayur"));
        daftarBarang.add(new Minuman("D001", "Es Teh Manis", 5000, "Medium"));
        daftarBarang.add(new Minuman("D002", "Jus Jeruk", 12000, "Large"));
        daftarBarang.add(new Minuman("D003", "Air Mineral", 3000, "Small"));
    }
    
    public void tambahBarang() {
        System.out.println("\n=== TAMBAH BARANG BARU ===");
        System.out.print("Kode Barang: ");
        String kode = scanner.nextLine();
        
        if (cariBarang(kode) != null) {
            System.out.println("Kode barang sudah ada!");
            return;
        }
        
        System.out.print("Nama Barang: ");
        String nama = scanner.nextLine();
        System.out.print("Harga: ");
        double harga = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.println("Jenis Barang:");
        System.out.println("1. Makanan");
        System.out.println("2. Minuman");
        System.out.print("Pilih (1/2): ");
        int jenis = scanner.nextInt();
        scanner.nextLine();
        
        if (jenis == 1) {
            System.out.print("Jenis Makanan: ");
            String jenisMakanan = scanner.nextLine();
            daftarBarang.add(new Makanan(kode, nama, harga, jenisMakanan));
        } else if (jenis == 2) {
            System.out.print("Ukuran Minuman: ");
            String ukuran = scanner.nextLine();
            daftarBarang.add(new Minuman(kode, nama, harga, ukuran));
        } else {
            System.out.println("Pilihan tidak valid!");
            return;
        }
        
        System.out.println("Barang berhasil ditambahkan!");
    }
    
    public void ubahBarang() {
        System.out.println("\n=== UBAH DATA BARANG ===");
        tampilkanDaftarBarang();
        System.out.print("Masukkan kode barang yang akan diubah: ");
        String kode = scanner.nextLine();
        
        Barang barang = cariBarang(kode);
        if (barang == null) {
            System.out.println("Barang tidak ditemukan!");
            return;
        }
        
        System.out.println("Data saat ini: " + barang.toString());
        System.out.println("Apakah Anda ingin melanjutkan perubahan? (Y/N)");
        String konfirmasi = scanner.nextLine();
        
        if (!konfirmasi.equalsIgnoreCase("Y")) {
            System.out.println("Perubahan dibatalkan.");
            return;
        }
        
        System.out.print("Nama baru (kosongkan jika tidak ingin mengubah): ");
        String namaBaru = scanner.nextLine();
        System.out.print("Harga baru (0 jika tidak ingin mengubah): ");
        double hargaBaru = scanner.nextDouble();
        scanner.nextLine();
        
        if (!namaBaru.isEmpty()) {
            barang.setNama(namaBaru);
        }
        if (hargaBaru > 0) {
            barang.setHarga(hargaBaru);
        }
        
        System.out.println("Data barang berhasil diubah!");
    }
    
    public void hapusBarang() {
        System.out.println("\n=== HAPUS BARANG ===");
        tampilkanDaftarBarang();
        System.out.print("Masukkan kode barang yang akan dihapus: ");
        String kode = scanner.nextLine();
        
        Barang barang = cariBarang(kode);
        if (barang == null) {
            System.out.println("Barang tidak ditemukan!");
            return;
        }
        
        System.out.println("Data yang akan dihapus: " + barang.toString());
        System.out.println("Apakah Anda yakin ingin menghapus? (Y/N)");
        String konfirmasi = scanner.nextLine();
        
        if (konfirmasi.equalsIgnoreCase("Y")) {
            daftarBarang.remove(barang);
            System.out.println("Barang berhasil dihapus!");
        } else {
            System.out.println("Penghapusan dibatalkan.");
        }
    }
    
    public void tampilkanDaftarBarang() {
        System.out.println("\n=== DAFTAR MENU WARUNG MAKAN WONG SOLO ===");
        if (daftarBarang.isEmpty()) {
            System.out.println("Belum ada barang yang terdaftar.");
            return;
        }
        
        System.out.println("--------------------------------------------------");
        System.out.printf("%-6s %-20s %-10s %-10s%n", "KODE", "NAMA", "KATEGORI", "HARGA");
        System.out.println("--------------------------------------------------");
        
        for (int i = 0; i < daftarBarang.size(); i++) {
            Barang barang = daftarBarang.get(i);
            System.out.printf("%-6s %-20s %-10s Rp%,-10.0f%n", 
                barang.getKode(), 
                barang.getNama(), 
                barang.getKategori(), 
                barang.getHarga());
        }
        System.out.println("--------------------------------------------------");
    }
    
    public Barang cariBarang(String kode) {
        for (Barang barang : daftarBarang) {
            if (barang.getKode().equalsIgnoreCase(kode)) {
                return barang;
            }
        }
        return null;
    }
    
    public Barang cariBarangByNama(String nama) {
        for (Barang barang : daftarBarang) {
            if (barang.getNama().equalsIgnoreCase(nama)) {
                return barang;
            }
        }
        return null;
    }
    
    public ArrayList<Barang> getDaftarBarang() {
        return daftarBarang;
    }
}
