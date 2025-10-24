/**
 * Klasa koja predstavlja neprijatelja u igri
 *
 * Autori: Andrej Janovic 24/009, Petar Simonovic 24/076
 */
class Enemy {
    private int x;           // X pozicija neprijatelja
    private int y;           // Y pozicija neprijatelja
    private int width;       // Sirina neprijatelja
    private int height;      // Visina neprijatelja
    private int damage;      // Vrijednost napada (0-100)

    /**
     * Konstruktor za kreiranje novog neprijatelja
     */
    public Enemy(int x, int y, int width, int height, int damage) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setDamage(damage);
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

    public int getDamage() {
        return damage;
    }

    /**
     * Postavlja vrijednost napada sa provjerom da bude u opsegu 0-100
     */
    public void setDamage(int damage) {
        if (damage < 0) {
            this.damage = 0;
        } else this.damage = Math.min(damage, 100);
    }
}
