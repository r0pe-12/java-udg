import java.util.Scanner;

public class Pravougaonik {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble();
        double b = sc.nextDouble();

        double p = a * b;
        double o = 2 * (a + b);
        System.out.printf("Povrsina je: %.2f %nObim je: %.2f", p, o);
    }
}
