package warungmakan;

public class cMenu2 {
    public static cMakanan[] getDaftarMenu() {
        return new cMakanan[]{
            new cMakanan("Ayam Bakar Solo", 25000, "Paket"),
            new cMakanan("Ayam Penyet", 23000, "Paket"),
            new cMakanan("Gurami Bakar", 45000, "Ala Carte"),
            new cMakanan("Kangkung", 12000, "Sayur")
        };
    }
}