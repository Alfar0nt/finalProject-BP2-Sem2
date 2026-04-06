package warungmakan;

import java.util.ArrayList;
import java.util.Scanner;

public class cPelangganManager {
    private ArrayList<cPelanggan> daftarPelanggan;
    private Scanner scanner;
    
    public cPelangganManager() {
        this.daftarPelanggan = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        initializeDefaultPelanggan();
    }
    
    private void initializeDefaultPelanggan() {
        daftarPelanggan.add(new cPelanggan("P001", "Budi Santoso", "Surabaya", "A1", true));
        daftarPelanggan.add(new cPelanggan("P002", "Siti Nurhaliza", "Malang", "B2", true));
        daftarPelanggan.add(new cPelanggan("P003", "Ahmad Fauzi", "Sidoarjo", "C3", false));
    }
    
    public void tambahPelanggan() {
        System.out.println("\n=== TAMBAH PELANGGAN BARU ===");
        System.out.print("Kode Pelanggan: ");
        String kode = scanner.nextLine();
        
        if (cariPelanggan(kode) != null) {
            System.out.println("Kode pelanggan sudah ada!");
            return;
        }
        
        System.out.print("Nama Pelanggan: ");
        String nama = scanner.nextLine();
        System.out.print("Alamat: ");
        String alamat = scanner.nextLine();
        System.out.print("Nomor Meja: ");
        String nomorMeja = scanner.nextLine();
        
        System.out.print("Apakah pelanggan tetap? (Y/N): ");
        boolean pelangganTetap = scanner.nextLine().equalsIgnoreCase("Y");
        
        daftarPelanggan.add(new cPelanggan(kode, nama, alamat, nomorMeja, pelangganTetap));
        System.out.println("Pelanggan berhasil ditambahkan!");
    }
    
    public void ubahPelanggan() {
        System.out.println("\n=== UBAH DATA PELANGGAN ===");
        tampilkanDaftarPelanggan();
        System.out.print("Masukkan kode pelanggan yang akan diubah: ");
        String kode = scanner.nextLine();
        
        cPelanggan pelanggan = cariPelanggan(kode);
        if (pelanggan == null) {
            System.out.println("Pelanggan tidak ditemukan!");
            return;
        }
        
        System.out.println("Data saat ini: " + pelanggan.toString());
        System.out.println("Apakah Anda ingin melanjutkan perubahan? (Y/N)");
        String konfirmasi = scanner.nextLine();
        
        if (!konfirmasi.equalsIgnoreCase("Y")) {
            System.out.println("Perubahan dibatalkan.");
            return;
        }
        
        System.out.print("Nama baru (kosongkan jika tidak ingin mengubah): ");
        String namaBaru = scanner.nextLine();
        System.out.print("Alamat baru (kosongkan jika tidak ingin mengubah): ");
        String alamatBaru = scanner.nextLine();
        System.out.print("Nomor Meja baru (kosongkan jika tidak ingin mengubah): ");
        String mejaBaru = scanner.nextLine();
        System.out.print("Pelanggan Tetap? (Y/N, kosongkan jika tidak ingin mengubah): ");
        String tetapBaru = scanner.nextLine();
        
        if (!namaBaru.isEmpty()) {
            pelanggan.setNama(namaBaru);
        }
        if (!alamatBaru.isEmpty()) {
            pelanggan.setAlamat(alamatBaru);
        }
        if (!mejaBaru.isEmpty()) {
            pelanggan.setNomorMeja(mejaBaru);
        }
        if (!tetapBaru.isEmpty()) {
            pelanggan.setPelangganTetap(tetapBaru.equalsIgnoreCase("Y"));
        }
        
        System.out.println("Data pelanggan berhasil diubah!");
    }
    
    public void hapusPelanggan() {
        System.out.println("\n=== HAPUS PELANGGAN ===");
        tampilkanDaftarPelanggan();
        System.out.print("Masukkan kode pelanggan yang akan dihapus: ");
        String kode = scanner.nextLine();
        
        cPelanggan pelanggan = cariPelanggan(kode);
        if (pelanggan == null) {
            System.out.println("Pelanggan tidak ditemukan!");
            return;
        }
        
        System.out.println("Data yang akan dihapus: " + pelanggan.toString());
        System.out.println("Apakah Anda yakin ingin menghapus? (Y/N)");
        String konfirmasi = scanner.nextLine();
        
        if (konfirmasi.equalsIgnoreCase("Y")) {
            daftarPelanggan.remove(pelanggan);
            System.out.println("Pelanggan berhasil dihapus!");
        } else {
            System.out.println("Penghapusan dibatalkan.");
        }
    }
    
    public void tampilkanDaftarPelanggan() {
        System.out.println("\n=== DAFTAR PELANGGAN WARUNG MAKAN WONG SOLO ===");
        if (daftarPelanggan.isEmpty()) {
            System.out.println("Belum ada pelanggan yang terdaftar.");
            return;
        }
        
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("%-6s %-20s %-15s %-8s %-15s %-15s%n", 
            "KODE", "NAMA", "ALAMAT", "MEJA", "JENIS", "TOTAL BELANJA");
        System.out.println("----------------------------------------------------------------------------------------");
        
        for (cPelanggan pelanggan : daftarPelanggan) {
            System.out.printf("%-6s %-20s %-15s %-8s %-15s Rp%,-15.0f%n", 
                pelanggan.getKodePelanggan(),
                pelanggan.getNama(),
                pelanggan.getAlamat(),
                pelanggan.getNomorMeja(),
                pelanggan.isPelangganTetap() ? "Tetap" : "Biasa",
                pelanggan.getTotalBelanja());
        }
        System.out.println("----------------------------------------------------------------------------------------");
    }
    
    public cPelanggan cariPelanggan(String kode) {
        for (cPelanggan pelanggan : daftarPelanggan) {
            if (pelanggan.getKodePelanggan().equalsIgnoreCase(kode)) {
                return pelanggan;
            }
        }
        return null;
    }
    
    public void tampilkanLaporanPelanggan() {
        System.out.println("\n=== LAPORAN TOTAL BELANJA PELANGGAN ===");
        
        if (daftarPelanggan.isEmpty()) {
            System.out.println("Belum ada pelanggan yang terdaftar.");
            return;
        }
        
        // Sort by total belanja descending
        daftarPelanggan.sort((p1, p2) -> Double.compare(p2.getTotalBelanja(), p1.getTotalBelanja()));
        
        System.out.println("--------------------------------------------------");
        System.out.printf("%-3s %-20s %-15s%n", "NO", "NAMA PELANGGAN", "TOTAL BELANJA");
        System.out.println("--------------------------------------------------");
        
        int rank = 1;
        for (cPelanggan pelanggan : daftarPelanggan) {
            if (pelanggan.getTotalBelanja() > 0) {
                System.out.printf("%-3d %-20s Rp%,-15.0f%n", 
                    rank++, pelanggan.getNama(), pelanggan.getTotalBelanja());
            }
        }
        
        System.out.println("--------------------------------------------------");
    }
    
    public ArrayList<cPelanggan> getDaftarPelanggan() {
        return daftarPelanggan;
    }
}
