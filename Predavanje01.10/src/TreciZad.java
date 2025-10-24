import java.util.Scanner;

public class TreciZad {
    private static int fact(int n) {
        int x = n;
        while (n > 1) {
            x *= --n;
        }
        return x;
    }

    static void main(String[] args) {
        System.out.print("Unesi broj: ");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        sc.close();

        System.out.printf("Faktorijal broja %d je %d", x, fact(x));
    }
}