import java.time.LocalDateTime;

public class Usluga {
    protected int radnik_id;
    protected int klijent_id;
    protected LocalDateTime pocetak;
    protected int trajanjeMin;
    private float kvadratura;
    private float vrijednost;
    private float ponder;

    public enum Tip {ELEKTRICARSKI, MOLERSKI}
}
