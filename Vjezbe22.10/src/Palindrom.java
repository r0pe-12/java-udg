import java.util.Scanner;

public class Palindrom {
    static void main() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Unesi string: ");
        String s = sc.nextLine().trim().toLowerCase();
        sc.close();
        String reversed = new StringBuilder(s).reverse().toString();

        if (s.equals(reversed)) {
            System.out.println("Rijec je palindrom.");
        } else {
            System.out.println("Rijec nije palindrom.");
        }
    }
}
