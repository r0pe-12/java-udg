import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Usluga {
    private Radnik radnik;
    private LocalDateTime pocetak;
    private double m2;
    private int trajanje;
    private double vrijednost;
    private String lokacija;
    private String klijent;

    public Usluga(Radnik radnik, LocalDateTime pocetak, double m2, int trajanje, String lokacija, String klijent) {
        this.radnik = radnik;
        this.pocetak = pocetak;
        this.m2 = m2;
        this.trajanje = trajanje;
        this.lokacija = lokacija;
        this.klijent = klijent;
    }

    public abstract void obracunajVrijednost();
    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public LocalDateTime getPocetak() {
        return pocetak;
    }

    public void setPocetak(LocalDateTime pocetak) {
        this.pocetak = pocetak;
    }

    public double getM2() {
        return m2;
    }

    public void setM2(double m2) {
        this.m2 = m2;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public double getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(double vrijednost) {
        this.vrijednost = vrijednost;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public String getKlijent() {
        return klijent;
    }

    public void setKlijent(String klijent) {
        this.klijent = klijent;
    }

    public static ArrayList<Usluga> filterPoDatumu(LocalDateTime pocetak, LocalDateTime kraj, ArrayList<Usluga> usluge) {
        ArrayList<Usluga> filtriraneUsluge = new ArrayList<>();
        for (Usluga usluga : usluge) {
            if (usluga.getPocetak().isAfter(pocetak) && usluga.getPocetak().isBefore(kraj)) {
                filtriraneUsluge.add(usluga);
            }
        }
        return filtriraneUsluge;
    }

    public static ArrayList<Usluga> filterPoRadniku(String radnik, ArrayList<Usluga> usluge) {
        ArrayList<Usluga> filtriraneUsluge = new ArrayList<>();
        for (Usluga usluga : usluge) {
            if (usluga.getRadnik().getImePrezime().toLowerCase().contains(radnik.toLowerCase())) {
                filtriraneUsluge.add(usluga);
            }
        }
        return filtriraneUsluge;
    }

    public static ArrayList<Usluga> filterPoKlijentu(String klijent, ArrayList<Usluga> usluge) {
        ArrayList<Usluga> filtriraneUsluge = new ArrayList<>();
        for (Usluga usluga : usluge) {
            if (usluga.getKlijent().toLowerCase().contains(klijent.toLowerCase())) {
                filtriraneUsluge.add(usluga);
            }
        }
        return filtriraneUsluge;
    }

    public static double ukupnaVrijednostUsluga(ArrayList<Usluga> usluge) {
        double ukupnaVrijednost = 0;
        for (Usluga usluga : usluge) {
            ukupnaVrijednost += usluga.getVrijednost();
        }
        return ukupnaVrijednost;
    }

    public static double ukupnaVrijednostUslugaPoDatumu(LocalDateTime pocetak, LocalDateTime kraj, ArrayList<Usluga> usluge) {
        ArrayList<Usluga> filtriraneUsluge = Usluga.filterPoDatumu(pocetak, kraj, usluge);
        return Usluga.ukupnaVrijednostUsluga(filtriraneUsluge);
    }

    public static int brojUslugaPoRadniku(Radnik radnik, ArrayList<Usluga> usluge) {
        ArrayList<Usluga> filtriraneUsluge = Usluga.filterPoRadniku(radnik.getImePrezime(), usluge);
        return filtriraneUsluge.size();
    }

    public static int vrijemeRadaRadnika(Radnik radnik, ArrayList<Usluga> usluge) {
        ArrayList<Usluga> filtriraneUsluge = Usluga.filterPoRadniku(radnik.getImePrezime(), usluge);
        int ukupnoVrijeme = 0;
        for (Usluga usluga : filtriraneUsluge) {
            ukupnoVrijeme += usluga.getTrajanje();
        }
        return ukupnoVrijeme;
    }

    public static double vrijednostUslugaPoRadniku(Radnik radnik, ArrayList<Usluga> usluge) {
        ArrayList<Usluga> filtriraneUsluge = Usluga.filterPoRadniku(radnik.getImePrezime(), usluge);
        return Usluga.ukupnaVrijednostUsluga(filtriraneUsluge);
    }
}
