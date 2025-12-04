public class BossEnemy extends Enemy {

    public BossEnemy(String type, int x, int y, Collidable collider, int damage, int health) {
        super(type, x, y, collider, damage, health);
    }

    // BossEnemy redefinira getEffectiveDamage da vraća dvostruku vrijednost
    @Override
    public int getEffectiveDamage() {
        return getDamage() * 2;
    }

    @Override
    public String toString() {
        return String.format("BOSS %s", super.toString());
    }
}
