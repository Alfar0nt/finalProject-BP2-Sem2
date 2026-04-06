# Project Structure Clean-up

## Current Status:
✅ **Folder `main` sudah dihapus** - tidak dibutuhkan lagi

## Files to keep in WarungMakan:
- ✅ `src/warungmakan/` - Semua source code Java
- ✅ `README.md` - Dokumentasi project
- ✅ `manifest.mf` - Manifest file (dibutuhkan untuk beberapa IDE)

## Files that can be removed:
- ❌ `build.xml` - Build file NetBeans (tidak perlu untuk Java biasa)
- ❌ `nbproject/` - NetBeans project folder (tidak perlu)
- ❌ `build/` - Build output folder (kosong)
- ❌ `test/` - Test folder (kosong)
- ❌ `backup_*.txt` - File backup sementara
- ❌ `WarungMakan.zip` - File zip di luar folder

## Recommended Final Structure:
```
finalProjectSem2/
└── WarungMakan/
    ├── README.md
    ├── src/
    │   └── warungmakan/
    │       ├── AppWarungMakan.java
    │       ├── Barang.java
    │       ├── Makanan.java
    │       ├── Minuman.java
    │       ├── MenuManager.java
    │       ├── Pelanggan.java
    │       ├── PelangganManager.java
    │       ├── Pesanan.java
    │       ├── ItemPesanan.java
    │       ├── TransaksiManager.java
    │       ├── LaporanManager.java
    │       ├── FileManager.java
    │       └── Login.java
    └── manifest.mf
```

## How to run:
```bash
cd WarungMakan
javac -d . src/warungmakan/*.java
java warungmakan.AppWarungMakan
```
