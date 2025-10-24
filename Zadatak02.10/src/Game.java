/**
 * Glavna klasa programa za testiranje sistema sudara izmedju igraca i neprijatelja
 *
 * Program kreira jednog igraca i dva neprijatelja, zatim testira detekciju sudara
 * i primjenjuje stetu na igraca kada dodje do sudara.
 *
 * Autori: Andrej Janovic 24/009, Petar Simonovic 24/076
 */
public class Game {
    public static void main(String[] args) {
        // Kreiranje igraca na poziciji (50, 50) sa dimenzijama 30x30 i punim zdravljem
        Player player = new Player(50, 50, 30, 30, 100);

        // Kreiranje prvog neprijatelja na poziciji (80, 80) sa dimenzijama 25x25 i damage-om 20
        Enemy enemy1 = new Enemy(80, 80, 25, 25, 20);

        // Kreiranje drugog neprijatelja na poziciji (200, 200) sa dimenzijama 25x25 i damage-om 35
        Enemy enemy2 = new Enemy(200, 200, 25, 25, 35);

        System.out.println("=== Inicijalno stanje ===");
        System.out.println("Player health: " + player.getHealth());
        System.out.println("Player pozicija: (" + player.getX() + ", " + player.getY() + ")");
        System.out.println("Enemy1 pozicija: (" + enemy1.getX() + ", " + enemy1.getY() + "), damage: " + enemy1.getDamage());
        System.out.println("Enemy2 pozicija: (" + enemy2.getX() + ", " + enemy2.getY() + "), damage: " + enemy2.getDamage());

        // Test sudara sa prvim neprijateljem
        System.out.println("\n=== Test sudara sa Enemy1 ===");
        if (GameUtils.checkCollision(player, enemy1)) {
            System.out.println("Sudar detektovan!");
            GameUtils.decreaseHealth(player, enemy1);
            System.out.println("Player health nakon sudara: " + player.getHealth());
        } else {
            System.out.println("Nema sudara sa Enemy1");
        }

        // Test sudara sa drugim neprijateljem
        System.out.println("\n=== Test sudara sa Enemy2 ===");
        if (GameUtils.checkCollision(player, enemy2)) {
            System.out.println("Sudar detektovan!");
            GameUtils.decreaseHealth(player, enemy2);
            System.out.println("Player health nakon sudara: " + player.getHealth());
        } else {
            System.out.println("Nema sudara sa Enemy2");
        }

        // Ispis finalnog stanja
        System.out.println("\n=== Finalno stanje ===");
        System.out.println("Player health: " + player.getHealth());
    }
}
