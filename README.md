# Warung Makan Wong Solo - Java Application

Aplikasi kasir restoran berbasis terminal untuk Warung Makan Wong Solo dengan fitur lengkap manajemen menu, pelanggan, transaksi, dan laporan.

## Fitur Utama

### Sistem Login
- Username: admin
- Password: wongsolo123
- Proteksi dengan batas percobaan 3x

### Master Menu
- CRUD (Create, Read, Update, Delete) menu makanan & minuman
- Kategori: Makanan (Paket/Ala Carte/Sayur) & Minuman (Small/Medium/Large)
- Kode unik untuk setiap item

### Master Pelanggan
- Manajemen data pelanggan (tetap & biasa)
- Diskon otomatis 10% untuk pelanggan tetap
- Tracking total belanja per pelanggan
- Laporan spending pelanggan
- Tampilan sederhana tanpa kolom "jenis"

### Transaksi Penjualan
- Shopping cart dengan multiple items
- Opsi pelanggan terdaftar atau walk-in
- Perhitungan otomatis diskon pelanggan tetap
- Struk detail dengan format yang rapi

### Laporan
- Laporan pendapatan harian
- Breakdown per item terlaris
- Laporan spending pelanggan
- Export data ke file teks

## Struktur Project (Simplified)

```
finalProjectSem2/
├── WarungMakan/
│   ├── src/
│   │   └── warungmakan/           # 12 files (dari 19 files)
│   │       ├── cAppWarungMakan.java     # Main Application
│   │       ├── cBarang.java             # Base Class (Abstract)
│   │       ├── cMakanan.java           # Food Class (+jenis)
│   │       ├── cMinuman.java           # Drink Class  
│   │       ├── cMenuManager.java        # Menu Management
│   │       ├── cPelanggan.java          # Customer Class (simplified)
│   │       ├── cPelangganManager.java   # Customer Management
│   │       ├── cPesanan.java           # Transaction Class
│   │       ├── ItemPesanan.java        # Order Item Class
│   │       ├── cTransaksiManager.java  # Transaction Management
│   │       ├── cLaporanManager.java    # Report Management
│   │       ├── cLogin.java             # Authentication
│   │       └── cFileManager.java       # File Storage
│   └── README.md
├── .gitignore
├── cleanup_notes.md
├── FILE_MANAGEMENT.md
└── README.md
```

## Deskripsi File & Class

### Main Class
- cAppWarungMakan - Entry point aplikasi, mengkoordinasi semua manager

### Core Classes
- cBarang - Abstract base class untuk semua menu item
  - Atribut: kode, nama, harga
  - Method abstract: getKategori(), tampilInfo()

- cMakanan - Class untuk item makanan (extends cBarang)
  - Atribut tambahan: jenis (Paket/Ala Carte/Sayur) - Dipertahankan

- cMinuman - Class untuk item minuman (extends cBarang)
  - Atribut tambahan: ukuran (Small/Medium/Large)

### Manager Classes
- cMenuManager - Mengelola CRUD menu makanan & minuman
- cPelangganManager - Mengelola data pelanggan dan diskon
  - Tampilan disederhanakan - tanpa kolom "jenis"
- cTransaksiManager - Mengelola alur transaksi & shopping cart
- cLaporanManager - Menggenerate laporan penjualan & analitik

### Transaction Classes
- cPesanan - Representasi satu transaksi/order
  - Mengelola multiple items, perhitungan diskon, struk

- ItemPesanan - Item dalam satu transaksi
  - Hubungan barang + jumlah + subtotal

### Support Classes
- cPelanggan - Data pelanggan dengan status tetap/biasa
  - Simplified - tidak ada atribut "jenis" berlebihan
  - Method diskon otomatis 10% untuk pelanggan tetap

- cLogin - Sistem autentikasi sederhana
- cFileManager - Penyimpanan & backup data ke file teks

## Cara Menjalankan

### Compile
```bash
cd WarungMakan
javac -d . src/warungmakan/*.java
```

### Run
```bash
java warungmakan.cAppWarungMakan
```

## File Storage

Aplikasi otomatis menyimpan data ke file teks:
- transactions.txt - Riwayat transaksi
- menu.txt - Data menu items
- pelanggan.txt - Data pelanggan
- backup_*.txt - Backup otomatis saat exit

## Teknologi

- Language: Java (CLI Application)
- Design: Object-Oriented Programming (OOP)
- Pattern: Manager Pattern untuk setiap modul
- Storage: File teks dengan format delimiter
- Architecture: Layered Architecture

## Kriteria Tambahan

Penamaan Class dengan Prefix 'c'
- Semua class menggunakan prefix 'c' sesuai konvensi
- Contoh: cBarang, cMenuManager, cPelanggan

Struktur yang Sederhana & Ringkas
- Hanya 12 file Java (dari 19 files)
- Tidak ada file duplikat atau legacy
- Tidak ada atribut "jenis" berlebihan di pelanggan
- Nama file yang deskriptif dan jelas

Dokumentasi Lengkap
- README yang menjelaskan setiap class
- Struktur project yang terorganisir
- Cara penggunaan yang jelas

## File yang Dihapus (Legacy Files)
- cBuyer.java - Class pelanggan lama
- cMenu.java, cMenu2.java, cMenu3.java - Duplikat menu
- cTransaksi.java - Class transaksi lama
- ccPelangganManager.java - Duplikat manager
- WarungMakan.java - Main class lama

## File Management Documentation

Untuk dokumentasi lengkap tentang sistem file management, silakan lihat:
- File: FILE_MANAGEMENT.md
- GitHub: https://github.com/Alfar0nt/finalProject-BP2-Sem2/blob/main/FILE_MANAGEMENT.md

## Repository

GitHub: https://github.com/Alfar0nt/finalProject-BP2-Sem2

---

Anggota Kelompok:
- Faril Akmal Aufa - 25082010039
- Moch. Ryan Saputra - 25082010028  
- Dhiaurrahman Rabbani Harianto - 25082010042

Version: 2.0 (Refactored & Simplified)  
Status: Production Ready
