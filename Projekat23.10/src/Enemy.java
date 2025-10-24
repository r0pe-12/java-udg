public class Enemy{
    private String type;
    private int x;
    private int y;
    private int width;
    private int height;
    private int damage;

    public Enemy(String type, int x, int y, int width, int height, int damage) {
        setType(type);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

        this(type, x, y, width, height, damage);
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
    public int getX() {
        return x;
    }
    public int setX(int x) {
        this.x = x;
        return x;
    }
    public int getY() {
        return y;
    }
    public int setY(int y) {
        this.y = y;
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int setWidth(int width) {
        this.width = width;
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int setHeight(int height) {
        this.height = height;
        return height;
    }

    @Override
    public String toString() {
        String threatLevel;
        if (damage > 75) {
            threatLevel = "ekstremno opasan";
        } else if (damage > 50) {
            threatLevel = "opasan";
        } else if (damage > 25) {
            threatLevel = "umjereno opasan";
        } else {
            threatLevel = "mala prijetnja";
        }

        String displayType = type.substring(0, 1).toUpperCase() + type.substring(1);

        return String.format("Neprijatelj: %s | Pozicija: (%d, %d) | Dimenzije: %dx%d | Napad: %d/100 (%s)",
                displayType, x, y, width, height, damage, threatLevel);
    }
}