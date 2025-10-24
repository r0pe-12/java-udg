import java.util.Scanner;

public class CetvrtiZadatak {
    static void main() {
        Scanner sc = new Scanner(System.in);
        double x1 = sc.nextDouble(), x2 = sc.nextDouble(), y1 = sc.nextDouble(), y2 = sc.nextDouble();
        double a = Math.abs(x1 - x2);
        double b = Math.abs(y1 - y2);

        double p = a * b;
        double o = 2 * (a + b);
        System.out.printf("Povrsina je: %.2f %nObim je: %.2f", p, o);
    }
}
