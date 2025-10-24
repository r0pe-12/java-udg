import java.util.Scanner;

public class DronIPaketi {
    public static double calculateDistance(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }

    static void main() {
        Scanner sc = new Scanner(System.in);
        int unos = sc.nextInt();
        double dronX = sc.nextDouble();
        double dronY = sc.nextDouble();
        int i = 0, x, y;
        double dist = 0;
        while (i < unos) {
            i++;
            System.out.print(i);
            x = (int) (Math.random() * 10);
            y = (int) (Math.random() * 10);
            if (x > 0 && y > 0) {
                dist += calculateDistance(dronX, dronY, x, y);
            }
        }
        System.out.println(dist);
    }
}
