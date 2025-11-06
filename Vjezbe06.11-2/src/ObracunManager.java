import java.io.*;
import java.util.ArrayList;

public class ObracunManager {
    private final ArrayList<ObracunPlate> obracuni;

    public ObracunManager() {
        this.obracuni = new ArrayList<>();
    }

    public void kreirajObracun(Restoran restoran, int mjesec, int godina) {
        obracuni.clear();
        for (Zaposleni z : restoran.getZaposleni()) {
            ObracunPlate obr = new ObracunPlate(mjesec, godina, z);
            obracuni.add(obr);
        }
    }

    public ObracunPlate getObracunZaZaposlenog(int id, int mjesec, int godina) {
        for (ObracunPlate obr : obracuni) {
            if (obr.getZaposleni().getId() == id &&
                obr.getMjesec() == mjesec &&
                obr.getGodina() == godina) {
                return obr;
            }
        }
        return null;
    }

    public void sacuvajSveObracune(String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("[");
            for (int i = 0; i < obracuni.size(); i++) {
                ObracunPlate o = obracuni.get(i);
                writer.println("  {");
                writer.printf("    \"mjesec\": %d,%n", o.getMjesec());
                writer.printf("    \"godina\": %d,%n", o.getGodina());
                writer.printf("    \"zaposlenId\": %d,%n", o.getZaposleni().getId());
                writer.printf("    \"ime\": \"%s\",%n", o.getZaposleni().getIme());
                writer.printf("    \"prezime\": \"%s\",%n", o.getZaposleni().getPrezime());
                writer.printf("    \"iznos\": %.2f,%n", o.getObracunatIznos());
                writer.printf("    \"napomena\": \"%s\"%n", o.getNapomena());
                writer.print("  }");
                if (i < obracuni.size() - 1) {
                    writer.println(",");
                } else {
                    writer.println();
                }
            }
            writer.println("]");
        }
    }

    public void ucitajObracune(String filename, ArrayList<Zaposleni> zaposleni) throws IOException {
        obracuni.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            Integer mjesec = null, godina = null, zaposlenId = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.contains("\"mjesec\":")) {
                    mjesec = Integer.parseInt(line.split(":")[1].replace(",", "").trim());
                } else if (line.contains("\"godina\":")) {
                    godina = Integer.parseInt(line.split(":")[1].replace(",", "").trim());
                } else if (line.contains("\"zaposlenId\":")) {
                    zaposlenId = Integer.parseInt(line.split(":")[1].replace(",", "").trim());
                } else if (line.equals("}") || line.equals("},")) {
                    if (mjesec != null && godina != null && zaposlenId != null) {
                        Zaposleni z = null;
                        for (Zaposleni zap : zaposleni) {
                            if (zap.getId() == zaposlenId) {
                                z = zap;
                                break;
                            }
                        }
                        if (z != null) {
                            obracuni.add(new ObracunPlate(mjesec, godina, z));
                        }
                        mjesec = godina = zaposlenId = null;
                    }
                }
            }
        }
    }

    public void prikaziIstoriju(int id) {
        System.out.println("\n=== ISTORIJA OBRAČUNA ZA ZAPOSLENOG ID: " + id + " ===");
        boolean pronadjen = false;
        for (ObracunPlate obr : obracuni) {
            if (obr.getZaposleni().getId() == id) {
                System.out.println(obr);
                pronadjen = true;
            }
        }
        if (!pronadjen) {
            System.out.println("Nema obračuna za ovog zaposlenog.");
        }
    }

    public ArrayList<ObracunPlate> getObracuni() {
        return obracuni;
    }

    public double ukupanIznos() {
        double suma = 0;
        for (ObracunPlate obr : obracuni) {
            suma += obr.getObracunatIznos();
        }
        return suma;
    }
}
