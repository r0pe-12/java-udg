import java.util.Scanner;

public class TreciZadatak {
    static void main() {
        Scanner sc = new Scanner(System.in);
        int p = sc.nextInt();
        double r = Math.sqrt(p) / Math.PI;
        double o = 2 * r * Math.PI;
        System.out.printf("Obim je %.2f", o);
    }
}
