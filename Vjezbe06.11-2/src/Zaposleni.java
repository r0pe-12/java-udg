public class Zaposleni {
    private int id;
    private String ime;
    private String prezime;
    private double plataPoSatu;
    private int ukupanBrojSati;
    private TipZaposlenog tipZaposlenog;
    private int prekovremenihSati;
    private double bonus;

    public Zaposleni(int id, String ime, String prezime, double plataPoSatu, int ukupanBrojSati, int prekovremenihSati) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.plataPoSatu = plataPoSatu;
        this.ukupanBrojSati = ukupanBrojSati;
        this.prekovremenihSati = prekovremenihSati;
        this.tipZaposlenog = TipZaposlenog.KONOBAR;
        this.bonus = 0;
    }

    public Zaposleni(int id, String ime, String prezime, double plataPoSatu, int ukupanBrojSati, TipZaposlenog tip) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.plataPoSatu = plataPoSatu;
        this.ukupanBrojSati = ukupanBrojSati;
        this.tipZaposlenog = tip;
        this.prekovremenihSati = 0;
        this.bonus = 0;
    }

    public Zaposleni(int id, String ime, String prezime, double plataPoSatu, int ukupanBrojSati, double bonus) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.plataPoSatu = plataPoSatu;
        this.ukupanBrojSati = ukupanBrojSati;
        this.bonus = bonus;
        this.tipZaposlenog = TipZaposlenog.MENADZER;
        this.prekovremenihSati = 0;
    }

    public double izracunajMjesecnuPlatu() {
        switch (tipZaposlenog) {
            case KONOBAR:
                double redovnaPlata = ukupanBrojSati * plataPoSatu;
                double prekovremenePlata = prekovremenihSati * plataPoSatu * 1.2;
                return 4 * (redovnaPlata + prekovremenePlata);
            case KUVAR:
                return 1500 + 4 * ukupanBrojSati * plataPoSatu;
            case MENADZER:
                return 1300 + 4 * ukupanBrojSati * plataPoSatu + bonus;
            default:
                return 0;
        }
    }

    public int getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public double getPlataPoSatu() {
        return plataPoSatu;
    }

    public int getUkupanBrojSati() {
        return ukupanBrojSati;
    }

    public TipZaposlenog getTipZaposlenog() {
        return tipZaposlenog;
    }

    public int getPrekovremenihSati() {
        return prekovremenihSati;
    }

    public double getBonus() {
        return bonus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setPlataPoSatu(double plataPoSatu) {
        this.plataPoSatu = plataPoSatu;
    }

    public void setUkupanBrojSati(int ukupanBrojSati) {
        this.ukupanBrojSati = ukupanBrojSati;
    }

    public void setTipZaposlenog(TipZaposlenog tipZaposlenog) {
        this.tipZaposlenog = tipZaposlenog;
    }

    public void setPrekovremenihSati(int prekovremenihSati) {
        this.prekovremenihSati = prekovremenihSati;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return String.format("%s %s (ID: %d, Tip: %s) - Mjesečna plata: %.2f EUR",
                ime, prezime, id, tipZaposlenog, izracunajMjesecnuPlatu());
    }
}
