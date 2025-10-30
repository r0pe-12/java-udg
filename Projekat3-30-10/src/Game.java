import java.util.ArrayList;

// Petar Simonovic 24/076 FIST
// Vasilije Scepanovic 24/036 FIST
// github repo link: https://github.com/r0pe-12/java-udg/tree/main/Projekat3-30-10
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
        return p.intersects(e);
    }

    public void decreaseHealth(Player p, Enemy e) {
        int oldHealth = p.getHp();
        int newHealth = Math.max(0, oldHealth - e.getDamage());
        p.setHp(newHealth);

        String displayType = e.getType().substring(0, 1).toUpperCase() + e.getType().substring(1);
        String logMessage = String.format("HIT: Player by %s for %d -> HP %d -> %d",
                displayType, e.getDamage(), oldHealth, newHealth);
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

    public static void main() {
        // Kreiranje playera i igre
        Player player = new Player(10, 10, 40, 60, "marko MARKOVIC", 100);
        Game game = new Game(player);

        System.out.println("=== INICIJALNO STANJE ===");
        System.out.println(player);
        System.out.println();

        // Dodavanje enemy rucno
        Enemy enemy1 = new BossEnemy(15, 15, 30, 30, "zombie", 25, 50);
        game.addEnemy(enemy1);

        // Dodavanje enemy iz stringa
        String enemyData = "Vasilije;12,5;16x16;20;50";

        Enemy enemy2 = new Enemy(enemyData);
        game.addEnemy(enemy2);

        // Ispisati sve enemies
        System.out.println("=== SVI NEPRIJATELJI ===");
        for (Enemy e : game.getEnemies()) {
            System.out.println(e);
        }
        System.out.println();

        // Trazimo enemies koji sadrze "gob"
        System.out.println("=== PRETRAGA: Neprijatelji sa 'vas' ===");
        ArrayList<Enemy> foundEnemies = game.findByType("vas");
        if (foundEnemies.isEmpty()) {
            System.out.println("Nijedan neprijatelj nije pronađen.");
        } else {
            for (Enemy e : foundEnemies) {
                System.out.println(e);
            }
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