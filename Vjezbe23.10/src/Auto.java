import java.util.ArrayList;
import java.util.List;

public class Auto {
    private String markaAuta;
    private int godisteAuta;
    private int snagaMotora;
    private boolean prodato;
    private int kubikazaMotora;
    private boolean registrovan;
    private static int prodatihAuta = 0;

    public Auto(String markaAuta, int godisteAuta, int snagaMotora, boolean prodato, int kubikazaMotora, boolean registrovan) {
        if (godisteAuta < 1985 && registrovan) {
            System.out.println("Auto ne moze biti registrovan jer je starije godiste od 1985.");
            this.registrovan = false;
        }
        if (prodato) {
            prodatihAuta++;
        }
        this.markaAuta = markaAuta;
        this.godisteAuta = godisteAuta;
        this.snagaMotora = snagaMotora;
        this.prodato = prodato;
        this.kubikazaMotora = kubikazaMotora;
        this.registrovan = registrovan;
    }

    public boolean mozeSeRegistrovati() {
        return !this.registrovan && this.godisteAuta >= 1985;
    }

    public double iznosRegistracije() {
        if (!this.registrovan) {
            return 0;
        }
        double koeficijent = (this.godisteAuta >= 1985 && this.godisteAuta <= 2000) ? 3.0 :
                (this.godisteAuta >= 2001 && this.godisteAuta <= 2010) ? 2.0 :
                        (this.godisteAuta >= 2011) ? 1.5 : 0.0;
        return koeficijent * this.kubikazaMotora * this.snagaMotora;
    }

    public static ArrayList<Auto> moguSeRegistrovati(ArrayList<Auto> vozila) {
        ArrayList<Auto> moguSeRegistrovati = new ArrayList<>();
        for (Auto auto : vozila) {
            if (!auto.isRegistrovan() && auto.mozeSeRegistrovati()) {
                moguSeRegistrovati.add(auto);
            }
        }
        return moguSeRegistrovati;
    }

    public static double ukupnaRegistracija(ArrayList<Auto> vozila) {
        double ukupna = 0;
        for (Auto auto : vozila) {
            ukupna += auto.iznosRegistracije();
        }
        return ukupna;
    }

    public String getMarkaAuta() {
        return markaAuta;
    }

    public void setMarkaAuta(String markaAuta) {
        this.markaAuta = markaAuta;
    }

    public int getGodisteAuta() {
        return godisteAuta;
    }

    public void setGodisteAuta(int godisteAuta) {
        if (godisteAuta < 1985 && this.registrovan) {
            System.out.println("Auto ne moze biti registrovan jer je starije godiste od 1985.");
            this.registrovan = false;
        }
        this.godisteAuta = godisteAuta;
    }

    public int getSnagaMotora() {
        return snagaMotora;
    }

    public void setSnagaMotora(int snagaMotora) {
        this.snagaMotora = snagaMotora;
    }

    public boolean isProdato() {
        return prodato;
    }

    public void setProdato(boolean prodato) {
        if (!this.prodato && prodato) {
            prodatihAuta++;
        }
        this.prodato = prodato;
    }

    public int getKubikazaMotora() {
        return kubikazaMotora;
    }

    public void setKubikazaMotora(int kubikazaMotora) {
        this.kubikazaMotora = kubikazaMotora;
    }

    public boolean isRegistrovan() {
        return registrovan;
    }

    public void setRegistrovan(boolean registrovan) {
        if (this.godisteAuta < 1985 && registrovan) {
            System.out.println("Auto ne moze biti registrovan jer je starije godiste od 1985.");
            this.registrovan = false;
        } else {
            this.registrovan = registrovan;
        }
    }

    public static int getProdatihAuta() {
        return prodatihAuta;
    }

    @Override
    public String toString() {
        return "Auto{" + "markaAuta='" + markaAuta + '\'' + ", godisteAuta='" + godisteAuta + '\'' + ", snagaMotora=" + snagaMotora + ", prodato=" + prodato + ", kubikazaMotora=" + kubikazaMotora + ", registrovan=" + registrovan + '}';
    }
}
