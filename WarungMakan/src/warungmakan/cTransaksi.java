package warungmakan;

public class cTransaksi {
    private cMakanan menu;
    private cBuyer pembeli;
    private int qty;
    private double total;

    public void tambahPesanan(cMakanan m, cBuyer b, int q) {
        this.menu = m;
        this.pembeli = b;
        this.qty = q;
        this.total = m.getHarga() * q; // Perhitungan total harga
    }

    public void cetakStruk() {
        System.out.println("\n================================");
        System.out.println("   STRUK WARUNG MAKAN SOLO");
        System.out.println("================================");
        System.out.println("Pembeli  : " + pembeli.getName());
        System.out.println("Pesanan  : " + menu.getNama());
        System.out.println("Harga    : Rp" + menu.getHarga());
        System.out.println("Jumlah   : " + qty);
        System.out.println("--------------------------------");
        System.out.println("TOTAL    : Rp" + total);
        System.out.println("================================");
    }
}