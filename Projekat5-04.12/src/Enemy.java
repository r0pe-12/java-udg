public abstract class Enemy extends GameObject implements Attacker {
    private String type;
    private int damage;
    private int health;

    public Enemy(String type, int x, int y, Collidable collider, int damage, int health) {
        super(x, y, collider);
        setType(type);
        setDamage(damage);
        setHealth(health);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Tip neprijatelja ne smije biti prazan");
        }
        this.type = type.trim();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        if (damage < 0 || damage > 100) {
            throw new IllegalArgumentException("Damage mora biti u opsegu 0-100");
        }
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0 || health > 100) {
            throw new IllegalArgumentException("Health mora biti u opsegu 0-100");
        }
        this.health = health;
    }

    @Override
    public String getDisplayName() {
        return type;
    }

    // Osnovna implementacija - vraća standardnu vrijednost damage-a
    @Override
    public int getEffectiveDamage() {
        return damage;
    }

    @Override
    public String toString() {
        String threatLevel;
        int effectiveDamage = getEffectiveDamage();
        if (effectiveDamage > 75) {
            threatLevel = "ekstremno opasan";
        } else if (effectiveDamage > 50) {
            threatLevel = "opasan";
        } else if (effectiveDamage > 25) {
            threatLevel = "umjereno opasan";
        } else {
            threatLevel = "mala prijetnja";
        }

        return String.format("Neprijatelj: %s | Pozicija: (%d, %d) | Collider: %s | Health: %d/100 | Napad: %d (%s)",
                type, getX(), getY(), getCollider(), health, effectiveDamage, threatLevel);
    }
}
