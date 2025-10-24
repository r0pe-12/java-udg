public class Player {

    private String name;
    private int x;
    private int y;
    private int width;
    private int height;
    private int health;

    public Player(String name, int x, int y, int width, int height, int health) {
        setName(name);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setHealth(health);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ime igraca ne smije biti prazno");
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

        this.name = formattedName.toString();
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        if (health < 0) {
            this.health = 0;
        } else this.health = Math.min(health, 100);
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
        String healthStatus;
        if (health > 75) {
            healthStatus = "odlicno";
        } else if (health > 50) {
            healthStatus = "dobro";
        } else if (health > 25) {
            healthStatus = "lose";
        } else {
            healthStatus = "kriticno";
        }

        return String.format("Igrac '%s' | Pozicija: (%d, %d) | Dimenzije: %dx%d | Health: %d/100 (%s)",
                name, x, y, width, height, health, healthStatus);
    }
}