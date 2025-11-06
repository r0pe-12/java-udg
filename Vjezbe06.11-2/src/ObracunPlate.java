import java.io.*;
import java.util.ArrayList;

public class ObracunPlate {
    private final int mjesec;
    private final int godina;
    private final Zaposleni zaposleni;
    private final double obracunatIznos;
    private final String napomena;

    public ObracunPlate(int mjesec, int godina, Zaposleni zaposleni) {
        this.mjesec = mjesec;
        this.godina = godina;
        this.zaposleni = zaposleni;
        this.obracunatIznos = zaposleni.izracunajMjesecnuPlatu();
        this.napomena = generisiNapomenu();
    }

    private String generisiNapomenu() {
        StringBuilder sb = new StringBuilder();
        switch (zaposleni.getTipZaposlenog()) {
            case KONOBAR:
                if (zaposleni.getPrekovremenihSati() > 0) {
                    sb.append("Uračunat prekovremeni rad: ").append(zaposleni.getPrekovremenihSati()).append("h");
                } else {
                    sb.append("Redovan rad");
                }
                break;
            case KUVAR:
                sb.append("Fiksni dodatak: 1500 EUR");
                break;
            case MENADZER:
                if (zaposleni.getBonus() > 0) {
                    sb.append("Bonus: ").append(String.format("%.0f EUR", zaposleni.getBonus()));
                } else {
                    sb.append("Osnovna plata");
                }
                break;
        }
        return sb.toString();
    }

    public static void sacuvajUFajl(String filename, ArrayList<ObracunPlate> obracuni) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Mjesec,Godina,ID,Ime,Prezime,Tip,Iznos,Napomena");
            for (ObracunPlate o : obracuni) {
                writer.printf("%d,%d,%d,%s,%s,%s,%.2f,%s%n",
                        o.mjesec, o.godina, o.zaposleni.getId(),
                        o.zaposleni.getIme(), o.zaposleni.getPrezime(),
                        o.zaposleni.getTipZaposlenog(), o.obracunatIznos,
                        o.napomena);
            }
        }
    }

    public static ArrayList<ObracunPlate> ucitajIzFajla(String filename, ArrayList<Zaposleni> zaposleni) throws IOException {
        ArrayList<ObracunPlate> obracuni = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 8);
                if (parts.length >= 7) {
                    int id = Integer.parseInt(parts[2]);
                    Zaposleni z = null;
                    for (Zaposleni zap : zaposleni) {
                        if (zap.getId() == id) {
                            z = zap;
                            break;
                        }
                    }
                    if (z != null) {
                        ObracunPlate obr = new ObracunPlate(
                                Integer.parseInt(parts[0]),
                                Integer.parseInt(parts[1]),
                                z
                        );
                        obracuni.add(obr);
                    }
                }
            }
        }
        return obracuni;
    }

    public int getMjesec() {
        return mjesec;
    }

    public int getGodina() {
        return godina;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public double getObracunatIznos() {
        return obracunatIznos;
    }

    public String getNapomena() {
        return napomena;
    }

    @Override
    public String toString() {
        return String.format("%d/%d - %s %s (ID: %d) - %.2f EUR - %s",
                mjesec, godina, zaposleni.getIme(), zaposleni.getPrezime(),
                zaposleni.getId(), obracunatIznos, napomena);
    }
}
