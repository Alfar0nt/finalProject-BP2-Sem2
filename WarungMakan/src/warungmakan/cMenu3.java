package warungmakan;
import java.util.ArrayList;
import java.util.Arrays;

public class cMenu3 {
    private ArrayList<cMakanan> daftar;

    public cMenu3(cMakanan[] menuAwal) {
        this.daftar = new ArrayList<>(Arrays.asList(menuAwal));
    }
    public void tambahMenu(cMakanan m) {
        daftar.add(m);
        System.out.println("Menu '" + m.getNama() + "' berhasil ditambahkan!");
    }
    public void tampilkanMenu() {
        System.out.println("\n--- DAFTAR MENU WONG SOLO ---");
        for (int i = 0; i < daftar.size(); i++) {
            System.out.println((i + 1) + ". " + daftar.get(i).toString());
        }
    }
    public void updateHarga(String namaCari, double hargaBaru) {
        for (cMakanan m : daftar) {
            if (m.getNama().equalsIgnoreCase(namaCari)) {
                System.out.println("Harga '" + namaCari + "' diperbarui ke Rp" + hargaBaru);
                return;
            }
        }
        System.out.println("Menu tidak ditemukan!");
    }
    public void hapusMenu(String namaCari) {
        for (int i = 0; i < daftar.size(); i++) {
            if (daftar.get(i).getNama().equalsIgnoreCase(namaCari)) {
                System.out.println("Menu '" + daftar.get(i).getNama() + "' telah dihapus!");
                daftar.remove(i);
                return;
            }
        }
        System.out.println("Menu tidak ditemukan!");
    }

    public cMakanan cariMenu(String namaCari) {
        for (cMakanan m : daftar) {
            if (m.getNama().equalsIgnoreCase(namaCari)) return m;
        }
        return null;
    }
}