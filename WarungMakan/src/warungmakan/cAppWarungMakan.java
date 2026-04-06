package warungmakan;

import java.util.Scanner;

public class cAppWarungMakan {
    private cMenuManager menuManager;
    private cPelangganManager pelangganManager;
    private cTransaksiManager transaksiManager;
    private cLaporanManager laporanManager;
    private Scanner scanner;
    
    public cAppWarungMakan() {
        this.menuManager = new cMenuManager();
        this.pelangganManager = new cPelangganManager();
        this.transaksiManager = new cTransaksiManager(menuManager);
        this.transaksiManager.setPelangganManager(pelangganManager);
        this.laporanManager = new cLaporanManager();
        this.scanner = new Scanner(System.in);
    }
    
    public void run() {
        if (!cLogin.authenticate()) {
            return;
        }
        
        int pilihan = 0;
        do {
            tampilkanMenuUtama();
            System.out.print("Pilih: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();
            
            switch (pilihan) {
                case 1:
                    menuMasterBarang();
                    break;
                case 2:
                    menuTransaksiPenjualan();
                    break;
                case 3:
                    menuMasterPelanggan();
                    break;
                case 4:
                    menuLaporan();
                    break;
                case 5:
                    System.out.println("\nMenampilkan laporan penutupan...");
                    laporanManager.tampilkanLaporanHarian();
                    
                    // Create backup before closing
                    System.out.println("\nMembuat backup data...");
                    cFileManager.backupData();
                    
                    System.out.println("\nTerima kasih telah menggunakan Aplikasi Warung Makan Wong Solo!");
                    System.out.println("Matur thank you brokk!");
                    break;
                default:
                    if (pilihan != 5) {
                        System.out.println("Pilihan tidak valid!");
                    }
            }
        } while (pilihan != 5);
    }
    
    private void tampilkanMenuUtama() {
        System.out.println("\n=== MENU UTAMA WARUNG MAKAN WONG SOLO ===");
        System.out.println("1. Master Barang");
        System.out.println("2. Transaksi Penjualan");
        System.out.println("3. Master Pelanggan");
        System.out.println("4. Laporan");
        System.out.println("5. Selesai");
    }
    
    private void menuMasterBarang() {
        int pilihan = 0;
        do {
            System.out.println("\n=== MASTER BARANG ===");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Ubah Barang");
            System.out.println("3. Hapus Barang");
            System.out.println("4. Lihat Daftar Barang");
            System.out.println("5. Kembali");
            System.out.print("Pilih: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();
            
            switch (pilihan) {
                case 1:
                    menuManager.tambahBarang();
                    cFileManager.saveMenu(menuManager.getDaftarBarang());
                    break;
                case 2:
                    menuManager.ubahBarang();
                    cFileManager.saveMenu(menuManager.getDaftarBarang());
                    break;
                case 3:
                    menuManager.hapusBarang();
                    cFileManager.saveMenu(menuManager.getDaftarBarang());
                    break;
                case 4:
                    menuManager.tampilkanDaftarBarang();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 5);
    }
    
    private void menuTransaksiPenjualan() {
        transaksiManager.mulaiTransaksiBaru();
        
        cPesanan pesananSelesai = transaksiManager.getPesananAktif();
        if (pesananSelesai != null && pesananSelesai.isSelesai()) {
            laporanManager.tambahTransaksi(pesananSelesai);
            
            // Save transaction to file
            cFileManager.saveTransaction(pesananSelesai);
            
            // Update total belanja pelanggan jika ada
            cPelanggan pelanggan = pelangganManager.cariPelanggan(pesananSelesai.getNamaPembeli());
            if (pelanggan != null) {
                pelanggan.tambahBelanja(pesananSelesai.getTotalBiaya());
                cFileManager.savePelanggan(pelangganManager.getDaftarPelanggan());
            }
        }
    }
    
    private void menuMasterPelanggan() {
        int pilihan = 0;
        do {
            System.out.println("\n=== MASTER PELANGGAN ===");
            System.out.println("1. Tambah Pelanggan");
            System.out.println("2. Ubah Pelanggan");
            System.out.println("3. Hapus Pelanggan");
            System.out.println("4. Lihat Daftar Pelanggan");
            System.out.println("5. Laporan Belanja Pelanggan");
            System.out.println("6. Kembali");
            System.out.print("Pilih: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();
            
            switch (pilihan) {
                case 1:
                    pelangganManager.tambahPelanggan();
                    cFileManager.savePelanggan(pelangganManager.getDaftarPelanggan());
                    break;
                case 2:
                    pelangganManager.ubahPelanggan();
                    cFileManager.savePelanggan(pelangganManager.getDaftarPelanggan());
                    break;
                case 3:
                    pelangganManager.hapusPelanggan();
                    cFileManager.savePelanggan(pelangganManager.getDaftarPelanggan());
                    break;
                case 4:
                    pelangganManager.tampilkanDaftarPelanggan();
                    break;
                case 5:
                    pelangganManager.tampilkanLaporanPelanggan();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 6);
    }
    
    private void menuLaporan() {
        int pilihan = 0;
        do {
            System.out.println("\n=== MENU LAPORAN ===");
            System.out.println("1. Laporan Pemasukan Harian");
            System.out.println("2. Detail Transaksi Harian");
            System.out.println("3. Laporan Belanja Pelanggan");
            System.out.println("4. Kembali");
            System.out.print("Pilih: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();
            
            switch (pilihan) {
                case 1:
                    laporanManager.tampilkanLaporanHarian();
                    break;
                case 2:
                    laporanManager.tampilkanDetailTransaksi();
                    break;
                case 3:
                    pelangganManager.tampilkanLaporanPelanggan();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 4);
    }
    
    public static void main(String[] args) {
        cAppWarungMakan app = new cAppWarungMakan();
        app.run();
    }
}
