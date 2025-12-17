import java.time.LocalDateTime;

public class VodoinstalaterskaUsluga extends Usluga {
    private final double cijena_m = 8.5;
    private final double cijena_sat = 18;
    private ObracunTip obracunTip;

    public enum ObracunTip {
        PO_M,
        PO_SATU
    }

    public VodoinstalaterskaUsluga(Radnik radnik, LocalDateTime pocetak, double m2, int trajanje, String lokacija, String klijent, ObracunTip obracunTip) {
        super(radnik, pocetak, m2, trajanje, lokacija, klijent);
        this.obracunTip = obracunTip;
        this.obracunajVrijednost();
    }

    public double getCijena_m() {
        return cijena_m;
    }

    public double getCijena_sat() {
        return cijena_sat;
    }

    public ObracunTip getObracunTip() {
        return obracunTip;
    }

    public void setObracunTip(ObracunTip obracunTip) {
        this.obracunTip = obracunTip;
    }

    @Override
    public void obracunajVrijednost() {
        double vrijednost = 0;
        if (this.obracunTip == ObracunTip.PO_M) {
            vrijednost = this.getM2() * this.cijena_m;
        } else if (this.obracunTip == ObracunTip.PO_SATU) {
            vrijednost = (this.getTrajanje() / 60.0) * this.cijena_sat;
        }

        this.setVrijednost(vrijednost);
    }
}
