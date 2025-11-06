import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

void main() {
    ArrayList<Zaposleni> zaposleni = new ArrayList<>();
    Restoran restoran = null;
    EvidencijaSmjena evidencija = new EvidencijaSmjena();
    ObracunManager obracunManager = new ObracunManager();
    Scanner scanner = new Scanner(System.in);
    boolean radi = true;

    System.out.println("SISTEM ZA UPRAVLJANJE ZAPOSLENIMA U RESTORANU");

    while (radi) {
        System.out.println("\nGLAVNI MENI");
        System.out.println("1. Upravljanje Zaposlenim");
        System.out.println("2. Upravljanje Restoranom");
        System.out.println("3. Evidencija Smjena");
        System.out.println("4. Obračun Plata");
        System.out.println("5. Izlaz");
        System.out.print("\nUnesite opciju: ");

        String izbor = scanner.nextLine().trim();

        switch (izbor) {
            case "1":
                meniZaposleni(zaposleni, scanner);
                break;
            case "2":
                restoran = meniRestoran(restoran, zaposleni, scanner);
                break;
            case "3":
                meniSmjene(evidencija, zaposleni, scanner);
                break;
            case "4":
                meniObracun(restoran, obracunManager, scanner);
                break;
            case "5":
                radi = false;
                System.out.println("\nIzlaz iz programa...");
                break;
            default:
                System.out.println("\nNevalidna opcija. Pokušajte ponovo.");
        }
    }
    scanner.close();
}

static void meniZaposleni(ArrayList<Zaposleni> zaposleni, Scanner scanner) {
    System.out.println("\nUPRAVLJANJE ZAPOSLENIM");
    System.out.println("1. Dodaj zaposlenog");
    System.out.println("2. Prikaži sve zaposlene");
    System.out.println("3. Prikaži po tipu");
    System.out.print("Izbor: ");

    String izbor = scanner.nextLine().trim();

    switch (izbor) {
        case "1":
            dodajZaposlenog(zaposleni, scanner);
            break;
        case "2":
            if (zaposleni.isEmpty()) {
                System.out.println("\nNema zaposlenih u sistemu.");
            } else {
                System.out.println("\nSVI ZAPOSLENI");
                ispisiTabelu(zaposleni.toArray(new Zaposleni[0]));
            }
            break;
        case "3":
            prikaziPoTipu(zaposleni, scanner);
            break;
    }
}

