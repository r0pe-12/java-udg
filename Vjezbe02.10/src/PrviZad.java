import java.util.Scanner;

public class PrviZad {
    static void main() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long d = sc.nextLong();
        long hd = sc.nextLong();
        long ka = sc.nextLong();

        long x, y, m;

        int num = 0;

        for (int i = 0; i < n; i++) {
            x = sc.nextLong();
            y = sc.nextLong();
            m = Math.abs(x) + Math.abs(y);

            if (m <= d) {
                num++;
            }
        }
        long dmg = num * ka;
        System.out.println(num);
        System.out.println(dmg);
    }
}
