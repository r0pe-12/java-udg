public class BossEnemy extends Enemy {
    public BossEnemy(int x, int y, int w, int h, String type, int damage, int hp) {
        super(x, y, w, h, type, 2*damage, hp);
    }

    @Override
    public String toString() {
        return "BossEnemy[" + type + "] @ (" + getX() + ", " + getY() + ") " + getW() + "x" + getH() + " DMG= " + getDamage() + " HP=" + getHp();
    }
}