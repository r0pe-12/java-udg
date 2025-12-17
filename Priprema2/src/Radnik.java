import java.io.*;
import java.util.ArrayList;

public class Radnik {

    private static final String FILE_NAME = "radnici.json";
    private static final ArrayList<Radnik> RADNICI = new ArrayList<>();

    private String ime;
    private String prezime;
    private int id;

    static {
        loadFromFile();
    }

    public static ArrayList<Radnik> getAll() {
        return RADNICI;
    }

    private static void saveToFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write("[\n");

            for (int i = 0; i < RADNICI.size(); i++) {
                Radnik r = RADNICI.get(i);
                writer.write("  " + r.toJson());
                if (i < RADNICI.size() - 1) writer.write(",");
                writer.write("\n");
            }

            writer.write("]");
        } catch (IOException e) {
            System.out.println("Greška pri upisu radnika u fajl.");
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
            System.out.println("Greška pri čitanju radnika iz fajla.");
        }
    }

    private static void parseJson(String json) {
        json = json.replace("[", "").replace("]", "");

        if (json.trim().isEmpty()) return;

        String[] items = json.split("\\},\\{");

        for (String item : items) {
            item = item.replace("{", "").replace("}", "");

            String[] fields = item.split(",");
            int id = 0;
            String ime = "", prezime = "";

            for (String field : fields) {
                String[] pair = field.split(":");
                String key = pair[0].replace("\"", "").trim();
                String value = pair[1].replace("\"", "").trim();

                switch (key) {
                    case "id" -> id = Integer.parseInt(value);
                    case "ime" -> ime = value;
                    case "prezime" -> prezime = value;
                }
            }

            RADNICI.add(new Radnik(id, ime, prezime, false));
        }
    }

    private Radnik(int id, String ime, String prezime, boolean save) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        if (save) {
            RADNICI.add(this);
            saveToFile();
        }
    }

    public Radnik(int id, String ime, String prezime) {
        this(id, ime, prezime, true);
    }

    private String toJson() {
        return "{"
                + "\"id\":" + id + ","
                + "\"ime\":\"" + ime + "\","
                + "\"prezime\":\"" + prezime + "\""
                + "}";
    }


    public String getIme() {
        return ime;
    }

    public String getImePrezime() {
        return ime + " " + prezime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
}
