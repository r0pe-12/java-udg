import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// Petar Simonovic 24/076 FIST
// Vasilije Scepanovic 24/036 FIST
// github repo link: https://github.com/r0pe-12/project2-23.10
public class Game {
    private final Player player;
    private final ArrayList<Enemy> enemies;
    private final ArrayList<String> eventLog;

    public Game(Player player) {
        this.player = player;
        this.enemies = new ArrayList<>();
        this.eventLog = new ArrayList<>();
    }

    public boolean checkCollision(Player p, Enemy e) {
        // Koristi novu intersects metodu iz GameObject klase
        return p.intersects(e);
    }

    public void decreaseHealth(Player p, Enemy e) {
        int oldHealth = p.getHealth();
        int effectiveDamage = e.getEffectiveDamage();
        int newHealth = Math.max(0, oldHealth - effectiveDamage);
        p.setHealth(newHealth);

        String displayType = e.getType();
        String logMessage = String.format("HIT: Player by %s for %d -> HP %d -> %d",
                displayType, effectiveDamage, oldHealth, newHealth);
        eventLog.add(logMessage);
    }

    public void addEnemy(Enemy e) {
        enemies.add(e);
        String displayType = e.getType().substring(0, 1).toUpperCase() + e.getType().substring(1);
        String logMessage = String.format("ADDED: Enemy %s at position (%d, %d)",
                displayType, e.getX(), e.getY());
        eventLog.add(logMessage);
    }

    public ArrayList<Enemy> findByType(String query) {
        ArrayList<Enemy> result = new ArrayList<>();
        String queryLower = query.toLowerCase().trim();

        for (Enemy e : enemies) {
            if (e.getType().toLowerCase().contains(queryLower)) {
                result.add(e);
            }
        }

        return result;
    }

    public ArrayList<Enemy> collidingWithPlayer() {
        ArrayList<Enemy> colliding = new ArrayList<>();

        for (Enemy e : enemies) {
            if (checkCollision(player, e)) {
                colliding.add(e);
            }
        }

        return colliding;
    }

    public void resolveCollisions() {
        ArrayList<Enemy> collidingEnemies = collidingWithPlayer();

        for (Enemy e : collidingEnemies) {
            decreaseHealth(player, e);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<String> getEventLog() {
        return eventLog;
    }

    public void printEventLog() {
        System.out.println("=== EVENT LOG ===");
        for (String event : eventLog) {
            System.out.println(event);
        }
    }

    /**
     * Učitava neprijatelje iz CSV fajla
     * Format CSV: type,class,damage,health,x,y,shape,width,height,radius
     * class: "melee" ili "boss"
     * shape: "rectangle" ili "circle"
     */
    public static ArrayList<Enemy> loadEnemiesFromCSV(String filePath) throws IOException {
        ArrayList<Enemy> loadedEnemies = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                // Preskačemo header
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // Parsiranje linije - split sa -1 da zadrži prazne stringove
                String[] parts = line.split(",", -1);
                if (parts.length < 10) {
                    System.out.println("Neispravan format CSV linije: " + line);
                    throw new IllegalArgumentException("Neispravan format CSV linije: " + line);
                }

                try {
                    String type = parts[0].trim();
                    String enemyClass = parts[1].trim().toLowerCase();
                    int damage = Integer.parseInt(parts[2].trim());
                    int health = Integer.parseInt(parts[3].trim());
                    int x = Integer.parseInt(parts[4].trim());
                    int y = Integer.parseInt(parts[5].trim());
                    String shape = parts[6].trim().toLowerCase();

                    // Kreiranje kolajdera na osnovu shape
                    Collidable collider;
                    if (shape.equals("rectangle")) {
                        int width = Integer.parseInt(parts[7].trim());
                        int height = Integer.parseInt(parts[8].trim());
                        collider = new RectangleCollider(x, y, width, height);
                    } else if (shape.equals("circle")) {
                        // Za krug radius je u koloni 9
                        int radius = Integer.parseInt(parts[9].trim());
                        // Za krug, centar je na poziciji (x, y)
                        collider = new CircleCollider(x, y, radius);
                    } else {
                        throw new IllegalArgumentException("Nepoznat oblik kolajdera: " + shape);
                    }

                    // Kreiranje odgovarajućeg tipa neprijatelja
                    Enemy enemy;
                    if (enemyClass.equals("melee")) {
                        enemy = new MeleeEnemy(type, x, y, collider, damage, health);
                    } else if (enemyClass.equals("boss")) {
                        enemy = new BossEnemy(type, x, y, collider, damage, health);
                    } else {
                        throw new IllegalArgumentException("Nepoznata klasa neprijatelja: " + enemyClass);
                    }

                    loadedEnemies.add(enemy);

                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Greška pri parsiranju brojeva u liniji: " + line, e);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Greška pri kreiranju neprijatelja iz linije: " + line, e);
                }
            }
        }

        return loadedEnemies;
    }

    public static void main() {
        GUI gui = new GUI();
        gui.setVisible(true);
        if (false) {
            // Kreiranje playera sa pravougaonim kolajderom na poziciji (10, 10)
            RectangleCollider playerCollider = new RectangleCollider(10, 10, 40, 60);
            Player player = new Player("marko MARKOVIC", 10, 10, playerCollider, 100);
            Game game = new Game(player);

            System.out.println("=== INICIJALNO STANJE ===");
            System.out.println(player);
            System.out.println();

            // Ispisati sve enemies
            System.out.println("=== SVI NEPRIJATELJI ===");
            for (Enemy e : game.getEnemies()) {
                System.out.println(e);
            }
            System.out.println();

            // Provjeriti kolizije i ispisati stanje prije
            System.out.println("=== STANJE PRIJE KOLIZIJA ===");
            System.out.println(player);
            System.out.println();

            System.out.println("=== PROVJERA KOLIZIJA ===");
            ArrayList<Enemy> collidingEnemies = game.collidingWithPlayer();
            if (collidingEnemies.isEmpty()) {
                System.out.println("Nema kolizija sa igračem.");
            } else {
                System.out.println("Igrac se sudara sa " + collidingEnemies.size() + " neprijatelja:");
                for (Enemy e : collidingEnemies) {
                    System.out.println("  - " + e);
                }
            }
            System.out.println();

            game.resolveCollisions();

            System.out.println("=== STANJE POSLIJE KOLIZIJA ===");
            System.out.println(player);
            System.out.println();

            game.printEventLog();
        }
    }
}