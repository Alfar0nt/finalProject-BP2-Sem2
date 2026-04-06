package warungmakan;

import java.util.Scanner;

public class cLogin {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "wongsolo123";
    
    public static boolean authenticate() {
        Scanner sc = new Scanner(System.in);
        int attempts = 0;
        final int maxAttempts = 3;
        
        while (attempts < maxAttempts) {
            System.out.println("=== LOGIN WARUNG MAKAN WONG SOLO ===");
            System.out.print("Username: ");
            String username = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();
            
            if (username.equals(USERNAME) && password.equals(PASSWORD)) {
                System.out.println("Login berhasil! Selamat datang di Warung Makan Wong Solo.");
                return true;
            } else {
                attempts++;
                System.out.println("Login gagal! Sisa percobaan: " + (maxAttempts - attempts));
                if (attempts < maxAttempts) {
                    System.out.println("Silakan coba lagi.");
                }
            }
        }
        
        System.out.println("Anda telah gagal login 3 kali. Program akan ditutup.");
        return false;
    }
}
