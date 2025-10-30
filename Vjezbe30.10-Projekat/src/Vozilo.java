class Vozilo {
    private String proizvodjac;
    private int godinaProizvodnje;
    private int kubikaza;
    private String boja;

    public Vozilo(String proizvodjac, int godinaProizvodnje, int kubikaza, String boja) {
        this.proizvodjac = proizvodjac;
        this.godinaProizvodnje = godinaProizvodnje;
        this.kubikaza = kubikaza;
        this.boja = boja;
    }

    public int iznosRegistracije() {
        int iznos = 100;
        if (this.godinaProizvodnje < 2010) {
            iznos += 30;
        }
        if (this.kubikaza > 2000) {
            iznos += 50;
        }

        return iznos;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public int getGodinaProizvodnje() {
        return godinaProizvodnje;
    }

    public void setGodinaProizvodnje(int godinaProizvodnje) {
        this.godinaProizvodnje = godinaProizvodnje;
    }

    public int getKubikaza() {
        return kubikaza;
    }

    public void setKubikaza(int kubikaza) {
        this.kubikaza = kubikaza;
    }

    public String getBoja() {
        return boja;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

    @Override
    public String toString() {
        return "{" + "proizvodjac='" + proizvodjac + '\'' + ", godinaProizvodnje=" + godinaProizvodnje + ", kubikaza=" + kubikaza + ", boja='" + boja + '\'';
    }
}