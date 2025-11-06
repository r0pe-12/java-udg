import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TestProgram {
    public static void main(String[] args) {
        System.out.println("=== TEST PROGRAM - SISTEM ZA UPRAVLJANJE RESTORANOM ===\n");

        Restoran restoran = new Restoran("moj restoran", "neka adresa, Podgorica", "12345678");
        System.out.println(restoran);
        System.out.println("\n--- KREIRANJE ZAPOSLENIH ---");

        Zaposleni konobar1 = new Zaposleni(1, "Marko", "Marković", 8.50, 0, 5);
        Zaposleni konobar2 = new Zaposleni(2, "Ana", "1", 9.00, 0, 8);
        Zaposleni kuvar1 = new Zaposleni(3, "Petar", "2", 12.00, 0, TipZaposlenog.KUVAR);
        Zaposleni kuvar2 = new Zaposleni(4, "Jelena", "3", 11.50, 0, TipZaposlenog.KUVAR);
        Zaposleni menadzer1 = new Zaposleni(5, "Stefan", "4", 15.00, 0, 500.00);
        Zaposleni pomocniRadnik = new Zaposleni(6, "Milan", "5", 7.00, 0, 0);

        restoran.dodajZaposlenog(konobar1);
        restoran.dodajZaposlenog(konobar2);
        restoran.dodajZaposlenog(kuvar1);
        restoran.dodajZaposlenog(kuvar2);
        restoran.dodajZaposlenog(menadzer1);
        restoran.dodajZaposlenog(pomocniRadnik);

        System.out.println("Dodato " + restoran.getZaposleni().size() + " zaposlenih");

        System.out.println("\n--- KREIRANJE SMJENA ---");
        EvidencijaSmjena evidencija = new EvidencijaSmjena();

        Smjena smjena1 = new Smjena(LocalDate.of(2025, 11, 1), LocalTime.of(8, 0), LocalTime.of(16, 0), TipSmjene.PRVA);
        smjena1.dodajZaposlenog(konobar1);
        smjena1.dodajZaposlenog(kuvar1);
        smjena1.dodajZaposlenog(pomocniRadnik);

        Smjena smjena2 = new Smjena(LocalDate.of(2025, 11, 1), LocalTime.of(16, 0), LocalTime.of(23, 0), TipSmjene.DRUGA);
        smjena2.dodajZaposlenog(konobar2);
        smjena2.dodajZaposlenog(kuvar2);
        smjena2.dodajZaposlenog(menadzer1);

        Smjena smjena3 = new Smjena(LocalDate.of(2025, 11, 2), LocalTime.of(8, 0), LocalTime.of(16, 0), TipSmjene.PRVA);
        smjena3.dodajZaposlenog(konobar1);
        smjena3.dodajZaposlenog(kuvar1);
        smjena3.dodajZaposlenog(pomocniRadnik);

        Smjena smjena4 = new Smjena(LocalDate.of(2025, 11, 2), LocalTime.of(16, 0), LocalTime.of(23, 0), TipSmjene.DRUGA);
        smjena4.dodajZaposlenog(konobar2);
        smjena4.dodajZaposlenog(kuvar2);

        Smjena smjena5 = new Smjena(LocalDate.of(2025, 11, 3), LocalTime.of(8, 0), LocalTime.of(20, 0), TipSmjene.PRVA);
        smjena5.dodajZaposlenog(konobar1);
        smjena5.dodajZaposlenog(konobar2);
        smjena5.dodajZaposlenog(kuvar1);
        smjena5.dodajZaposlenog(kuvar2);
        smjena5.dodajZaposlenog(menadzer1);

        evidencija.dodajSmjenu(smjena1);
        evidencija.dodajSmjenu(smjena2);
        evidencija.dodajSmjenu(smjena3);
        evidencija.dodajSmjenu(smjena4);
        evidencija.dodajSmjenu(smjena5);

        System.out.println("Kreirano 5 smjena:");
        for (Smjena s : evidencija.getSvesmjene()) {
            System.out.println("  " + s);
        }

        System.out.println("\n--- AUTOMATSKI OBRAČUN SATI IZ SMJENA ---");
        evidencija.azurirajSateZaposlenih(restoran.getZaposleni());

        for (Zaposleni z : restoran.getZaposleni()) {
            int sati = evidencija.izracunajSateZaZaposlenog(z.getId());
            System.out.printf("%s %s (ID: %d): %d sati sedmično\n",
                    z.getIme(), z.getPrezime(), z.getId(), sati);
        }

        System.out.println("\n--- GENERISANJE MJESEČNOG OBRAČUNA (Novembar 2025) ---");
        ArrayList<ObracunPlate> obracuni = restoran.generisiMjesecniObracun(11, 2025);
        restoran.prikaziTabeluObracuna(obracuni);

        System.out.println("\n--- ČUVANJE PODATAKA U FAJLOVE ---");
        try {
            ObracunPlate.sacuvajUFajl("obracuni.csv", obracuni);
            System.out.println("✓ Obračuni sačuvani u: obracuni.csv");

            ObracunManager manager = new ObracunManager();
            manager.kreirajObracun(restoran, 11, 2025);
            manager.sacuvajSveObracune("obracuni.json");
            System.out.println("✓ Obračuni sačuvani u: obracuni.json");

        } catch (Exception e) {
            System.out.println("✗ Greška pri čuvanju: " + e.getMessage());
        }

        System.out.println("\n--- PRETRAGA ZAPOSLENOG ---");
        Zaposleni pronadjen = restoran.pronadjiZaposlenog(201);
        if (pronadjen != null) {
            System.out.println("Pronađen: " + pronadjen.getIme() + " " + pronadjen.getPrezime());
            System.out.printf("Mjesečna plata: %.2f EUR\n", pronadjen.izracunajMjesecnuPlatu());
        }

        System.out.println("\n--- UKUPAN TROŠAK PLATA ZA RESTORAN ---");
        System.out.printf("Ukupno: %.2f EUR\n", restoran.ukupanTrosakPlata());

        System.out.println("\n=== TEST PROGRAM ZAVRŠEN ===");
    }
}
