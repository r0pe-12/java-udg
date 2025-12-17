import java.time.LocalDateTime;
import java.util.ArrayList;

public class Posao {
    private int id;
    private double cijena;
    private ArrayList<Usluga> usluge;

    //ovo nam je kad ocemo novi posao
    public Posao(int id) {
        this.id = id;
    }

    public Posao(ArrayList<Usluga> usluge, int id) {
        this.id = id;
        this.usluge = usluge;
        this.obracunajCijenu();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public ArrayList<Usluga> getUsluge() {
        return usluge;
    }

    public void setUsluge(ArrayList<Usluga> usluge) {
        this.usluge = usluge;
        obracunajCijenu();
    }

    public void obracunajCijenu() {
        this.cijena = Usluga.ukupnaVrijednostUsluga(usluge);
    }

    public static ArrayList<Usluga> filterPoKlijentu(ArrayList<Posao> poslovi, String klijent) {
        ArrayList<Usluga> filtriraneUsluge = new ArrayList<>();
        for (Posao posao : poslovi) {
            filtriraneUsluge.addAll(Usluga.filterPoKlijentu(klijent, posao.getUsluge()));
        }
        return filtriraneUsluge;
    }

    public static ArrayList<Usluga> filterPoRadniku(ArrayList<Posao> poslovi, String radnik) {
        ArrayList<Usluga> filtriraneUsluge = new ArrayList<>();
        for (Posao posao : poslovi) {
            filtriraneUsluge.addAll(Usluga.filterPoRadniku(radnik, posao.getUsluge()));
        }
        return filtriraneUsluge;
    }

    public static ArrayList<Usluga> filterPoDatumu(ArrayList<Posao> poslovi, LocalDateTime pocetak, LocalDateTime kraj) {
        ArrayList<Usluga> filtriraneUsluge = new ArrayList<>();
        for (Posao posao : poslovi) {
            filtriraneUsluge.addAll(Usluga.filterPoDatumu(pocetak, kraj, posao.getUsluge()));
        }
        return filtriraneUsluge;
    }

    @Override
    public String toString() {
        if (this.id == 0) {
            return "Novi posao";
        }
        return String.format("%d", this.id);
    }
}
