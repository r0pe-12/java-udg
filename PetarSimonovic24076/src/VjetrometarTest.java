import java.util.ArrayList;

public class VjetrometarTest {
    public static void main(String[] args) {
        ArrayList<Vjetrometar> vjetrometri = new ArrayList<>();
        vjetrometri.add(new Vjetrometar("test1", "2025-11-04 12:33:67", 56, 40, 130));
        vjetrometri.add(new Vjetrometar("test2", "2025-11-03 12:33:67", 70, 50, 130));
        vjetrometri.add(new Vjetrometar("test2", "2025-11-02 12:33:67", 100, 50, 130));
        vjetrometri.add(new Vjetrometar("test2", "2025-11-04 12:33:67", 120, 50, 130));
        vjetrometri.add(new Vjetrometar("test3", "2025-11-03 12:33:67", 30, 10, 130));
        vjetrometri.add(new Vjetrometar("test2", "2025-11-05 12:33:67", 20, 5, 130));
        vjetrometri.add(new Vjetrometar("test5", "2025-11-07 12:33:67", 100, 60, 130));

        ArrayList<Vjetrometar> vjetrometriPoDatumu = Vjetrometar.vjetrometriPoDatumu(vjetrometri, "2025-11-04");
        ArrayList<Vjetrometar> vjetrometriPoStanici = Vjetrometar.vjetrometriPoStanici(vjetrometri, "test2");
        Vjetrometar max_brzina = Vjetrometar.maksUdarVjetra(vjetrometri, "2025-11", "test2");

        System.out.println("max_brzina: " + max_brzina.toString());

        for (Vjetrometar x : vjetrometriPoStanici) {
            System.out.println(x.toString());
        }

    }

}
