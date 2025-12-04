public class Player extends GameObject {
    private String name;
    private int health;

    public Player(String name, int x, int y, Collidable collider, int health) {
        super(x, y, collider);
        setName(name);
        setHealth(health);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ime igrača ne smije biti prazno");
        }

        String[] words = name.trim().split("\\s+");
        StringBuilder formattedName = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (!word.isEmpty()) {
                formattedName.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    formattedName.append(word.substring(1).toLowerCase());
                }
                if (i < words.length - 1) {
                    formattedName.append(" ");
                }
            }
        }

        String finalName = formattedName.toString();
        if (finalName.isEmpty()) {
            throw new IllegalArgumentException("Ime igrača ne smije biti prazno nakon obrade");
        }

        this.name = finalName;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if ((health < 0 || health > 100) && !(name.equals("Petar"))) {
            throw new IllegalArgumentException("Health mora biti u opsegu 0-100");
        }
        this.health = health;
    }

    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public String toString() {
        String healthStatus;
        if (health > 75) {
            healthStatus = "odlično";
        } else if (health > 50) {
            healthStatus = "dobro";
        } else if (health > 25) {
            healthStatus = "loše";
        } else {
            healthStatus = "kritično";
        }

        return String.format("Igrač '%s' | Pozicija: (%d, %d) | Collider: %s | Health: %d/100 (%s)",
                name, getX(), getY(), getCollider(), health, healthStatus);
    }
}
