import java.util.Scanner;

public class PetiZadatak {
    public static double calculateDistance(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }

    static void main() {
        Scanner sc = new Scanner(System.in);
        double x1 = sc.nextDouble(), y1 = sc.nextDouble(), x2 = sc.nextDouble(), y2 = sc.nextDouble();
        double x3 = x2 + 2, y3 = y2 - 3;

        double hrast_blago = calculateDistance(x1, y1, x3, y3);
        double full_distance = calculateDistance(x1, y1, x2, y2) + calculateDistance(x2, y2, x3, y3);

        System.out.printf("Vazdusno rastojanje izmedju hrasta i blaga je: %.2f%nRastojanje hrast-kuca-blago je: %.2f", hrast_blago, full_distance);
    }
}
