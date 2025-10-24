import java.util.Scanner;

public class BrojRijeci {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Unesi recenicu: ");
        String s = sc.nextLine();
        sc.close();

        String[] rijeci = s.trim().split("\\s+");
        int brojRijeci = rijeci.length;
        System.out.println(brojRijeci);
    }
}
