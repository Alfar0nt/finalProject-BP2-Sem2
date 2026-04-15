# 📁 FILE MANAGEMENT SYSTEM - Warung Makan Wong Solo

## 🗂️ Struktur File Storage

Aplikasi menggunakan sistem file teks (.txt) untuk menyimpan semua data secara persisten. Semua file disimpan di direktori root aplikasi.

### 📄 Daftar File yang Digunakan

#### **1. `transactions.txt`**
- **Fungsi:** Menyimpan riwayat transaksi penjualan
- **Format:** `nomorTransaksi|namaPembeli|totalBiaya|isSelesai`
- **Contoh:** 
  ```
  TRX001|Budi Santoso|45000.0|true
  ITEM|M001|Nasi Goreng|2|30000.0
  ITEM|M003|Es Teh|1|5000.0
  END_TRANSACTION
  TRX002|Siti Nurhaliza|75000.0|true
  ITEM|M002|Mie Ayam|1|35000.0
  ITEM|M003|Es Jeruk|3|15000.0
  END_TRANSACTION
  ```

#### **2. `menu.txt`**
- **Fungsi:** Menyimpan data master barang (makanan & minuman)
- **Format:** `kode|nama|harga|kategori|jenis/ukuran`
- **Contoh:**
  ```
  M001|Nasi Goreng|15000.0|Makanan|Paket
  M002|Mie Ayam|35000.0|Makanan|Ala Carte
  M003|Sayur Lodeh|20000.0|Makanan|Sayur
  D001|Es Teh|5000.0|Minuman|Small
  D002|Es Jeruk|5000.0|Minuman|Medium
  D003|Jus Alpukat|15000.0|Minuman|Large
  ```

#### **3. `pelanggan.txt`**
- **Fungsi:** Menyimpan data master pelanggan
- **Format:** `kode|nama|alamat|nomorMeja|pelangganTetap|totalBelanja`
- **Contoh:**
  ```
  P001|Budi Santoso|Surabaya|A1|true|125000.0
  P002|Siti Nurhaliza|Malang|B2|true|85000.0
  P003|Ahmad Fauzi|Sidoarjo|C3|false|45000.0
  ```

#### **4. `backup_*.txt`**
- **Fungsi:** Backup otomatis saat aplikasi ditutup
- **Format:** Timestamp dalam nama file
- **Contoh:** `backup_2026-04-06T17-38-35.312780036.txt`

---

## 🔧 Kode File Management

### **Class: `cFileManager.java`**

#### **📍 Lokasi File:**
```
WarungMakan/src/warungmakan/cFileManager.java
```

#### **🔑 Method Utama:**

##### **1. Save Transaction**
```java
public static void saveTransaction(cPesanan pesanan) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTION_FILE, true))) {
        // Save header transaksi
        writer.write(pesanan.getNomorTransaksi() + "|" + 
                    pesanan.getNamaPembeli() + "|" + 
                    pesanan.getTotalBiaya() + "|" + 
                    pesanan.isSelesai());
        writer.newLine();
        
        // Save setiap item dalam transaksi
        for (ItemPesanan item : pesanan.getDaftarItem()) {
            writer.write("ITEM|" + 
                       item.getBarang().getKode() + "|" + 
                       item.getBarang().getNama() + "|" + 
                       item.getJumlah() + "|" + 
                       item.getSubtotal());
            writer.newLine();
        }
        writer.write("END_TRANSACTION");
        writer.newLine();
    } catch (IOException e) {
        System.out.println("Error saving transaction: " + e.getMessage());
    }
}
```

