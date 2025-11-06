public class Racunari extends EProizvodi {
    private String procesor;
    private int memorija;

    public Racunari(String opis, String sifra, float ucijena, String procesor, int memorija) {
        super(opis, "", ucijena);
        setSifra(sifra);
        setProcesor(procesor);
        setMemorija(memorija);
        this.izracunajMP();
    }

    public static Racunari fromCsv(String csvLine) {
        String[] parts = csvLine.split(",");
        return new Racunari(
                parts[0].trim(),
                parts[1].trim(),
                Float.parseFloat(parts[2].trim()),
                parts[3].trim(),
                Integer.parseInt(parts[4].trim())
        );
    }

    public String getProcesor() {
        return procesor;
    }

    public void setProcesor(String procesor) {
        this.procesor = procesor;
    }

    public int getMemorija() {
        return memorija;
    }

    public void setMemorija(int memorija) {
        this.memorija = memorija;
    }

    @Override
    public void setSifra(String sifra) {
        if (sifra.startsWith("RA")) {
            super.setSifra(sifra);
        } else {
            System.out.println("Sifra racunara mora pocinjati sa RA");
        }
    }

    public void izracunajMP() {
        super.setMpcijena((float) (1.05 * this.getUcijena()));
    }

    @Override
    public String toString() {
        return "Racunari" +
                super.toString() +
                "procesor='" + procesor + '\'' +
                ", memorija=" + memorija +
                "}";
    }
}