import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Radnik> radnici = new ArrayList<>();
        ArrayList<Posao> poslovi = new ArrayList<>();
        poslovi.add(new Posao(0));
        radnici.add(new Radnik(1, "Marko", "Markovic"));
        radnici.add(new Radnik(2, "Jovan", "Jovanovic"));
        radnici.add(new Radnik(3, "Ana", "Anic"));
        GUI gui = new GUI(radnici, poslovi);
        gui.setVisible(true);

        if (false) {


            // Radnici
            Radnik r1 = new Radnik(1, "Marko", "Markovic");
            Radnik r2 = new Radnik(2, "Jovan", "Jovanovic");
            Radnik r3 = new Radnik(3, "Ana", "Anic");

            // Vrijeme
            LocalDateTime d1 = LocalDateTime.of(2025, 1, 10, 9, 0);
            LocalDateTime d2 = LocalDateTime.of(2025, 1, 12, 13, 30);
            LocalDateTime d3 = LocalDateTime.of(2025, 1, 20, 8, 15);
            LocalDateTime d4 = LocalDateTime.of(2025, 2, 2, 16, 0);

            // Usluge (klijent + lokacija)
            Usluga u1 = new VodoinstalaterskaUsluga(
                    r1, d1,
                    0,                  // m2 koristiš kao "količinu"; ovdje nije bitno jer je PO_SATU
                    120,                // trajanje u minutima
                    "Bulevar 1",        // lokacija
                    "Hotel Podgorica",  // klijent
                    VodoinstalaterskaUsluga.ObracunTip.PO_SATU
            );

            Usluga u2 = new VodoinstalaterskaUsluga(
                    r2, d2,
                    35,                 // 35m (kod tebe stoji u m2)
                    0,                  // trajanje nebitno za PO_M
                    "Bulevar 1",
                    "Hotel Podgorica",
                    VodoinstalaterskaUsluga.ObracunTip.PO_M
            );

            Usluga u3 = new KeramicarskaUsluga(
                    r3, d3,
                    18.5,               // m2
                    240,                // trajanje u minutima (ne utiče na cijenu kod tebe)
                    "Industrijska zona",
                    "Milic DOO",
                    KeramicarskaUsluga.TipUsluge.POSTAVLJANJE_PLOCICA
            );

            Usluga u4 = new KeramicarskaUsluga(
                    r1, d4,
                    12.0,
                    90,
                    "Industrijska zona",
                    "Milic DOO",
                    KeramicarskaUsluga.TipUsluge.NIVELACIJA_PODLOGE
            );

            // Poslovi
            ArrayList<Usluga> posao1Usluge = new ArrayList<>();
            posao1Usluge.add(u1);
            posao1Usluge.add(u2);

            ArrayList<Usluga> posao2Usluge = new ArrayList<>();
            posao2Usluge.add(u3);
            posao2Usluge.add(u4);

            Posao p1 = new Posao(posao1Usluge, 1);
            Posao p2 = new Posao(posao2Usluge, 1);

            ArrayList<Posao> sviPoslovi = new ArrayList<>();
            sviPoslovi.add(p1);
            sviPoslovi.add(p2);

            // ===== ISPISI: pojedinačne vrijednosti =====
            System.out.println("u1 (PO_SATU, 120min): " + u1.getVrijednost());
            System.out.println("u2 (PO_M, 35m):       " + u2.getVrijednost());
            System.out.println("u3 (PLOCICE, 18.5m2): " + u3.getVrijednost());
            System.out.println("u4 (NIVELACIJA, 12m2):" + u4.getVrijednost());

            // ===== obračun po poslu =====
            System.out.println("\nPosao 1 cijena: " + p1.getCijena());
            System.out.println("Posao 2 cijena: " + p2.getCijena());

            // ===== filtriranja =====
            ArrayList<Usluga> sveUsluge = new ArrayList<>();
            sveUsluge.add(u1);
            sveUsluge.add(u2);
            sveUsluge.add(u3);
            sveUsluge.add(u4);

            System.out.println("\nFilter po klijentu 'Hotel':");
            for (Usluga u : Usluga.filterPoKlijentu("Hotel", sveUsluge)) {
                System.out.println(" - " + u.getKlijent() + " | " + u.getRadnik().getImePrezime() + " | " + u.getVrijednost());
            }

            System.out.println("\nFilter po radniku 'Marko':");
            for (Usluga u : Usluga.filterPoRadniku("Marko", sveUsluge)) {
                System.out.println(" - " + u.getKlijent() + " | " + u.getRadnik().getImePrezime() + " | " + u.getVrijednost());
            }

            // ===== period =====
            LocalDateTime periodOd = LocalDateTime.of(2025, 1, 1, 0, 0);
            LocalDateTime periodDo = LocalDateTime.of(2025, 1, 31, 23, 59);

            double ukupnoJanuar = Usluga.ukupnaVrijednostUslugaPoDatumu(periodOd, periodDo, sveUsluge);
            System.out.println("\nUkupna vrijednost (januar): " + ukupnoJanuar);

            // ===== učinak radnika =====
            System.out.println("\nUčinak radnika Marko Markovic:");
            System.out.println(" - broj usluga: " + Usluga.brojUslugaPoRadniku(r1, sveUsluge));
            System.out.println(" - vrijeme (min): " + Usluga.vrijemeRadaRadnika(r1, sveUsluge));
            System.out.println(" - vrijednost: " + Usluga.vrijednostUslugaPoRadniku(r1, sveUsluge));

            // ===== filtriranja preko poslova =====
            System.out.println("\nPreko Posao.filterPoKlijentu (Milic): " + Posao.filterPoKlijentu(sviPoslovi, "Milic").size());
            System.out.println("Preko Posao.filterPoRadniku (Jovan): " + Posao.filterPoRadniku(sviPoslovi, "Jovan").size());
            System.out.println("Preko Posao.filterPoDatumu (januar): " + Posao.filterPoDatumu(sviPoslovi, periodOd, periodDo).size());
        }
    }
}
