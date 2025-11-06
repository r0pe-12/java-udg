public class Telefoni extends EProizvodi {
    private String os;
    private int velicina_ekrana;

    public Telefoni(String opis, String sifra, float ucijena, String os, int velicina_ekrana) {
        super(opis, "", ucijena);
        setSifra(sifra);
        setOs(os);
        setVelicina_ekrana(velicina_ekrana);
        this.izracunajMP();
    }

    public static Telefoni fromCsv(String csvLine) {
        String[] parts = csvLine.split(",");
        return new Telefoni(
                parts[0].trim(),
                parts[1].trim(),
                Float.parseFloat(parts[2].trim()),
                parts[3].trim(),
                Integer.parseInt(parts[4].trim())
        );
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public int getVelicina_ekrana() {
        return velicina_ekrana;
    }

    public void setVelicina_ekrana(int velicina_ekrana) {
        this.velicina_ekrana = velicina_ekrana;
    }

    @Override
    public void setSifra(String sifra) {
        if (sifra.startsWith("TE")) {
            super.setSifra(sifra);
        } else {
            System.out.println("Sifra telefona mora pocinjati sa TE");
        }
    }

    public void izracunajMP() {
        float iznos = this.getUcijena();
        if (this.getVelicina_ekrana() > 6) {
            iznos = (float) (1.03 * iznos);
        }
        super.setMpcijena(iznos);
    }

    @Override
    public String toString() {
        return "Telefoni{" +
                super.toString() +
                "os='" + os + '\'' +
                ", velicina_ekrana=" + velicina_ekrana +
                "}";
    }
}