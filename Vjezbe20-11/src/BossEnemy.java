public class BossEnemy extends Enemy {

    public BossEnemy(double x, double y, ICollidable collider, String type, int damage, int health) {
        super(x, y, collider, type, damage, health);
    }

    @Override
    public int getEffectiveDamage() {
        return getDamage() * 2;
    }

    @Override
    public String toString() {
        String colliderInfo = "";
        if (getCollider() instanceof RectangleCollider rect) {
            colliderInfo = String.format("%.0fx%.0f", rect.getWidth(), rect.getHeight());
        } else if (getCollider() instanceof CircleCollider circle) {
            colliderInfo = String.format("R=%.0f", circle.getRadius());
        }
        return String.format("BossEnemy[%s] @ (%.0f,%.0f) %s DMG=%dx2 HP=%d",
                           getType(), getX(), getY(), colliderInfo, getDamage(), getHealth());
    }
}
