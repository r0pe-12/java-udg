import java.util.Scanner;

public class Zadatak2 {
    static void main() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Unesite duzinu niza: ");
        int duzinaNiza = sc.nextInt();
        int input;
//        int[] niz = new int[duzinaNiza];
        int zbir = 0;
        float prosjek = 0;
        int min = 100;
        int minp = 0;
        for (int i = 0; i < duzinaNiza - 1; i++) {
            System.out.println("Unesite broj bodova" + (i + 1) + ". studenta: ");
            input = sc.nextInt();
            if (input < min) {
                min = input;
                minp = i;
            }
            zbir += input;
//            niz[i] = input;
        }
        prosjek = (float) zbir / duzinaNiza;
        System.out.println("Prosjek je: " + prosjek);
        System.out.println("Najmanji broj bodova ima student " + (minp + 1) + " sa " + min + " bodova.");
        sc.close();
    }
}
