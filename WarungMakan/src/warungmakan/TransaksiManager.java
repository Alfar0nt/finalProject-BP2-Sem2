package warungmakan;

import java.util.Scanner;

public class TransaksiManager {
    private MenuManager menuManager;
    private PelangganManager pelangganManager;
    private Pesanan pesananAktif;
    private Scanner scanner;
    private static int counterTransaksi = 1;
    
    public TransaksiManager(MenuManager menuManager) {
        this.menuManager = menuManager;
        this.scanner = new Scanner(System.in);
    }
    
    public void setPelangganManager(PelangganManager pelangganManager) {
        this.pelangganManager = pelangganManager;
    }
    
    public void mulaiTransaksiBaru() {
        System.out.println("\n=== TRANSAKSI PENJUALAN BARU ===");
        System.out.println("1. Input nama pembeli langsung");
        System.out.println("2. Cari pelanggan terdaftar");
        System.out.print("Pilih (1/2): ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();
        
        String namaPembeli;
        Pelanggan pelanggan = null;
        
        if (pilihan == 2 && pelangganManager != null) {
            pelangganManager.tampilkanDaftarPelanggan();
            System.out.print("Masukkan kode pelanggan: ");
            String kode = scanner.nextLine();
            pelanggan = pelangganManager.cariPelanggan(kode);
            
            if (pelanggan != null) {
                namaPembeli = pelanggan.getNama();
                System.out.println("Pelanggan ditemukan: " + pelanggan.getNama());
            } else {
                System.out.println("Pelanggan tidak ditemukan, input nama manual.");
                System.out.print("Nama Pembeli: ");
                namaPembeli = scanner.nextLine();
            }
        } else {
            System.out.print("Nama Pembeli: ");
            namaPembeli = scanner.nextLine();
        }
        
        String nomorTransaksi = generateNomorTransaksi();
        pesananAktif = new Pesanan(nomorTransaksi, namaPembeli);
        
        System.out.println("Transaksi dimulai dengan No: " + nomorTransaksi);
        
        menuTransaksi(pelanggan);
    }
    
    private String generateNomorTransaksi() {
        return String.format("TRX%03d", counterTransaksi++);
    }
    
    private void menuTransaksi(Pelanggan pelanggan) {
        int pilihan = 0;
        
        do {
            System.out.println("\n=== MENU TRANSAKSI ===");
            System.out.println("1. Tambah Item");
            System.out.println("2. Hapus Item");
            System.out.println("3. Lihat Daftar Belanja");
            System.out.println("4. Bayar");
            System.out.println("0. Selesai Transaksi");
            System.out.print("Pilih: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();
            
            switch (pilihan) {
                case 1:
                    tambahItem();
                    break;
                case 2:
                    hapusItem();
                    break;
                case 3:
                    pesananAktif.tampilkanDaftarBelanja();
                    break;
                case 4:
                    prosesPembayaran(pelanggan);
                    pilihan = 0;
                    break;
                case 0:
                    System.out.println("Transaksi dibatalkan.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 0);
    }
    
    private void tambahItem() {
        System.out.println("\n=== TAMBAH ITEM PESANAN ===");
        menuManager.tampilkanDaftarBarang();
        
        System.out.print("Masukkan kode barang: ");
        String kode = scanner.nextLine();
        
        Barang barang = menuManager.cariBarang(kode);
        if (barang == null) {
            System.out.println("Barang tidak ditemukan!");
            return;
        }
        
        System.out.print("Jumlah: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();
        
        if (jumlah <= 0) {
            System.out.println("Jumlah harus lebih dari 0!");
            return;
        }
        
        pesananAktif.tambahItem(barang, jumlah);
        System.out.println("Item berhasil ditambahkan!");
        pesananAktif.tampilkanDaftarBelanja();
    }
    
    private void hapusItem() {
        pesananAktif.tampilkanDaftarBelanja();
        
        if (pesananAktif.getDaftarItem().isEmpty()) {
            System.out.println("Belum ada item yang dipesan.");
            return;
        }
        
        System.out.print("Masukkan nomor item yang akan dihapus: ");
        int nomor = scanner.nextInt();
        scanner.nextLine();
        
        if (nomor < 1 || nomor > pesananAktif.getDaftarItem().size()) {
            System.out.println("Nomor item tidak valid!");
            return;
        }
        
        System.out.println("Apakah Anda yakin ingin menghapus item ini? (Y/N)");
        String konfirmasi = scanner.nextLine();
        
        if (konfirmasi.equalsIgnoreCase("Y")) {
            pesananAktif.hapusItem(nomor - 1);
            System.out.println("Item berhasil dihapus!");
            pesananAktif.tampilkanDaftarBelanja();
        } else {
            System.out.println("Penghapusan dibatalkan.");
        }
    }
    
    private void prosesPembayaran(Pelanggan pelanggan) {
        pesananAktif.tampilkanDaftarBelanja();
        
        if (pesananAktif.getDaftarItem().isEmpty()) {
            System.out.println("Belum ada item yang dipesan!");
            return;
        }
        
        double totalBiaya = pesananAktif.getTotalBiaya();
        double diskon = 0;
        
        if (pelanggan != null && pelanggan.isPelangganTetap()) {
            diskon = pelanggan.hitungDiskon(totalBiaya);
            totalBiaya -= diskon;
            System.out.println("Diskon pelanggan tetap (10%): Rp" + (long)diskon);
        }
        
        System.out.printf("Total yang harus dibayar: Rp%,-2.0f%n", totalBiaya);
        System.out.print("Masukkan uang pembayaran: Rp");
        double uangBayar = scanner.nextDouble();
        scanner.nextLine();
        
        if (uangBayar < totalBiaya) {
            System.out.println("Uang pembayaran kurang!");
            return;
        }
        
        pesananAktif.cetakStruk(uangBayar, diskon);
        pesananAktif.setSelesai(true);
        
        // Update total belanja pelanggan
        if (pelanggan != null) {
            pelanggan.tambahBelanja(pesananAktif.getTotalBiaya() - diskon);
        }
        
        System.out.println("\nTransaksi selesai. Terima kasih!");
    }
    
    public Pesanan getPesananAktif() { return pesananAktif; }
}
