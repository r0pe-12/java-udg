import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener {
    private JTextField Fbroj_tiketa;
    private JTextField Ftablice;
    private JTextField FdateTime;

    private ButtonGroup tipVozilaButtons;
    private JRadioButton Rautomobil;
    private JRadioButton Rkombi;
    private JRadioButton Rautobus;

    private JButton Bunesi;
    private JButton Bobrisi;
    private JButton BobrisiPolja;

    public GUI() {
        super("Parking Servis");
        this.setSize(800, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3, 1));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 3, 10, 5));

        infoPanel.add(new JLabel("Broj tiketa:"));
        infoPanel.add(new JLabel("Broj tablica:"));
        infoPanel.add(new JLabel("Datum i vrijeme:"));

        Fbroj_tiketa = new JTextField("");
        Fbroj_tiketa.setEditable(false);
        infoPanel.add(Fbroj_tiketa);

        Ftablice = new JTextField("");
        Ftablice.addActionListener(this);
        infoPanel.add(Ftablice);

        FdateTime = new JTextField("");
        FdateTime.setEditable(false);
        infoPanel.add(FdateTime);

        add(infoPanel);

        JPanel footer = new JPanel();
        footer.setLayout(new GridLayout(1, 2, 10, 15));

        JPanel tipVozila = new JPanel();
        tipVozila.setLayout(new GridLayout(4, 1, 5, 5));
        tipVozila.add(new JLabel("Tip vozila:"));

        tipVozilaButtons = new ButtonGroup();
        Rautomobil = new JRadioButton("Automobil");
        Rautomobil.setSelected(true);
        tipVozilaButtons.add(Rautomobil);
        tipVozila.add(Rautomobil);

        Rkombi = new JRadioButton("Kombi");
        tipVozilaButtons.add(Rkombi);
        tipVozila.add(Rkombi);

        Rautobus = new JRadioButton("Autobus");
        tipVozilaButtons.add(Rautobus);
        tipVozila.add(Rautobus);

        footer.add(tipVozila);

        JPanel opcije = new JPanel();
        opcije.setLayout(new GridLayout(3, 1, 5, 5));
        Bunesi = new JButton("Unesi");
        Bunesi.addActionListener(this);
        opcije.add(Bunesi);
        Bobrisi = new JButton("Obrisi ulaz");
        Bobrisi.addActionListener(this);
        opcije.add(Bobrisi);
        BobrisiPolja = new JButton("Ocisti polja");
        BobrisiPolja.addActionListener(this);
        opcije.add(BobrisiPolja);

        footer.add(opcije);

        add(footer);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Bunesi) {
            try {
                String tablice = Ftablice.getText();
                ArrayList<Tiket> tiketi = Tiket.pretraziPoTablici(tablice);
                if (tiketi.size() == 1) {
                    throw new Exception("Vozilo Postoji");
                }
                LocalDateTime dateTime = LocalDateTime.now();
                TipVozila tip = null;
                if (Rautomobil.isSelected()) {
                    tip = TipVozila.AUTOMOBIL;
                } else if (Rkombi.isSelected()) {
                    tip = TipVozila.KOMBI;
                } else if (Rautobus.isSelected()) {
                    tip = TipVozila.AUTOBUS;
                }

                new Tiket(tablice, dateTime, tip);
                obrisiPolja();
            } catch (Exception ex) {
                if (ex.getMessage().equals("Vozilo Postoji")) {
                    JOptionPane.showMessageDialog(this, "Vozilo Postoji", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Neispravan unos.", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else if (e.getSource() == BobrisiPolja) {
            obrisiPolja();
        } else if (e.getSource() == Bobrisi) {
            try {
                ArrayList<Tiket> tiketi = Tiket.pretraziPoTablici(Ftablice.getText());
                if (tiketi.size() == 1) {
                    Tiket tiket = tiketi.getFirst();
                    Tiket.removeTiket(tiket);
                    obrisiPolja();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Neispravan unos.", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == Ftablice) {
            ArrayList<Tiket> tiketi = Tiket.pretraziPoTablici(Ftablice.getText());

            if (tiketi.size() == 1) {
                Tiket tiket = tiketi.getFirst();
                Fbroj_tiketa.setText(String.valueOf(tiket.getBroj_tiketa()));
                Ftablice.setText(tiket.getTablice());
                FdateTime.setText(tiket.getDateTime().toString());

                if (tiket.getTipVozila() == TipVozila.AUTOMOBIL) {
                    Rautomobil.setSelected(true);
                } else if (tiket.getTipVozila() == TipVozila.AUTOBUS) {
                    Rautobus.setSelected(true);
                } else if (tiket.getTipVozila() == TipVozila.KOMBI) {
                    Rkombi.setSelected(true);
                }

            }

        }
    }

    private void obrisiPolja() {
        Fbroj_tiketa.setText("");
        Ftablice.setText("");
        FdateTime.setText("");
        Rautomobil.setSelected(true);
        Rkombi.setSelected(false);
        Rautobus.setSelected(false);
    }
}
