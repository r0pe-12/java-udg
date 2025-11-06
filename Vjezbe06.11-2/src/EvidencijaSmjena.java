import java.util.ArrayList;
import java.util.HashMap;

public class EvidencijaSmjena {
    private final ArrayList<Smjena> smjene;
    private final HashMap<Integer, Integer> ukupnoSatiPoZaposlenom;

    public EvidencijaSmjena() {
        this.smjene = new ArrayList<>();
        this.ukupnoSatiPoZaposlenom = new HashMap<>();
    }

    public void dodajSmjenu(Smjena smjena) {
        smjene.add(smjena);
        azurirajSate();
    }

    public void ukloniSmjenu(Smjena smjena) {
        smjene.remove(smjena);
        azurirajSate();
    }

    private void azurirajSate() {
        ukupnoSatiPoZaposlenom.clear();
        for (Smjena smjena : smjene) {
            int trajanje = smjena.trajanjeSati();
            for (Zaposleni z : smjena.getZaposleni()) {
                int id = z.getId();
                ukupnoSatiPoZaposlenom.put(id, ukupnoSatiPoZaposlenom.getOrDefault(id, 0) + trajanje);
            }
        }
    }

    public int izracunajSateZaZaposlenog(int id) {
        return ukupnoSatiPoZaposlenom.getOrDefault(id, 0);
    }

    public void azurirajSateZaposlenih(ArrayList<Zaposleni> zaposleni) {
        for (Zaposleni z : zaposleni) {
            int sati = izracunajSateZaZaposlenog(z.getId());
            z.setUkupanBrojSati(sati);
        }
    }

    public ArrayList<Smjena> getSmjeneZaZaposlenog(int id) {
        ArrayList<Smjena> rezultat = new ArrayList<>();
        for (Smjena smjena : smjene) {
            for (Zaposleni z : smjena.getZaposleni()) {
                if (z.getId() == id) {
                    rezultat.add(smjena);
                    break;
                }
            }
        }
        return rezultat;
    }

    public ArrayList<Smjena> getSvesmjene() {
        return smjene;
    }

    public HashMap<Integer, Integer> getUkupnoSatiPoZaposlenom() {
        return ukupnoSatiPoZaposlenom;
    }
}
