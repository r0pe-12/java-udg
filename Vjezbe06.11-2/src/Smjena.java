import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
import java.util.ArrayList;

public class Smjena {
    private LocalDate datum;
    private LocalTime pocetak;
    private LocalTime kraj;
    private TipSmjene tipSmjene;
    private final ArrayList<Zaposleni> zaposleni;

    public Smjena(LocalDate datum, LocalTime pocetak, LocalTime kraj, TipSmjene tipSmjene) {
        this.datum = datum;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.tipSmjene = tipSmjene;
        this.zaposleni = new ArrayList<>();
    }

    public void dodajZaposlenog(Zaposleni z) {
        if (!zaposleni.contains(z)) {
            zaposleni.add(z);
        }
    }

    public void ukloniZaposlenog(int id) {
        zaposleni.removeIf(z -> z.getId() == id);
    }

    public int trajanjeSati() {
        Duration duration = Duration.between(pocetak, kraj);
        return (int) duration.toHours();
    }

    public LocalDate getDatum() {
        return datum;
    }

    public LocalTime getPocetak() {
        return pocetak;
    }

    public LocalTime getKraj() {
        return kraj;
    }

    public TipSmjene getTipSmjene() {
        return tipSmjene;
    }

    public ArrayList<Zaposleni> getZaposleni() {
        return zaposleni;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public void setPocetak(LocalTime pocetak) {
        this.pocetak = pocetak;
    }

    public void setKraj(LocalTime kraj) {
        this.kraj = kraj;
    }

    public void setTipSmjene(TipSmjene tipSmjene) {
        this.tipSmjene = tipSmjene;
    }

    @Override
    public String toString() {
        return String.format("%s | %s - %s (%s) | %d sati | %d zaposlenih",
                datum, pocetak, kraj, tipSmjene, trajanjeSati(), zaposleni.size());
    }
}