##### **2. Save Menu**
```java
public static void saveMenu(ArrayList<cBarang> daftarBarang) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(MENU_FILE))) {
        for (cBarang barang : daftarBarang) {
            String line = barang.getKode() + "|" + 
                        barang.getNama() + "|" + 
                        barang.getHarga() + "|" + 
                        barang.getKategori();
            
            // Tambahkan atribut spesifik berdasarkan jenis barang
            if (barang instanceof cMakanan) {
                line += "|" + ((cMakanan) barang).getJenis();
            } else if (barang instanceof cMinuman) {
                line += "|" + ((cMinuman) barang).getUkuran();
            }
            
            writer.write(line);
            writer.newLine();
        }
    } catch (IOException e) {
        System.out.println("Error saving menu: " + e.getMessage());
    }
}
```

##### **3. Save Pelanggan**
```java
public static void savePelanggan(ArrayList<cPelanggan> daftarPelanggan) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(PELANGGAN_FILE))) {
        for (cPelanggan pelanggan : daftarPelanggan) {
            String line = pelanggan.getKodePelanggan() + "|" + 
                        pelanggan.getNama() + "|" + 
                        pelanggan.getAlamat() + "|" + 
                        pelanggan.getNomorMeja() + "|" + 
                        pelanggan.isPelangganTetap() + "|" + 
                        pelanggan.getTotalBelanja();
            writer.write(line);
            writer.newLine();
        }
    } catch (IOException e) {
        System.out.println("Error saving pelanggan: " + e.getMessage());
    }
}
```

##### **4. Backup Data**
```java
public static void backupData() {
    String timestamp = java.time.LocalDateTime.now().toString().replace(":", "-");
    String backupFile = "backup_" + timestamp + ".txt";
    
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(backupFile))) {
        writer.write("=== BACKUP WARUNG MAKAN WONG SOLO ===");
        writer.newLine();
        writer.write("Timestamp: " + java.time.LocalDateTime.now());
        writer.newLine();
        writer.newLine();
        
        writer.write("=== TRANSAKSI ===");
        writer.newLine();
        ArrayList<String> transactions = loadTransactions();
        for (String transaction : transactions) {
            writer.write(transaction);
            writer.newLine();
        }
    } catch (IOException e) {
        System.out.println("Error creating backup: " + e.getMessage());
    }
}
```

---

## 🚀 Pemanggilan File Manager

### **Di `cAppWarungMakan.java`:**

#### **1. Save Menu Setiap Perubahan**
```java
case 1: // Tambah Barang
    menuManager.tambahBarang();
    cFileManager.saveMenu(menuManager.getDaftarBarang());
    break;
case 2: // Ubah Barang
    menuManager.ubahBarang();
    cFileManager.saveMenu(menuManager.getDaftarBarang());
    break;
case 3: // Hapus Barang
    menuManager.hapusBarang();
    cFileManager.saveMenu(menuManager.getDaftarBarang());
    break;
```

#### **2. Save Transaksi Setiap Pembayaran**
```java
// Setelah transaksi selesai
laporanManager.tambahTransaksi(pesananSelesai);

// Save transaction ke file
cFileManager.saveTransaction(pesananSelesai);

// Update total belanja pelanggan
cPelanggan pelanggan = pelangganManager.cariPelanggan(pesananSelesai.getNamaPembeli());
if (pelanggan != null) {
    pelanggan.tambahBelanja(pesananSelesai.getTotalBiaya());
    cFileManager.savePelanggan(pelangganManager.getDaftarPelanggan());
}
```

#### **3. Save Pelanggan Setiap Perubahan**
```java
case 1: // Tambah Pelanggan
    pelangganManager.tambahPelanggan();
    cFileManager.savePelanggan(pelangganManager.getDaftarPelanggan());
    break;
case 2: // Ubah Pelanggan
    pelangganManager.ubahPelanggan();
    cFileManager.savePelanggan(pelangganManager.getDaftarPelanggan());
    break;
case 3: // Hapus Pelanggan
    pelangganManager.hapusPelanggan();
    cFileManager.savePelanggan(pelangganManager.getDaftarPelanggan());
    break;
```

