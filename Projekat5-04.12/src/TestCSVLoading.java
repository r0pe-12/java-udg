import java.io.IOException;
import java.util.ArrayList;

public class TestCSVLoading {
    public static void main(String[] args) {
        System.out.println("=== TEST CSV UČITAVANJA ===\n");

        try {
            // Učitavanje neprijatelja iz CSV fajla
            String csvPath = "src/enemies.csv";
            ArrayList<Enemy> enemies = Game.loadEnemiesFromCSV(csvPath);

            System.out.println("Uspješno učitano " + enemies.size() + " neprijatelja!\n");

            // Prikaz svih učitanih neprijatelja
            System.out.println("=== SVI NEPRIJATELJI ===");
            for (Enemy enemy : enemies) {
                System.out.println(enemy);
            }
            System.out.println();

            // Kreiranje igrača sa pravougaonim kolajderom
            RectangleCollider playerCollider = new RectangleCollider(12, 5, 32, 32);
            Player player = new Player("Harry Potter", 12, 5, playerCollider, 90);

            System.out.println("=== IGRAČ ===");
            System.out.println(player);
            System.out.println();

            // Kreiranje igre
            Game game = new Game(player);

            // Dodavanje svih neprijatelja u igru
            for (Enemy enemy : enemies) {
                game.addEnemy(enemy);
            }

            // Provjera kolizija
            System.out.println("=== PROVJERA KOLIZIJA ===");
            ArrayList<Enemy> collidingEnemies = game.collidingWithPlayer();
            if (collidingEnemies.isEmpty()) {
                System.out.println("Nema kolizija sa igračem.");
            } else {
                System.out.println("Igrač se sudara sa " + collidingEnemies.size() + " neprijatelja:");
                for (Enemy e : collidingEnemies) {
                    System.out.println("  - " + e.getType() + " (Damage: " + e.getEffectiveDamage() + ")");
                }
            }
            System.out.println();

            // Resolve kolizija
            System.out.println("=== PRIJE KOLIZIJA ===");
            System.out.println("Player Health: " + player.getHealth());
            System.out.println();

            game.resolveCollisions();

            System.out.println("=== POSLIJE KOLIZIJA ===");
            System.out.println("Player Health: " + player.getHealth());
            System.out.println();

            // Event log
            game.printEventLog();

        } catch (IOException e) {
            System.err.println("Greška pri čitanju CSV fajla: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Greška pri parsiranju podataka: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
