import java.time.LocalDateTime;

public class KeramicarskaUsluga extends Usluga {
    public enum TipUsluge {
        POSTAVLJANJE_PLOCICA(1, "Postavljanje Plocica"),
        NIVELACIJA_PODLOGE(1.4, "Nivelacija Podloge"),
        FUGOVANJE(0.7, "Fugovanje");

        private final double ponder;
        private final String naziv;

        TipUsluge(double ponder, String naziv) {
            this.ponder = ponder;
            this.naziv = naziv;
        }

        public String getNaziv() {
            return naziv;
        }

        public double getPonder() {
            return ponder;
        }

        @Override
        public String toString() {
            return naziv + " (Ponder: " + ponder + ")";
        }
    }

    private TipUsluge tipUsluge;
    private final double osnovica = 12;

    public KeramicarskaUsluga(Radnik radnik, LocalDateTime pocetak, double m2, int trajanje, String lokacija, String klijent, TipUsluge tipUsluge) {
        super(radnik, pocetak, m2, trajanje, lokacija, klijent);
        this.tipUsluge = tipUsluge;
        this.obracunajVrijednost();
    }

    public double getOsnovica() {
        return osnovica;
    }

    public TipUsluge getTipUsluge() {
        return tipUsluge;
    }

    public void setTipUsluge(TipUsluge tipUsluge) {
        this.tipUsluge = tipUsluge;
    }

    @Override
    public void obracunajVrijednost() {
        double vrijednost = this.getM2() * this.osnovica * this.tipUsluge.getPonder();
        this.setVrijednost(vrijednost);
    }
}
