abstract class EProizvodi {
    private String opis;
    private String sifra;
    private float ucijena;
    private float mpcijena;

    public EProizvodi(String opis, String sifra, float ucijena) {
        this.opis = opis;
        this.sifra = sifra;
        this.ucijena = ucijena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public float getUcijena() {
        return ucijena;
    }

    public void setUcijena(float ucijena) {
        this.ucijena = ucijena;
    }

    public float getMpcijena() {
        return mpcijena;
    }

    public void setMpcijena(float mpcijena) {
        this.mpcijena = (float) (1.05 * mpcijena);
    }

    @Override
    public String toString() {
        return "EProizvodi{" +
                "opis='" + opis + '\'' +
                ", sifra='" + sifra + '\'' +
                ", ucijena=" + ucijena +
                ", mpcijena=" + mpcijena + " ";
    }
}