import java.util.Scanner;

public class PrebrojSamoglasnike {
    static void main() {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        input.close();
        int brojac = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = Character.toLowerCase(s.charAt(i));
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                brojac++;
            }
        }

        System.out.println("Broj samoglasnika: " + brojac);
    }
}
