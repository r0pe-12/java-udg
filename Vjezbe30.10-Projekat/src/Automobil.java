class Automobil extends Vozilo {
    private int brojVrata;
    private String tipMotora;

    public Automobil(String proizvodjac, int godinaProizvodnje, String tipMotora, int kubikaza, String boja, int brojVrata) {
        super(proizvodjac, godinaProizvodnje, kubikaza, boja);
        this.brojVrata = brojVrata;
        this.tipMotora = tipMotora;
    }

    public int getBrojVrata() {
        return brojVrata;
    }

    public int iznosRegistracije() {
        int iznos = super.iznosRegistracije();
        if (tipMotora.equalsIgnoreCase("dizel")) {
            iznos += 20;
        }
        return iznos;
    }

    public void setBrojVrata(int brojVrata) {
        this.brojVrata = brojVrata;
    }

    public String getTipMotora() {
        return tipMotora;
    }

    public void setTipMotora(String tipMotora) {
        this.tipMotora = tipMotora;
    }

    @Override
    public String toString() {
        return super.toString() + ", brojVrata=" + brojVrata + ", tipMotora='" + tipMotora + '\'' + ", iznosRegistracije=" + iznosRegistracije() + '}';
    }
}