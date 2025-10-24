public class Zadatak1 {
    static void main() {
        int[] niz = new int[10];
        for (int i = 0; i < niz.length; i++) {
            niz[i] = i + 1;
        }

        for (int i = 9; i >= 0; i--) {
            System.out.println(niz[i]);
        }
    }
}
