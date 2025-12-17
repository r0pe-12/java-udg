import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener {
    private final JComboBox<Radnik> radnikJComboBox;
    private final JTextField klijentJTextField;
    private final JTextField lokacijaJTextField;
    private final JComboBox<String> vrstaUslugeJComboBox;
    private final JComboBox<Object> obracunJComboBox;
    private final JTextField kolicinaJTextField;
    private final JComboBox<Posao> posaoJComboBox;
    private final JPanel vrijednostPanel;
    private final JTextField vrijednostJTextField;
    private final JTextField vrijednostPoslaJTextField;
    private final JButton dodajUsluguButton;
    private final JButton obrisiButton;
    private final JButton obracunajCijenuButton;
    private int currentId = 0;
    private ArrayList<Posao> poslovi;

    public GUI(ArrayList<Radnik> radnici, ArrayList<Posao> poslovi) {
        super("Baustela DOO");
        currentId = poslovi.getLast().getId();
        this.poslovi = poslovi;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new GridLayout(9, 2, 5, 5));

        add(new JLabel("Radnik"));
        radnikJComboBox = new JComboBox<>(radnici.toArray(new Radnik[0]));
        add(radnikJComboBox);

        add(new JLabel("Usluga"));
        vrstaUslugeJComboBox = new JComboBox<>(new String[]{"Keramicarska Usluga", "Vodoinstalaterska Usluga"});
        vrstaUslugeJComboBox.addActionListener(this);
        add(vrstaUslugeJComboBox);

        add(new JLabel("Obracun"));
        obracunJComboBox = new JComboBox<>();
        add(obracunJComboBox);

        add(new JLabel("Kolicina"));
        kolicinaJTextField = new JTextField();
        add(kolicinaJTextField);

        add(new JLabel("Posao"));
        posaoJComboBox = new JComboBox<>(poslovi.toArray(new Posao[0]));
        posaoJComboBox.addActionListener(this);
        add(posaoJComboBox);

        add(new JLabel("Klijent"));
        klijentJTextField = new JTextField();
        add(klijentJTextField);

        add(new JLabel("Lokacija"));
        lokacijaJTextField = new JTextField();
        add(lokacijaJTextField);

        vrijednostPanel = new JPanel();
        vrijednostPanel.setLayout(new GridLayout(1, 2, 10, 0));

        add(new JLabel("Vrijednost: U/P"));
        vrijednostJTextField = new JTextField();
        vrijednostJTextField.setEditable(false);
        vrijednostPanel.add(vrijednostJTextField);

        vrijednostPoslaJTextField = new JTextField();
        vrijednostPoslaJTextField.setEditable(false);
        vrijednostPanel.add(vrijednostPoslaJTextField);
        add(vrijednostPanel);


        add(new JLabel("Akcije"));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 0));

        dodajUsluguButton = new JButton("Dodaj Uslugu");
        dodajUsluguButton.addActionListener(this);
        buttonPanel.add(dodajUsluguButton);

        obrisiButton = new JButton("Obrisi");
        obrisiButton.addActionListener(this);
        buttonPanel.add(obrisiButton);

        obracunajCijenuButton = new JButton("Obracunaj");
        obracunajCijenuButton.addActionListener(this);
        buttonPanel.add(obracunajCijenuButton);
        add(buttonPanel);

        refreshObracunOptions();

//        JRadioButton rbA = new JRadioButton("Opcija A", true);
//        JRadioButton rbB = new JRadioButton("Opcija B");
//
//        ButtonGroup g = new ButtonGroup();
//        g.add(rbA);
//        g.add(rbB);
//
//        JButton btn = new JButton("OK");
//        btn.addActionListener(e -> {
//            if (rbA.isSelected()) {
//                System.out.println("Izabrana A");
//            } else {
//                System.out.println("Izabrana B");
//            }
//        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vrstaUslugeJComboBox) {
            refreshObracunOptions();
        } else if (e.getSource() == posaoJComboBox) {
            Posao posao = (Posao) posaoJComboBox.getSelectedItem();
            vrijednostPoslaJTextField.setText(String.valueOf(posao.getCijena()));
        } else if (e.getSource() == dodajUsluguButton || e.getSource() == obracunajCijenuButton) {

            try {
                boolean saveMode = e.getSource() == dodajUsluguButton;
                Posao posao = (Posao) posaoJComboBox.getSelectedItem();

                ArrayList<Usluga> usluge = new ArrayList<>();
                Radnik radnik = (Radnik) radnikJComboBox.getSelectedItem();
                String vrstaUsluge = (String) vrstaUslugeJComboBox.getSelectedItem();
                Object obracun = obracunJComboBox.getSelectedItem();
                double kolicina = Double.parseDouble(kolicinaJTextField.getText());

                String klijent = klijentJTextField.getText();
                String lokacija = lokacijaJTextField.getText();
                Usluga usluga;
                assert vrstaUsluge != null;
                if (vrstaUsluge.equals("Keramicarska Usluga")) {
                    usluga = new KeramicarskaUsluga(radnik, LocalDateTime.now(), kolicina, 0, lokacija, klijent, (KeramicarskaUsluga.TipUsluge) obracun);
                } else {
                    usluga = new VodoinstalaterskaUsluga(radnik, LocalDateTime.now(), kolicina, 0, lokacija, klijent, (VodoinstalaterskaUsluga.ObracunTip) obracun);
                }

                vrijednostJTextField.setText(usluga.getVrijednost() + "");


                if (saveMode) {
                    usluge.add(usluga);
                    assert posao != null;
                    if (posao.getId() == 0) {
                        Posao novi_posao = new Posao(usluge, ++currentId);
                        poslovi.add(novi_posao);
                        posaoJComboBox.addItem(novi_posao);
                    } else {
                        ArrayList<Usluga> postojeceUsluge = posao.getUsluge();
                        postojeceUsluge.addAll(usluge);
                        posao.setUsluge(postojeceUsluge);
                    }
                    resetForm();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Unesite ispravnu kolicinu.", "Greska", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == obrisiButton) {
            vrijednostPanel.removeAll();
            vrijednostPanel.setLayout(new GridLayout());
            vrijednostPanel.add(new JLabel("test"));
            vrijednostPanel.revalidate();
            vrijednostPanel.repaint();
            resetForm();
        }
    }

    private void refreshObracunOptions() {
        obracunJComboBox.removeAllItems();

        String selectedUsluga = (String) vrstaUslugeJComboBox.getSelectedItem();

        if ("Keramicarska Usluga".equals(selectedUsluga)) {
            for (KeramicarskaUsluga.TipUsluge tip : KeramicarskaUsluga.TipUsluge.values()) {
                obracunJComboBox.addItem(tip); // ubacuješ enum, ne string
            }
        } else {
            for (VodoinstalaterskaUsluga.ObracunTip tip : VodoinstalaterskaUsluga.ObracunTip.values()) {
                obracunJComboBox.addItem(tip); // ubacuješ enum
            }
        }
    }

    private void resetForm() {
        radnikJComboBox.setSelectedIndex(0);
        vrstaUslugeJComboBox.setSelectedIndex(0);
        obracunJComboBox.setSelectedIndex(0);
        kolicinaJTextField.setText("");
        klijentJTextField.setText("");
        lokacijaJTextField.setText("");
        vrijednostJTextField.setText("");
        vrijednostPoslaJTextField.setText("");

        refreshObracunOptions();

        revalidate();
        repaint();
    }


}
