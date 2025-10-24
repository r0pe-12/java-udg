import java.util.Scanner;

public class DrugiZadatak {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double p = (a * b) / 100;
        System.out.printf("Povrsina je %.2f", p);
    }
}
