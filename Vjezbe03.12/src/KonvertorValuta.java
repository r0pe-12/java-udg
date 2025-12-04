import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.io.File;
import java.io.FileReader;

public class KonvertorValuta extends JFrame {

    private JLabel eurLabela, usdLabela, gbpLabela;
    private JTextField eurPolje, usdPolje, gbpPolje;
    private boolean azuriramPolja = true;

    // Kursevi valuta (u odnosu na EUR)
    private double EUR_TO_USD;
    private double EUR_TO_GBP;
    private double USD_TO_GBP;

    private void ucitajKurseve() {
        try {
            // Ispis trenutnog direktorijuma za debugging
            String trenutniDir = System.getProperty("user.dir");
            System.out.println("Trenutni direktorijum: " + trenutniDir);

            File fajl = new File("src/kursevi.json");
            System.out.println("Puna putanja fajla: " + fajl.getAbsolutePath());
            System.out.println("Da li fajl postoji: " + fajl.exists());

            if (!fajl.exists()) {
                throw new Exception("Fajl kursevi.json ne postoji na putanji: " + fajl.getAbsolutePath());
            }

            FileReader reader = new FileReader(fajl);
            StringBuilder sb = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                sb.append((char) i);
            }
            reader.close();

            String json = sb.toString();
            System.out.println("Učitan JSON: " + json);

            // Ručno parsiranje - jednostavno za ovaj JSON
            EUR_TO_USD = parseValue(json, "EUR_USD");
            EUR_TO_GBP = parseValue(json, "EUR_GBP");
            USD_TO_GBP = parseValue(json, "USD_GBP");

            System.out.println("Učitani kursevi:");
            System.out.println("EUR_TO_USD: " + EUR_TO_USD);
            System.out.println("EUR_TO_GBP: " + EUR_TO_GBP);
            System.out.println("USD_TO_GBP: " + USD_TO_GBP);

        } catch (Exception e) {
            System.err.println("Greška: " + e.getMessage());
            e.printStackTrace();

            JOptionPane.showMessageDialog(this,
                    "Greška pri učitavanju kurseva iz fajla!\n" + e.getMessage(),
                    "Greška",
                    JOptionPane.ERROR_MESSAGE);

            // Podrazumijevani kursevi ako fajl ne postoji
            EUR_TO_USD = 1.10;
            EUR_TO_GBP = 0.85;
            USD_TO_GBP = 0.77;
        }
    }

    private double parseValue(String json, String key) {
        int start = json.indexOf("\"" + key + "\":") + key.length() + 3;
        int end = json.indexOf(",", start);
        if (end == -1) end = json.indexOf("}", start);
        return Double.parseDouble(json.substring(start, end).trim());
    }

    public KonvertorValuta() {
        super("Konvertor Valuta");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        ucitajKurseve();

        // Kreiranje komponenti za EUR
        eurLabela = new JLabel("EUR:");
        add(eurLabela);
        eurPolje = new JTextField();
        add(eurPolje);

        // Kreiranje komponenti za USD
        usdLabela = new JLabel("USD:");
        add(usdLabela);
        usdPolje = new JTextField();
        add(usdPolje);

        // Kreiranje komponenti za GBP
        gbpLabela = new JLabel("GBP:");
        add(gbpLabela);
        gbpPolje = new JTextField();
        add(gbpPolje);

        // Dodavanje osluškivača za EUR polje
        eurPolje.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {
                azurirajIzEUR();
            }
        });

        // Dodavanje KeyListener-a za USD polje
        usdPolje.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {
                azurirajIzUSD();
            }
        });

        // Dodavanje KeyListener-a za GBP polje
        gbpPolje.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {
                azurirajIzGBP();
            }
        });
    }

    private void azurirajIzEUR() {
        if (!azuriramPolja) return;

        try {
            String tekst = eurPolje.getText();
            if (tekst.isEmpty()) {
                azuriramPolja = false;
                usdPolje.setText("");
                gbpPolje.setText("");
                azuriramPolja = true;
                return;
            }

            double eur = Double.parseDouble(tekst);
            double usd = eur * EUR_TO_USD;
            double gbp = eur * EUR_TO_GBP;

            azuriramPolja = false;
            usdPolje.setText(String.format("%.2f", usd));
            gbpPolje.setText(String.format("%.2f", gbp));
            azuriramPolja = true;
        } catch (NumberFormatException e) {
            // Ignorišemo nevaljan unos
        }
    }

    private void azurirajIzUSD() {
        if (!azuriramPolja) return;

        try {
            String tekst = usdPolje.getText();
            if (tekst.isEmpty()) {
                azuriramPolja = false;
                eurPolje.setText("");
                gbpPolje.setText("");
                azuriramPolja = true;
                return;
            }

            double usd = Double.parseDouble(tekst);
            double eur = usd / EUR_TO_USD;
            double gbp = eur * EUR_TO_GBP;

            azuriramPolja = false;
            eurPolje.setText(String.format("%.2f", eur));
            gbpPolje.setText(String.format("%.2f", gbp));
            azuriramPolja = true;
        } catch (NumberFormatException e) {
            // Ignorišemo nevaljan unos
        }
    }

    private void azurirajIzGBP() {
        if (!azuriramPolja) return;

        try {
            String tekst = gbpPolje.getText();
            if (tekst.isEmpty()) {
                azuriramPolja = false;
                eurPolje.setText("");
                usdPolje.setText("");
                azuriramPolja = true;
                return;
            }

            double gbp = Double.parseDouble(tekst);
            double eur = gbp / EUR_TO_GBP;
            double usd = eur * EUR_TO_USD;

            azuriramPolja = false;
            eurPolje.setText(String.format("%.2f", eur));
            usdPolje.setText(String.format("%.2f", usd));
            azuriramPolja = true;
        } catch (NumberFormatException e) {
            // Ignorišemo nevaljan unos
        }
    }
}