import java.util.Scanner;

public class DrugiZad {
    static void main() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Unesi koordinate centra");
        double cx = sc.nextDouble();
        double cy = sc.nextDouble();

        System.out.println("Unesi poluprecnik prvog kruga");
        double r1 = sc.nextDouble();

        System.out.println("Unesi poluprecnik drugog kruga");
        double r2 = sc.nextDouble();

        System.out.println("Unesi broj trkaca");
        int n = sc.nextInt();

        int naStazi = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Unesi x trkaca");
            double x = sc.nextDouble();
            System.out.println("Unesi y trkaca");
            double y = sc.nextDouble();

            double d = Math.sqrt(Math.pow(x - cx, 2) + Math.pow(y - cy, 2));

            if
        }
    }
}
