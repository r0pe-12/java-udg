class Kombi extends Vozilo {
    private int kapacitetPutnika;

    public Kombi(String proizvodjac, int godinaProizvodnje, int kubikaza, String boja, int kapacitetPutnika) {
        super(proizvodjac, godinaProizvodnje, kubikaza, boja);
        this.kapacitetPutnika = kapacitetPutnika;
    }

    public int iznosRegistracije() {
        int iznos = super.iznosRegistracije();
        if (kapacitetPutnika > 8) {
            iznos += 30;
        }
        return iznos;
    }

    public int getKapacitetPutnika() {
        return kapacitetPutnika;
    }

    public void setKapacitetPutnika(int kapacitetPutnika) {
        this.kapacitetPutnika = kapacitetPutnika;
    }

    @Override
    public String toString() {
        return super.toString() + ", kapacitetPutnika=" + kapacitetPutnika + ", iznosRegistracije=" + iznosRegistracije() + '}';
    }
}