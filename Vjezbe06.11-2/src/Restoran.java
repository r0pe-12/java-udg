import java.util.ArrayList;

public class Restoran {
    private String naziv;
    private String adresa;
    private String pib;
    private final ArrayList<Zaposleni> zaposleni;

    public Restoran(String naziv, String adresa, String pib) {
        this.naziv = naziv;
        this.adresa = adresa;
        this.pib = pib;
        this.zaposleni = new ArrayList<>();
    }

    public boolean dodajZaposlenog(Zaposleni z) {
        for (Zaposleni postojeci : zaposleni) {
            if (postojeci.getId() == z.getId()) {
                return false;
            }
        }
        zaposleni.add(z);
        return true;
    }

    public boolean ukloniZaposlenog(int id) {
        return zaposleni.removeIf(z -> z.getId() == id);
    }

    public Zaposleni pronadjiZaposlenog(int id) {
        for (Zaposleni z : zaposleni) {
            if (z.getId() == id) {
                return z;
            }
        }
        return null;
    }

    public ArrayList<ObracunPlate> generisiMjesecniObracun(int mjesec, int godina) {
        ArrayList<ObracunPlate> obracuni = new ArrayList<>();
        for (Zaposleni z : zaposleni) {
            obracuni.add(new ObracunPlate(mjesec, godina, z));
        }
        return obracuni;
    }

    public double ukupanTrosakPlata() {
        double suma = 0;
        for (Zaposleni z : zaposleni) {
            suma += z.izracunajMjesecnuPlatu();
        }
        return suma;
    }

    public void prikaziTabeluObracuna(ArrayList<ObracunPlate> obracuni) {
        TabelaPrinter.stampajObracune(obracuni);
    }

    public String getNaziv() {
        return naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getPib() {
        return pib;
    }

    public ArrayList<Zaposleni> getZaposleni() {
        return zaposleni;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    @Override
    public String toString() {
        return String.format("Restoran: %s | Adresa: %s | PIB: %s | Zaposlenih: %d",
                naziv, adresa, pib, zaposleni.size());
    }
}
