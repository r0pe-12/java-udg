import java.util.ArrayList;

public class Vjetrometar {
    private String nazivMjerneStanice;
    private String datumVrijemeOcitavanja;
    private float maksimalnaBrzinaVjetra;
    private float srednjaBrzinaVjetra;
    private float pravacVjetra;

    public Vjetrometar(String nazivMjerneStanice, String datumVrijemeOcitavanja, float maksimalnaBrzinaVjetra, float srednjaBrzinaVjetra, float pravacVjetra) {
        this.nazivMjerneStanice = nazivMjerneStanice;
        this.datumVrijemeOcitavanja = datumVrijemeOcitavanja;
        this.maksimalnaBrzinaVjetra = maksimalnaBrzinaVjetra;
        this.srednjaBrzinaVjetra = srednjaBrzinaVjetra;
        this.pravacVjetra = pravacVjetra;
    }

    public static ArrayList<Vjetrometar> vjetrometriPoDatumu(ArrayList<Vjetrometar> vjetrometri, String datum) {
        ArrayList<Vjetrometar> vjetrometriPoDatumu = new ArrayList<>();
        for (Vjetrometar vjetrometar : vjetrometri) {
            if (vjetrometar.datumVrijemeOcitavanja.toLowerCase().contains(datum.toLowerCase())) {
                vjetrometriPoDatumu.add(vjetrometar);
            }
        }
        return vjetrometriPoDatumu;
    }

    public static ArrayList<Vjetrometar> vjetrometriPoStanici(ArrayList<Vjetrometar> vjetrometri, String stanica) {
        ArrayList<Vjetrometar> vjetrometriPoStanici = new ArrayList<>();
        for (Vjetrometar vjetrometar : vjetrometri) {
            if (vjetrometar.nazivMjerneStanice.toLowerCase().contains(stanica.toLowerCase())) {
                vjetrometriPoStanici.add(vjetrometar);
            }
        }
        return vjetrometriPoStanici;
    }

    public static Vjetrometar maksUdarVjetra(ArrayList<Vjetrometar> vjetrometri, String datum, String stanica) {
        Vjetrometar maksimalnaBrzinaVjetra = vjetrometri.getFirst();
        float brzina = maksimalnaBrzinaVjetra.maksimalnaBrzinaVjetra;
        ArrayList<Vjetrometar> vjetrometriPoDatumuIStanici = vjetrometriPoStanici(vjetrometriPoDatumu(vjetrometri, datum), stanica);
        for (Vjetrometar vjetrometar : vjetrometriPoDatumuIStanici) {
            if (vjetrometar.maksimalnaBrzinaVjetra > brzina) {
                maksimalnaBrzinaVjetra = vjetrometar;
            }
        }

        return maksimalnaBrzinaVjetra;
    }

    public String getNazivMjerneStanice() {
        return nazivMjerneStanice;
    }

    public void setNazivMjerneStanice(String nazivMjerneStanice) {
        this.nazivMjerneStanice = nazivMjerneStanice;
    }

    public String getDatumVrijemeOcitavanja() {
        return datumVrijemeOcitavanja;
    }

    public void setDatumVrijemeOcitavanja(String datumVrijemeOcitavanja) {
        this.datumVrijemeOcitavanja = datumVrijemeOcitavanja;
    }

    public float getMaksimalnaBrzinaVjetra() {
        return maksimalnaBrzinaVjetra;
    }

    public void setMaksimalnaBrzinaVjetra(float maksimalnaBrzinaVjetra) {
        this.maksimalnaBrzinaVjetra = maksimalnaBrzinaVjetra;
    }

    public float getSrednjaBrzinaVjetra() {
        return srednjaBrzinaVjetra;
    }

    public void setSrednjaBrzinaVjetra(float srednjaBrzinaVjetra) {
        this.srednjaBrzinaVjetra = srednjaBrzinaVjetra;
    }

    public float getPravacVjetra() {
        return pravacVjetra;
    }

    public void setPravacVjetra(float pravacVjetra) {
        this.pravacVjetra = pravacVjetra;
    }

    @Override
    public String toString() {
        return "Vjetrometar{" +
                "nazivMjerneStanice='" + nazivMjerneStanice + '\'' +
                ", datumVrijemeOcitavanja='" + datumVrijemeOcitavanja + '\'' +
                ", maksimalnaBrzinaVjetra=" + maksimalnaBrzinaVjetra +
                ", srednjaBrzinaVjetra=" + srednjaBrzinaVjetra +
                ", pravacVjetra=" + pravacVjetra +
                '}';
    }
}
