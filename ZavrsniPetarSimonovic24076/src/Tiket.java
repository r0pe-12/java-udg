import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

public class Tiket {

    public static final String FILE_NAME = "tickets.json";
    public static final ArrayList<Tiket> TIKETI = new ArrayList<>();

    private long broj_tiketa;
    private String tablice;
    private LocalDateTime dateTime;
    private TipVozila tipVozila;

    static {
        loadFromFile();
    }

    public static ArrayList<Tiket> getAll() {
        return TIKETI;
    }

    public static void removeTiket(Tiket tiket) {
        TIKETI.remove(tiket);
        saveToFile();
    }

    public static ArrayList<Tiket> pretraziPoTablici(String tablice) {
        ArrayList<Tiket> pretraziPoTablici = new ArrayList<>();
        for (Tiket tiket : getAll()) {
            if (tiket.getTablice().equalsIgnoreCase(tablice)) {
                pretraziPoTablici.add(tiket);
            }
        }
        return pretraziPoTablici;
    }

    private static void saveToFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("[\n");

            for (int i = 0; i < TIKETI.size(); i++) {
                Tiket t = TIKETI.get(i);
                writer.write("  " + t.toJson());
                if (i < TIKETI.size() - 1) writer.write(",");
                writer.write("\n");
            }

            writer.write("]");
        } catch (IOException e) {
            System.out.println("Greška pri upisu tiketa u fajl.");
        }
    }

    private static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            StringBuilder json = new StringBuilder();

            while ((line = br.readLine()) != null) {
                json.append(line.trim());
            }

            parseJson(json.toString());
        } catch (IOException e) {
            System.out.println("Greška pri čitanju tiketa iz fajla.");
        }
    }

    private static void parseJson(String json) {
        json = json.replace("[", "").replace("]", "");

        if (json.trim().isEmpty()) return;

        String[] items = json.split("\\},\\{");

        for (String item : items) {
            item = item.replace("{", "").replace("}", "");

            String[] fields = item.split(",");
            long broj_tiketa = 0;
            String tablice = "";
            LocalDateTime dateTime = null;
            TipVozila tipVozila = null;

            for (String field : fields) {
                String[] pair = field.split(":");
                String key = pair[0].replace("\"", "").trim();
                String value = pair[1].replace("\"", "").trim();
//                System.out.print(Arrays.toString(pair));
                if (pair.length > 2) {
                    value = (pair[1] + ":" + pair[2] + ":" + pair[3]).replace("\"", "").trim();
                }

//                System.out.println(key + ": " + value);

                switch (key) {
                    case "broj_tiketa" -> broj_tiketa = Long.parseLong(value);
                    case "tablice" -> tablice = value;
                    case "dateTime" -> dateTime = LocalDateTime.parse(value);
                    case "tipVozila" -> tipVozila = TipVozila.valueOf(value);
                }
            }

            TIKETI.add(new Tiket(broj_tiketa, tablice, dateTime, tipVozila, false));
        }
    }

    private String toJson() {
        return "{"
                + "\"broj_tiketa\":" + broj_tiketa + ","
                + "\"tablice\":\"" + tablice + "\","
                + "\"tipVozila\":\"" + tipVozila + "\","
                + "\"dateTime\":\"" + dateTime + "\""
                + "}";
    }


    public Tiket(long broj_tiketa, String tablice, LocalDateTime dateTime, TipVozila tipVozila, boolean save) {
        this.broj_tiketa = broj_tiketa;
        this.tablice = tablice;
        this.dateTime = dateTime;
        this.tipVozila = tipVozila;
        if (save) {
            TIKETI.add(this);
            saveToFile();
        }
    }

    public Tiket(long broj_tiketa, String tablice, LocalDateTime dateTime, TipVozila tipVozila) {
        this(broj_tiketa, tablice, dateTime, tipVozila, true);
    }

    public Tiket(String tablice, LocalDateTime dateTime, TipVozila tipVozila) {
        this(dateTime.toEpochSecond(ZoneOffset.UTC), tablice, dateTime, tipVozila, true);
    }

    public long getBroj_tiketa() {
        return broj_tiketa;
    }

    public void setBroj_tiketa(long broj_tiketa) {
        this.broj_tiketa = broj_tiketa;
    }

    public String getTablice() {
        return tablice;
    }

    public void setTablice(String tablice) {
        this.tablice = tablice;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public TipVozila getTipVozila() {
        return tipVozila;
    }

    public void setTipVozila(TipVozila tipVozila) {
        this.tipVozila = tipVozila;
    }
}
