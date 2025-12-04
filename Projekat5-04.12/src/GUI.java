import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GUI extends JFrame implements ActionListener {
    public GUI() {
        super("Game Setup");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 700);
        setLayout(new GridLayout(6, 2, 5, 5));
        JLabel name_label = new JLabel("Ime");
        add(name_label);
        JTextField name_field = new JTextField();
        add(name_field);

        JLabel health_label = new JLabel("Health (0 - 100)");
        add(health_label);
        JTextField health_field = new JTextField();
        add(health_field);

        JLabel x_position_label = new JLabel("X position");
        add(x_position_label);
        JTextField x_position_field = new JTextField();
        add(x_position_field);

        JLabel y_position_label = new JLabel("Y position");
        add(y_position_label);
        JTextField y_position_field = new JTextField();
        add(y_position_field);

        JLabel collider_label = new JLabel("Kolajder");
        add(collider_label);

        String[] kolajderi = {"rectangle", "circle"};
        JComboBox<String> collider_field = new JComboBox<>(kolajderi);
        add(collider_field);

        JButton start_game = new JButton("Pokreni Igru");
        start_game.addActionListener(this);
        add(start_game);

        JTextField log = new JTextField();
        add(log);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}