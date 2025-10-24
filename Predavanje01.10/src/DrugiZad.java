import java.util.Scanner;

public class DrugiZad {
    private static float f(int x) {
        if (x < 1) {
            return (float) Math.pow(x, 2);
        }
        if (x < 5) {
            return 2 - x;
        }
        return ((float) (Math.pow(x, 3) - 1) / 5);
    }

    public static void main(String[] args) {
        float f;
        System.out.print("Unesi broj: ");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        sc.close();
        System.out.printf("f(%d)=%.2f\n ", x, f(x));
    }
}
