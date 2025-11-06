public class TV extends EProizvodi {
    private int velicina_ekrana;

    public TV(String opis, String sifra, float ucijena, int velicina_ekrana) {
        super(opis, "", ucijena);
        setSifra(sifra);
        setVelicina_ekrana(velicina_ekrana);
        this.izracunajMP();
    }

    public static TV fromCsv(String csvLine) {
        String[] parts = csvLine.split(",");
        return new TV(
                parts[0].trim(),
                parts[1].trim(),
                Float.parseFloat(parts[2].trim()),
                Integer.parseInt(parts[3].trim())
        );
    }

    public int getVelicina_ekrana() {
        return velicina_ekrana;
    }

    public void setVelicina_ekrana(int velicina_ekrana) {
        this.velicina_ekrana = velicina_ekrana;
    }

    @Override
    public void setSifra(String sifra) {
        if (sifra.startsWith("TV")) {
            super.setSifra(sifra);
        } else {
            System.out.println("Sifra televizora mora pocinjati sa TV");
        }
    }

    public void izracunajMP() {
        float iznos = this.getUcijena();
        if (this.getVelicina_ekrana() > 65) {
            iznos = (float) (1.1 * iznos);
        }
        super.setMpcijena(iznos);
    }

    @Override
    public String toString() {
        return "TV{" +
                super.toString() +
                "velicina_ekrana=" + velicina_ekrana +
                "}";
    }
}