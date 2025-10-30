class Kamion extends Vozilo {
    private float kapacitetTereta;
    private boolean prikolica;

    public Kamion(String proizvodjac, int godinaProizvodnje, int kubikaza, String boja, float kapacitetTereta, boolean prikolica) {
        super(proizvodjac, godinaProizvodnje, kubikaza, boja);
        this.kapacitetTereta = kapacitetTereta;
        this.prikolica = prikolica;
    }

    public int iznosRegistracije() {
        int iznos = super.iznosRegistracije();
        if (prikolica) {
            iznos += 50;
        }

        return iznos;
    }

    public float getKapacitetTereta() {
        return kapacitetTereta;
    }

    public void setKapacitetTereta(float kapacitetTereta) {
        this.kapacitetTereta = kapacitetTereta;
    }

    public boolean isPrikolica() {
        return prikolica;
    }

    public void setPrikolica(boolean prikolica) {
        this.prikolica = prikolica;
    }

    @Override
    public String toString() {
        return super.toString() + ", kapacitetTereta=" + kapacitetTereta + ", prikolica=" + prikolica + ", iznosRegistracije=" + iznosRegistracije() + '}';
    }
}