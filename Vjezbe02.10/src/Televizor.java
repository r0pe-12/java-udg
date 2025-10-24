public class Televizor {

    private int brojKanala;
    private String nazivKanala;
    private int jacinaTona;

    public Televizor(int brojKanala, String nazivKanala, int jacinaTona) {
        this.brojKanala = Math.max(brojKanala, 1);
        this.nazivKanala = nazivKanala;
        if (jacinaTona >= 0 && jacinaTona <= 10) {
            this.jacinaTona = jacinaTona;
        } else this.jacinaTona = 5;
    }
}
