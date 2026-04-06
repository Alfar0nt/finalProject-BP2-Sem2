package warungmakan;

import java.io.*;
import java.util.ArrayList;

public class cFileManager {
    private static final String TRANSACTION_FILE = "transactions.txt";
    private static final String MENU_FILE = "menu.txt";
    private static final String PELANGGAN_FILE = "pelanggan.txt";
    
    public static void saveTransaction(cPesanan pesanan) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTION_FILE, true))) {
            writer.write(pesanan.getNomorTransaksi() + "|" + 
                        pesanan.getNamaPembeli() + "|" + 
                        pesanan.getTotalBiaya() + "|" + 
                        pesanan.isSelesai());
            writer.newLine();
            
            // Save items
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
    
    public static void saveMenu(ArrayList<cBarang> daftarBarang) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MENU_FILE))) {
            for (cBarang barang : daftarBarang) {
                String line = barang.getKode() + "|" + 
                            barang.getNama() + "|" + 
                            barang.getHarga() + "|" + 
                            barang.getKategori();
                
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
    
    public static ArrayList<String> loadTransactions() {
        ArrayList<String> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTION_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(line);
            }
        } catch (IOException e) {
            System.out.println("No existing transaction file found.");
        }
        return transactions;
    }
    
    public static void clearTransactionFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTION_FILE))) {
            writer.write("");
        } catch (IOException e) {
            System.out.println("Error clearing transaction file: " + e.getMessage());
        }
    }
    
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
}
