public class Player extends GameObject {
    private String name;
    private int health;

    public Player(double x, double y, ICollidable collider, String name, int health) {
        super(x, y, collider);
        setName(name);
        setHealth(health);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Ime ne moze biti null");
        }

        String processed = name.trim().replaceAll("\\s+", " ");

        if (processed.isEmpty()) {
            throw new IllegalArgumentException("Ime ne moze biti prazno");
        }

        String[] words = processed.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (!words[i].isEmpty()) {
                result.append(Character.toUpperCase(words[i].charAt(0)));
                if (words[i].length() > 1) {
                    result.append(words[i].substring(1).toLowerCase());
                }
                if (i < words.length - 1) {
                    result.append(" ");
                }
            }
        }

        this.name = result.toString();
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
    public String getDisplayName() {
        return name;
    }

    @Override
    public String toString() {
        String colliderInfo = "";
        if (getCollider() instanceof RectangleCollider rect) {
            colliderInfo = String.format("%.0fx%.0f", rect.getWidth(), rect.getHeight());
        } else if (getCollider() instanceof CircleCollider circle) {
            colliderInfo = String.format("R=%.0f", circle.getRadius());
        }
        return String.format("Player[%s] @ (%.0f,%.0f) %s HP=%d",
                           name, getX(), getY(), colliderInfo, health);
    }
}
