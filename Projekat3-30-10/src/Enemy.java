public class Enemy extends GameObject {
    String type;
    int damage;

    public Enemy(int x, int y, int w, int h, String type, int damage, int hp) {
        super(x, y, w, h, hp);
        setType(type);
        setDamage(damage);
    }

    public Enemy(String enemyString) {
        String[] parts = enemyString.split(";");
        String type = parts[0];
        String[] position = parts[1].split(",");
        int x = Integer.parseInt(position[0]);
        int y = Integer.parseInt(position[1]);
        String[] dimensions = parts[2].split("x");
        int width = Integer.parseInt(dimensions[0]);
        int height = Integer.parseInt(dimensions[1]);
        int damage = Integer.parseInt(parts[3]);
        int hp = Integer.parseInt(parts[4]);

        this(x, y, width, height, type, damage, hp);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Tip neprijatelja ne smije biti prazan");
        }
        this.type = type.trim().toLowerCase();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        if (damage < 0) {
            this.damage = 0;
        } else {
            this.damage = Math.min(damage, 100);
        }
    }

    @Override
    public String toString() {
        return "Enemy[" + type + "] @ (" + getX() + ", " + getY() + ") " + getW() + "x" + getH() + " DMG= " + getDamage() + " HP=" + getHp();
    }
}