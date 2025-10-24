/**
 * Klasa koja predstavlja igraca u igri
 *
 * Autori: Andrej Janovic 24/009, Petar Simonovic 24/076
 */
class Player {
    private int x;           // X pozicija igraca
    private int y;           // Y pozicija igraca
    private int width;       // Sirina igraca
    private int height;      // Visina igraca
    private int health;      // Zdravlje igraca (0-100)

    /**
     * Konstruktor za kreiranje novog igraca
     */
    public Player(int x, int y, int width, int height, int health) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setHealth(health);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHealth() {
        return health;
    }

    /**
     * Postavlja zdravlje igraca sa provjerom da bude u opsegu 0-100
     */
    public void setHealth(int health) {
        if (health < 0) {
            this.health = 0;
        } else this.health = Math.min(health, 100);
    }
}
