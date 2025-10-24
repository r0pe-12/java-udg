import java.util.Scanner;

public class PrviZad {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Unesi broj: ");
        int a = sc.nextInt();

//        int abs = a;
//        sc.close();
//        if (a < 0) {
//            abs = -a;
//        }

        System.out.printf("|%d| = %d", a, Math.abs(a));
    }
}
