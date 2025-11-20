import java.util.ArrayList;

public class Game {
    private Player player;
    private final ArrayList<Enemy> enemies;
    private final ArrayList<String> eventLog;

    public Game(Player player) {
        this.player = player;
        this.enemies = new ArrayList<>();
        this.eventLog = new ArrayList<>();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<String> getEventLog() {
        return eventLog;
    }

    public boolean checkCollision(Player p, Enemy e) {
        if (p == null || e == null) {
            throw new IllegalArgumentException("Player i Enemy ne mogu biti null");
        }
        return p.intersects(e);
    }

    public void decreaseHealth(Player p, Enemy e) {
        if (p == null || e == null) {
            throw new IllegalArgumentException("Player i Enemy ne mogu biti null");
        }

        int currentHealth = p.getHealth();
        int effectiveDamage = e.getEffectiveDamage();
        int newHealth = currentHealth - effectiveDamage;

        if (newHealth < 0) {
            newHealth = 0;
        }

        p.setHealth(newHealth);

        String logEntry = String.format("HIT: Player by %s for %d -> HP %d -> %d",
                                       e.getType(), effectiveDamage, currentHealth, newHealth);
        eventLog.add(logEntry);
    }

    public void addEnemy(Enemy e) {
        if (e == null) {
            throw new IllegalArgumentException("Enemy ne može biti null");
        }
        enemies.add(e);
        eventLog.add("ADDED: " + e.getType() + " enemy to game");
    }

    public ArrayList<Enemy> findEnemyByType(String query) {
        ArrayList<Enemy> result = new ArrayList<>();
        String lowerQuery = query.toLowerCase();

        for (Enemy e : enemies) {
            if (e.getType().toLowerCase().contains(lowerQuery)) {
                result.add(e);
            }
        }

        return result;
    }

    public ArrayList<Enemy> collidingWithPlayer() {
        ArrayList<Enemy> result = new ArrayList<>();

        for (Enemy e : enemies) {
            if (checkCollision(player, e)) {
                result.add(e);
            }
        }

        return result;
    }

    public void resolveCollisions() {
        ArrayList<Enemy> colliding = collidingWithPlayer();

        for (Enemy e : colliding) {
            decreaseHealth(player, e);
        }
    }

    public static Enemy parseEnemy(String line) {
        try {
            String[] parts = line.split(";");

            if (parts.length < 5) {
                throw new IllegalArgumentException("Neispravan format: nedovoljno djelova");
            }

            String type = parts[0].trim();

            String[] posParts = parts[1].split(",");
            if (posParts.length != 2) {
                throw new IllegalArgumentException("Neispravan format pozicije");
            }
            double x = Double.parseDouble(posParts[0].trim());
            double y = Double.parseDouble(posParts[1].trim());

            String sizePart = parts[2].trim();
            ICollidable collider;

            if (sizePart.contains("x")) {
                String[] dimensions = sizePart.split("x");
                if (dimensions.length != 2) {
                    throw new IllegalArgumentException("Neispravan format dimenzija");
                }
                double width = Double.parseDouble(dimensions[0].trim());
                double height = Double.parseDouble(dimensions[1].trim());
                collider = new RectangleCollider(x, y, width, height);
            } else if (sizePart.startsWith("R=")) {
                double radius = Double.parseDouble(sizePart.substring(2).trim());
                collider = new CircleCollider(x, y, radius);
            } else {
                throw new IllegalArgumentException("Neispravan format collidera");
            }

            int damage = Integer.parseInt(parts[3].trim());

            boolean isBoss = false;
            int health = 50;

            if (parts.length >= 5) {
                String lastPart = parts[4].trim();
                if (lastPart.equalsIgnoreCase("boss")) {
                    isBoss = true;
                } else {
                    try {
                        health = Integer.parseInt(lastPart);
                    } catch (NumberFormatException nfe) {
                        if (lastPart.equalsIgnoreCase("boss")) {
                            isBoss = true;
                        }
                    }
                }
            }

            if (parts.length >= 6) {
                try {
                    health = Integer.parseInt(parts[5].trim());
                } catch (NumberFormatException _) {
                }
            }

            if (isBoss) {
                return new BossEnemy(x, y, collider, type, damage, health);
            } else {
                return new MeleeEnemy(x, y, collider, type, damage, health);
            }

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Neispravan format broja: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Neispravan format stringa: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== GAME TEST ===\n");

        RectangleCollider playerCollider = new RectangleCollider(10, 5, 32, 32);
        Player player = new Player(10, 5, playerCollider, " peTar petrović ", 85);

        Game game = new Game(player);

        System.out.println("Igrač kreiran:");
        System.out.println(player);
        System.out.println();

        RectangleCollider goblinCollider = new RectangleCollider(12, 5, 16, 16);
        MeleeEnemy goblin = new MeleeEnemy(12, 5, goblinCollider, "Goblin", 20, 60);
        game.addEnemy(goblin);

        Enemy parsedEnemy = Game.parseEnemy("Goblin King;10,10;32x32;25;boss;100");
        game.addEnemy(parsedEnemy);

        CircleCollider slimeCollider = new CircleCollider(50, 50, 10);
        MeleeEnemy slime = new MeleeEnemy(50, 50, slimeCollider, "Slime", 15, 40);
        game.addEnemy(slime);

        System.out.println("Svi neprijatelji:");
        for (Enemy e : game.getEnemies()) {
            System.out.println(e);
        }
        System.out.println();

        System.out.println("Pretraga 'gob':");
        ArrayList<Enemy> goblinEnemies = game.findEnemyByType("gob");
        for (Enemy e : goblinEnemies) {
            System.out.println("  " + e.getDisplayName());
        }
        System.out.println();

        System.out.println("Neprijatelji u koliziji sa igracem:");
        ArrayList<Enemy> colliding = game.collidingWithPlayer();
        for (Enemy e : colliding) {
            System.out.println("  " + e);
        }
        System.out.println();

        System.out.println("Stanje igraca PRIJE resolveCollisions:");
        System.out.println(player);
        System.out.println();

        game.resolveCollisions();

        System.out.println("Stanje igraca POSLIJE resolveCollisions:");
        System.out.println(player);
        System.out.println();

        System.out.println("=== Event Log ===");
        for (String log : game.getEventLog()) {
            System.out.println(log);
        }
        System.out.println();

        System.out.println("=== TEST ZAVRSEN ===");
    }
}
