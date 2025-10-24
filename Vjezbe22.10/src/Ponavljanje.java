import java.util.Scanner;

public class Ponavljanje {
    static void main() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();

        boolean nadjeno = false;
        for (int i = 0; i < input.length() - 1; i++) {
            if (input.charAt(i) == input.charAt(i + 1)) {
                nadjeno = true;
                System.out.println("ponovljeni znak: " + input.charAt(i));
            }
        }

    }
}
