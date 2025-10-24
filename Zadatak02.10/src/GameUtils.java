/**
 * Pomocna klasa za igru sa metodama za provjeru sudara i smanjenje zdravlja
 * Autori: Andrej Janovic 24/009, Petar Simonovic 24/076
 */
class GameUtils {

    /**
     * Provjerava da li se igrac i neprijatelj preklapaju (sudaraju)
     *
     * @param p - igrac
     * @param e - neprijatelj
     * @return true ako postoji sudar, false ako nema sudara
     */
    public static boolean checkCollision(Player p, Enemy e) {
        return p.getX() < e.getX() + e.getWidth() &&
               p.getX() + p.getWidth() > e.getX() &&
               p.getY() < e.getY() + e.getHeight() &&
               p.getY() + p.getHeight() > e.getY();
    }

    /**
     * Smanjuje zdravlje igraca za vrijednost napada neprijatelja
     *
     * @param p - igrac cije se zdravlje smanjuje
     * @param e - neprijatelj koji nanosi stetu
     */
    public static void decreaseHealth(Player p, Enemy e) {
        int newHealth = p.getHealth() - e.getDamage();
        p.setHealth(newHealth);
    }
}
