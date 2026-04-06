# Warung Makan Wong Solo - Aplikasi Manajemen UMKM

## Deskripsi
Aplikasi ini digunakan untuk membantu pihak pemilik UMKM Warung Makan Wong Solo dalam mencatat transaksi penjualan sehari-hari. Aplikasi mendukung transaksi dengan satu jenis barang atau banyak jenis barang sekaligus, dengan fitur pelanggan tetap dan diskon.

## Fitur Utama

### Spesifikasi Standar (75 Poin)
1. **Class Container & Pewarisan**: Implementasi class induk `Barang` dengan class anak `Makanan` dan `Minuman`
2. **Sistem Login**: Login dan password (admin/wongsolo123) dengan batasan 3 kali percobaan
3. **Master Barang**: Menu lengkap untuk mengelola data barang (Tambah, Ubah, Hapus, Lihat)
4. **Transaksi Penjualan**: Nomor transaksi otomatis, shopping cart, dan proses pembayaran
5. **Konfirmasi Tindakan**: Dialog konfirmasi untuk operasi ubah dan hapus

### Spesifikasi Tambahan (25 Poin)
1. **Master Pembeli**: Manajemen data pelanggan dengan sistem diskon 10% untuk pelanggan tetap
2. **Laporan Harian**: Rekap pemasukan harian dengan breakdown per menu
3. **Laporan Pelanggan**: Ranking total belanja pelanggan dari tertinggi ke terendah
4. **Penyimpanan Data**: Simpan transaksi dan data ke file otomatis
5. **Antarmuka Terminal**: Interaksi user-friendly melalui console

## Struktur Project

```
src/warungmakan/
├── AppWarungMakan.java      # Main application class
├── Login.java              # Authentication system
├── Barang.java             # Parent class for items
├── Makanan.java            # Child class for food items
├── Minuman.java            # Child class for drink items
├── MenuManager.java        # Menu/Barang management
├── Pelanggan.java           # Customer class
├── PelangganManager.java    # Customer management
├── Pesanan.java             # Transaction/order class
├── ItemPesanan.java         # Order item class
├── TransaksiManager.java    # Transaction management
├── LaporanManager.java      # Reporting system
└── FileManager.java         # File storage system
```

## Cara Menjalankan

1. Compile project:
```bash
javac -cp src src/warungmakan/*.java
```

2. Jalankan aplikasi:
```bash
java -cp src warungmakan.AppWarungMakan
```

## Login Default
- **Username**: admin
- **Password**: wongsolo123

## Menu Utama

1. **Master Barang** - Kelola menu makanan dan minuman
2. **Transaksi Penjualan** - Proses pesanan dan pembayaran
3. **Master Pelanggan** - Kelola data pelanggan
4. **Laporan** - Lihat laporan penjualan dan pelanggan
5. **Selesai** - Tutup aplikasi dengan backup data

## Fitur Diskon
- Pelanggan tetap mendapatkan diskon 10% dari total belanja
- Diskon otomatis diterapkan saat pembayaran

## Penyimpanan Data
- Transaksi otomatis disimpan ke `transactions.txt`
- Data menu disimpan ke `menu.txt`
- Data pelanggan disimpan ke `pelanggan.txt`
- Backup otomatis dibuat saat aplikasi ditutup

## Contoh Output Laporan

### Laporan Pemasukan Harian
```
=== LAPORAN PEMASUKAN HARIAN ===
Warung Makan Wong Solo
Tanggal: 2026-04-06
==========================================
Total Pendapatan : Rp480.000
==========================================
Rekap Pemasukan per Menu:
1. Ayam Bakar Solo    : Rp230.000
2. Gurami Bakar       : Rp150.000
3. Es Teh Manis      : Rp85.000
4. Kangkung           : Rp15.000
==========================================
```

### Laporan Pelanggan
```
=== LAPORAN TOTAL BELANJA PELANGGAN ===
--------------------------------------------------
NO   NAMA PELANGGAN      TOTAL BELANJA
--------------------------------------------------
1    Budi Santoso       Rp35.000
2    Siti Nurhaliza     Rp15.000
--------------------------------------------------
```

## Teknologi
- Java 8+
- File I/O untuk penyimpanan data
- Object-oriented programming dengan inheritance
- ArrayList untuk data collection

## Author
Project ini dikembangkan untuk memenuhi tugas UMKM Management System.