static Restoran meniRestoran(Restoran restoran, ArrayList<Zaposleni> zaposleni, Scanner scanner) {
    System.out.println("\nUPRAVLJANJE RESTORANOM");

    if (restoran == null) {
        System.out.println("Restoran nije kreiran. Kreirajte restoran:");
        System.out.print("Naziv: ");
        String naziv = scanner.nextLine().trim();
        System.out.print("Adresa: ");
        String adresa = scanner.nextLine().trim();
        System.out.print("PIB: ");
        String pib = scanner.nextLine().trim();

        restoran = new Restoran(naziv, adresa, pib);
        System.out.println("\nRestoran kreiran!");
        System.out.println(restoran);
        return restoran;
    }

    System.out.println(restoran);
    System.out.println("\n1. Dodaj zaposlenog u restoran");
    System.out.println("2. Ukloni zaposlenog");
    System.out.println("3. Pretraži zaposlenog");
    System.out.println("4. Prikaz ukupnog troška plata");
    System.out.print("Izbor: ");

    String izbor = scanner.nextLine().trim();

    switch (izbor) {
        case "1":
            if (zaposleni.isEmpty()) {
                System.out.println("\nNema zaposlenih. Prvo kreirajte zaposlene u meniju 1.");
            } else {
                System.out.println("\nDostupni zaposleni:");
                ispisiTabelu(zaposleni.toArray(new Zaposleni[0]));
                System.out.print("\nUnesite ID zaposlenog za dodavanje: ");
                try {
                    int id = Integer.parseInt(scanner.nextLine().trim());
                    Zaposleni z = null;
                    for (Zaposleni zap : zaposleni) {
                        if (zap.getId() == id) {
                            z = zap;
                            break;
                        }
                    }
                    if (z != null) {
                        if (restoran.dodajZaposlenog(z)) {
                            System.out.println("Zaposleni dodat u restoran!");
                        } else {
                            System.out.println("Zaposleni već postoji u restoranu.");
                        }
                    } else {
                        System.out.println("Zaposleni sa tim ID-jem ne postoji.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Neispravan ID.");
                }
            }
            break;
        case "2":
            System.out.print("Unesite ID zaposlenog za uklanjanje: ");
            try {
                int id = Integer.parseInt(scanner.nextLine().trim());
                if (restoran.ukloniZaposlenog(id)) {
                    System.out.println("Zaposleni uklonjen!");
                } else {
                    System.out.println(" Zaposleni nije pronađen.");
                }
            } catch (NumberFormatException e) {
                System.out.println(" Neispravan ID.");
            }
            break;
        case "3":
            System.out.print("Unesite ID: ");
            try {
                int id = Integer.parseInt(scanner.nextLine().trim());
                Zaposleni z = restoran.pronadjiZaposlenog(id);
                if (z != null) {
                    System.out.println("\nPronađen: " + z.getIme() + " " + z.getPrezime());
                    System.out.printf("Mjesečna plata: %.2f EUR\n", z.izracunajMjesecnuPlatu());
                } else {
                    System.out.println("Zaposleni nije pronađen.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Neispravan ID.");
            }
            break;
        case "4":
            System.out.printf("\nUkupan trošak plata: %.2f EUR\n", restoran.ukupanTrosakPlata());
            break;
    }

    return restoran;
}

static void meniSmjene(EvidencijaSmjena evidencija, ArrayList<Zaposleni> zaposleni, Scanner scanner) {
    System.out.println("\nEVIDENCIJA SMJENA");
    System.out.println("1. Dodaj smjenu");
    System.out.println("2. Prikaži sve smjene");
    System.out.println("3. Ažuriraj sate zaposlenih iz smjena");
    System.out.print("Izbor: ");

    String izbor = scanner.nextLine().trim();

    switch (izbor) {
        case "1":
            System.out.print("Datum (YYYY-MM-DD): ");
            String datumStr = scanner.nextLine().trim();
            System.out.print("Početak (HH:MM): ");
            String pocetakStr = scanner.nextLine().trim();
            System.out.print("Kraj (HH:MM): ");
            String krajStr = scanner.nextLine().trim();
            System.out.println("Tip smjene: 1-PRVA, 2-DRUGA, 3-TRECA");
            System.out.print("Izbor: ");
            String tipIzbor = scanner.nextLine().trim();

            try {
                LocalDate datum = LocalDate.parse(datumStr);
                LocalTime pocetak = LocalTime.parse(pocetakStr);
                LocalTime kraj = LocalTime.parse(krajStr);
                TipSmjene tip = switch (tipIzbor) {
                    case "1" -> TipSmjene.PRVA;
                    case "2" -> TipSmjene.DRUGA;
                    case "3" -> TipSmjene.TRECA;
                    default -> TipSmjene.PRVA;
                };

                Smjena smjena = new Smjena(datum, pocetak, kraj, tip);
                evidencija.dodajSmjenu(smjena);
                System.out.println(" Smjena kreirana!");

            } catch (Exception e) {
                System.out.println(" Greška pri kreiranju smjene: " + e.getMessage());
            }
            break;
        case "2":
            if (evidencija.getSvesmjene().isEmpty()) {
                System.out.println("\nNema evidencije smjena.");
            } else {
                System.out.println("\nSVE SMJENE");
                for (Smjena s : evidencija.getSvesmjene()) {
                    System.out.println(s);
                }
            }
            break;
        case "3":
            evidencija.azurirajSateZaposlenih(zaposleni);
            System.out.println(" Sati ažurirani iz smjena!");
            for (Zaposleni z : zaposleni) {
                System.out.printf("%s %s: %d sati\n", z.getIme(), z.getPrezime(), z.getUkupanBrojSati());
            }
            break;
    }
}

static void meniObracun(Restoran restoran, ObracunManager manager, Scanner scanner) {
    if (restoran == null) {
        System.out.println("\n Prvo kreirajte restoran (Meni 2).");
        return;
    }

    System.out.println("\nOBRAČUN PLATA");
    System.out.println("1. Generiši mjesečni obračun");
    System.out.println("2. Prikaži istoriju obračuna");
    System.out.println("3. Sačuvaj obračune u fajl");
    System.out.print("Izbor: ");

    String izbor = scanner.nextLine().trim();

    switch (izbor) {
        case "1":
            System.out.print("Mjesec (1-12): ");
            try {
                int mjesec = Integer.parseInt(scanner.nextLine().trim());
                System.out.print("Godina: ");
                int godina = Integer.parseInt(scanner.nextLine().trim());

                manager.kreirajObracun(restoran, mjesec, godina);
                ArrayList<ObracunPlate> obracuni = restoran.generisiMjesecniObracun(mjesec, godina);

                System.out.printf("\nOBRAČUN ZA %d/%d \n", mjesec, godina);
                restoran.prikaziTabeluObracuna(obracuni);

            } catch (NumberFormatException e) {
                System.out.println(" Neispravan unos.");
            }
            break;
        case "2":
            System.out.print("ID zaposlenog: ");
            try {
                int id = Integer.parseInt(scanner.nextLine().trim());
                manager.prikaziIstoriju(id);
            } catch (NumberFormatException e) {
                System.out.println(" Neispravan ID.");
            }
            break;
        case "3":
            try {
                manager.sacuvajSveObracune("obracuni.json");
                System.out.println(" Obračuni sačuvani u: obracuni.json");
            } catch (Exception e) {
                System.out.println(" Greška: " + e.getMessage());
            }
            break;
    }
}

static void dodajZaposlenog(ArrayList<Zaposleni> zaposleni, Scanner scanner) {
    System.out.println("\nDODAVANJE NOVOG ZAPOSLENOG");
    System.out.println("1. Konobar");
    System.out.println("2. Kuvar");
    System.out.println("3. Menadžer");
    System.out.print("Izaberite tip zaposlenog: ");

    String tipIzbor = scanner.nextLine().trim();

    try {
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        for (Zaposleni z : zaposleni) {
            if (z.getId() == id) {
                System.out.println("\nGreška: ID već postoji! Koristite drugi ID.");
                return;
            }
        }

        System.out.print("Ime: ");
        String ime = scanner.nextLine().trim();

        System.out.print("Prezime: ");
        String prezime = scanner.nextLine().trim();

        System.out.print("Plata po satu (EUR): ");
        double plataPoSatu = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Ukupan broj sati sedmično: ");
        int ukupanBrojSati = Integer.parseInt(scanner.nextLine().trim());

        Zaposleni noviZaposleni = null;

        switch (tipIzbor) {
            case "1":
                System.out.print("Prekovremeni sati: ");
                int prekovremenihSati = Integer.parseInt(scanner.nextLine().trim());
                noviZaposleni = new Zaposleni(id, ime, prezime, plataPoSatu, ukupanBrojSati, prekovremenihSati);
                break;
            case "2":
                noviZaposleni = new Zaposleni(id, ime, prezime, plataPoSatu, ukupanBrojSati, TipZaposlenog.KUVAR);
                break;
            case "3":
                System.out.print("Bonus (EUR): ");
                double bonus = Double.parseDouble(scanner.nextLine().trim());
                noviZaposleni = new Zaposleni(id, ime, prezime, plataPoSatu, ukupanBrojSati, bonus);
                break;
            default:
                System.out.println("Nevalidna opcija.");
                return;
        }

        zaposleni.add(noviZaposleni);
        System.out.println("\nZaposleni uspešno dodat!");
        System.out.printf("Mjesečna plata: %.2f EUR\n", noviZaposleni.izracunajMjesecnuPlatu());

    } catch (NumberFormatException e) {
        System.out.println("\nGreška: Neispravan unos podataka.");
    }
}

static void prikaziPoTipu(ArrayList<Zaposleni> zaposleni, Scanner scanner) {
    if (zaposleni.isEmpty()) {
        System.out.println("\nNema zaposlenih u sistemu.");
        return;
    }

    System.out.println("\nPRIKAZ PO TIPU");
    System.out.println("1. Konobari");
    System.out.println("2. Kuvari");
    System.out.println("3. Menadžeri");
    System.out.print("Izaberite tip: ");

    String izbor = scanner.nextLine().trim();
    TipZaposlenog tip = null;
    String naziv = "";

    switch (izbor) {
        case "1":
            tip = TipZaposlenog.KONOBAR;
            naziv = "KONOBARI";
            break;
        case "2":
            tip = TipZaposlenog.KUVAR;
            naziv = "KUVARI";
            break;
        case "3":
            tip = TipZaposlenog.MENADZER;
            naziv = "MENADŽERI";
            break;
        default:
            System.out.println("Nevalidna opcija.");
            return;
    }

    ArrayList<Zaposleni> filtriraniZaposleni = new ArrayList<>();
    for (Zaposleni z : zaposleni) {
        if (z.getTipZaposlenog() == tip) {
            filtriraniZaposleni.add(z);
        }
    }

    if (filtriraniZaposleni.isEmpty()) {
        System.out.println("\nNema zaposlenih tipa: " + naziv);
    } else {
        System.out.println("\n" + naziv + ":");
        ispisiTabelu(filtriraniZaposleni.toArray(new Zaposleni[0]));
    }
}

static void ispisiTabelu(Zaposleni[] zaposleni) {
    TabelaPrinter.stampajZaposlene(zaposleni);
}
