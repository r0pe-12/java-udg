public class Enemy extends GameObject implements IAttacker {
    private String type;
    private int damage;
    private int health;

    public Enemy(double x, double y, ICollidable collider, String type, int damage, int health) {
        super(x, y, collider);
        setType(type);
        setDamage(damage);
        setHealth(health);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Tip neprijatelja ne moze biti null");
        }

        String trimmed = type.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Tip neprijatelja ne moze biti prazan");
        }

        this.type = trimmed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        if (damage < 0 || damage > 100) {
            throw new IllegalArgumentException("Damage mora biti izmedju 0 i 100");
        }
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0 || health > 100) {
            throw new IllegalArgumentException("Health mora biti izmedju 0 i 100");
        }
        this.health = health;
    }

    @Override
    public int getEffectiveDamage() {
        return damage;
    }

    @Override
    public String getDisplayName() {
        return type;
    }

    @Override
    public String toString() {
        String colliderInfo = "";
        if (getCollider() instanceof RectangleCollider rect) {
            colliderInfo = String.format("%.0fx%.0f", rect.getWidth(), rect.getHeight());
        } else if (getCollider() instanceof CircleCollider circle) {
            colliderInfo = String.format("R=%.0f", circle.getRadius());
        }
        return String.format("Enemy[%s] @ (%.0f,%.0f) %s DMG=%d HP=%d",
                           type, getX(), getY(), colliderInfo, damage, health);
    }
}
