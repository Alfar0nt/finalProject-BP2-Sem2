package warungmakan;
import java.util.Scanner;

public class WarungMakan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        cMenu3 menuBox = new cMenu3(cMenu2.getDaftarMenu());
        cTransaksi transaksi = new cTransaksi();
        
        System.out.print("Masukkan Nama Pembeli: ");
        String namaIn = sc.nextLine();
        cBuyer pelanggan = new cBuyer(101, namaIn, "Surabaya");

        int pilihan = 0;
        do {
            System.out.println("\n=== UTAMA: WONG SOLO ===");
            System.out.println("1. Daftar Menu\n2. Pesan Makanan\n3. Kelola Menu (Admin)\n4. Selesai");
            System.out.print("Pilih: ");
            pilihan = sc.nextInt();
            sc.nextLine(); 

            switch(pilihan) {
                case 1:
                    menuBox.tampilkanMenu();
                    break;
                case 2:
                    System.out.print("Nama Menu: ");
                    String cari = sc.nextLine();
                    cMakanan item = menuBox.cariMenu(cari);
                    if (item != null) {
                        System.out.print("Jumlah: ");
                        int q = sc.nextInt();
                        transaksi.tambahPesanan(item, pelanggan, q);
                        transaksi.cetakStruk();
                    } else System.out.println("Menu tidak ada!");
                    break;
                case 3:
                    System.out.println("\n--- KELOLA MENU (CRUD) ---");
                    System.out.println("1. Tambah Menu\n2. Hapus Menu\n3. Kembali");
                    System.out.print("Pilih: ");
                    int subPilih = sc.nextInt();
                    sc.nextLine();
                    
                    if(subPilih == 1) { // CREATE
                        System.out.print("Nama Menu Baru: ");
                        String n = sc.nextLine();
                        System.out.print("Harga: ");
                        double h = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Kategori (Paket/Minuman): ");
                        String k = sc.nextLine();
                        menuBox.tambahMenu(new cMakanan(n, h, k));
                    } else if(subPilih == 2) { // DELETE
                        System.out.print("Nama Menu yang Dihapus: ");
                        String hapus = sc.nextLine();
                        menuBox.hapusMenu(hapus);
                    }
                    break;
            }
        } while (pilihan != 4);
        System.out.println("Matur Nuwun!");
    }
}