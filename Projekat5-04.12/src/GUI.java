import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

class GUI extends JFrame implements ActionListener {
    private JTextField name_field;
    private JTextField health_field;
    private JTextField x_position_field;
    private JTextField y_position_field;
    private JComboBox<String> collider_field;
    private JButton start_game;
    private JTextArea log;

    public GUI() {
        super("Game Setup");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 700);
        setLayout(new GridLayout(6, 2, 5, 5));
        JLabel name_label = new JLabel("Ime");
        add(name_label);
        name_field = new JTextField();
        add(name_field);

        JLabel health_label = new JLabel("Health (0 - 100)");
        add(health_label);
        health_field = new JTextField();
        add(health_field);

        JLabel x_position_label = new JLabel("X position");
        add(x_position_label);
        x_position_field = new JTextField();
        add(x_position_field);

        JLabel y_position_label = new JLabel("Y position");
        add(y_position_label);
        y_position_field = new JTextField();
        add(y_position_field);

        JLabel collider_label = new JLabel("Kolajder");
        add(collider_label);

        String[] kolajderi = {"rectangle", "circle"};
        collider_field = new JComboBox<>(kolajderi);
        add(collider_field);

        start_game = new JButton("Pokreni Igru");
        start_game.addActionListener(this);
        add(start_game);

        log = new JTextArea();
        log.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(log);
        add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start_game) {
            try {
                // Očisti rezultate
                log.setText("");

                // Učitaj podatke iz polja
                String name = name_field.getText().trim();
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Ime ne smije biti prazno");
                }

                int health = Integer.parseInt(health_field.getText().trim());
                int x = Integer.parseInt(x_position_field.getText().trim());
                int y = Integer.parseInt(y_position_field.getText().trim());

                // Kreiranje kolajdera
                Collidable collider;
                String colliderType = (String) collider_field.getSelectedItem();
                if (colliderType.equals("rectangle")) {
                    // Fiksne dimenzije 32x32
                    collider = new RectangleCollider(x, y, 32, 32);
                } else {
                    // Fiksni radius 16
                    collider = new CircleCollider(x, y, 16);
                }

                // Kreiranje igrača
                Player player = new Player(name, x, y, collider, health);

                // Kreiranje igre
                Game game = new Game(player);

                // Učitavanje neprijatelja iz CSV fajla
                String csvPath = "src/enemies.csv";
                ArrayList<Enemy> enemies = Game.loadEnemiesFromCSV(csvPath);

                // Dodavanje neprijatelja u igru
                for (Enemy enemy : enemies) {
                    game.addEnemy(enemy);
                }

                // Prikaz početnog stanja
                StringBuilder output = new StringBuilder();
                output.append("=== POČETNO STANJE ===\n");
                output.append(player.toString()).append("\n\n");

                output.append("=== NEPRIJATELJI ===\n");
                for (Enemy enemy : game.getEnemies()) {
                    output.append(enemy.toString()).append("\n");
                }
                output.append("\n");

                // Provjera kolizija
                ArrayList<Enemy> collidingEnemies = game.collidingWithPlayer();
                output.append("=== KOLIZIJE ===\n");
                if (collidingEnemies.isEmpty()) {
                    output.append("Nema kolizija sa igračem.\n\n");
                } else {
                    output.append("Igrač se sudara sa ").append(collidingEnemies.size()).append(" neprijatelja:\n");
                    for (Enemy enemy : collidingEnemies) {
                        output.append("  - ").append(enemy.getType())
                                .append(" (Effective Damage: ").append(enemy.getEffectiveDamage()).append(")\n");
                    }
                    output.append("\n");
                }

                // Resolve kolizija
                game.resolveCollisions();

                output.append("=== STANJE POSLIJE KOLIZIJA ===\n");
                output.append(player.toString()).append("\n\n");

                // Event log
                output.append("=== EVENT LOG ===\n");
                for (String event : game.getEventLog()) {
                    output.append(event).append("\n");
                }

                // Prikaz rezultata
                log.setText(output.toString());

                // Provjera da li je igrač poražen ili pobijedio
                if (player.getHealth() == 0) {
                    JOptionPane.showMessageDialog(this,
                            "Igrač je poražen! Enemies su ga uništili.",
                            "Poraz",
                            JOptionPane.ERROR_MESSAGE);
                } else if (collidingEnemies.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Igrač je pobijedio! Nema neprijatelja u koliziji.",
                            "Pobjeda",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Igrač je preživio! Health: " + player.getHealth() + "/100",
                            "Preživio",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Greška: Unesite ispravne brojeve za health i poziciju!",
                        "Greška unosa",
                        JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this,
                        "Greška: " + ex.getMessage(),
                        "Greška",
                        JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Greška pri učitavanju CSV fajla: " + ex.getMessage(),
                        "Greška",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}