#### **4. Backup Otomatis Saat Exit**
```java
case 5: // Selesai
    System.out.println("\nMenampilkan laporan penutupan...");
    laporanManager.tampilkanLaporanPenutupan();
    
    // Create backup before closing
    System.out.println("\nMembuat backup data...");
    cFileManager.backupData();
    
    System.out.println("\nTerima kasih telah menggunakan Aplikasi Warung Makan Wong Solo!");
    break;
```

---

## 📋 Format File Lengkap

### **Struktur `transactions.txt`:**
```
[HEADER_TRANSAKSI]
[ITEM_1]
[ITEM_2]
...
[ITEM_N]
END_TRANSACTION
[HEADER_TRANSAKSI_2]
[ITEM_1]
...
END_TRANSACTION
```

### **Struktur `menu.txt`:**
```
[KODE]|[NAMA]|[HARGA]|[KATEGORI]|[JENIS/UKURAN]
[M001]|[Nasi Goreng]|[15000.0]|[Makanan]|[Paket]
[M002]|[Mie Ayam]|[35000.0]|[Makanan]|[Ala Carte]
[D001]|[Es Teh]|[5000.0]|[Minuman]|[Small]
```

### **Struktur `pelanggan.txt`:**
```
[KODE]|[NAMA]|[ALAMAT]|[NOMOR_MEJA]|[PELANGGAN_TETAP]|[TOTAL_BELANJA]
[P001]|[Budi]|[Surabaya]|[A1]|[true]|[125000.0]
```

---

## 🔒 Keamanan Data

1. **Auto Backup:** Setiap aplikasi ditutup, backup otomatis dibuat
2. **Error Handling:** Try-catch untuk setiap operasi file
3. **Append Mode:** Transaksi menggunakan append mode (true) untuk menambah data
4. **Overwrite Mode:** Menu dan pelanggan menggunakan overwrite mode untuk update data

---

## 🛠️ Teknologi yang Digunakan

- **Java I/O:** `BufferedWriter`, `BufferedReader`
- **File Handling:** `FileWriter`, `FileReader`
- **Exception Handling:** `IOException`
- **Date/Time:** `java.time.LocalDateTime`
- **Data Structure:** `ArrayList<String>` untuk temporary storage

---

## 📊 Contoh Data Real

### **File `transactions.txt` (setelah beberapa transaksi):**
```
TRX001|Budi Santoso|45000.0|true
ITEM|M001|Nasi Goreng|2|30000.0
ITEM|M003|Es Teh|3|15000.0
END_TRANSACTION
TRX002|Siti Nurhaliza|75000.0|true
ITEM|M002|Mie Ayam|1|35000.0
ITEM|M004|Sayur Lodeh|1|20000.0
ITEM|M005|Es Jeruk|4|20000.0
END_TRANSACTION
```

### **File `menu.txt` (complete data):**
```
M001|Nasi Goreng|15000.0|Makanan|Paket
M002|Mie Ayam|35000.0|Makanan|Ala Carte
M003|Sayur Lodeh|20000.0|Makanan|Sayur
M004|Ayam Bakar|25000.0|Makanan|Ala Carte
D001|Es Teh|5000.0|Minuman|Small
D002|Es Jeruk|5000.0|Minuman|Medium
D003|Jus Alpukat|15000.0|Minuman|Large
```

### **File `pelanggan.txt` (dengan total belanja):**
```
P001|Budi Santoso|Surabaya|A1|true|125000.0
P002|Siti Nurhaliza|Malang|B2|true|85000.0
P003|Ahmad Fauzi|Sidoarjo|C3|false|45000.0
```

---

## ✅ Summary

**Sistem file management ini sudah lengkap dan robust:**
- ✅ **3 file utama** untuk data persisten
- ✅ **Auto backup** untuk keamanan data
- ✅ **Format terstruktur** dengan delimiter |
- ✅ **Error handling** yang baik
- ✅ **Real-time save** setiap perubahan data
- ✅ **Timestamp** untuk backup dan tracking

**Total file yang dikelola:** 4 file (.txt) + backup files
