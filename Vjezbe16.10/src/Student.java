import java.util.ArrayList;

public class Student {
    public String ime;
    public String prezime;
    public String index;
    public String adresa;
    public float bodovi;
    public boolean polozio;
    public static int prolazNaIspitu;

    public Student(String ime, String prezime, String index, String adresa, float bodovi) {
        this.ime = ime;
        this.prezime = prezime;
        this.index = index;
        this.adresa = adresa;
        this.bodovi = bodovi;
        this.azurirajProlaz();
    }

    public Student() {
    }

    public String getIme() {
        return ime;
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

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public float getBodovi() {
        return bodovi;
    }

    public void setBodovi(float bodovi) {
        this.bodovi = bodovi;
        this.azurirajProlaz();
    }

    public boolean isPolozio() {
        return polozio;
    }

    public void setPolozio(boolean polozio) {
        this.polozio = polozio;
    }

    public static int getProlazNaIspitu() {
        return prolazNaIspitu;
    }

    public static void setProlazNaIspitu(int prolazNaIspitu) {
        Student.prolazNaIspitu = prolazNaIspitu;
    }

    public void azurirajProlaz() {
        this.polozio = this.bodovi >= Student.prolazNaIspitu;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", index='" + index + '\'' +
                ", adresa='" + adresa + '\'' +
                ", bodovi=" + bodovi +
                ", polozio=" + polozio +
                '}';
    }

    public static ArrayList<Student> izdvojUlica(ArrayList<Student> students, String ulica) {
        ArrayList<Student> izdvojUlica = new ArrayList<>();
        for (Student student : students) {
            if (student.getAdresa().toLowerCase().contains(ulica.toLowerCase())) {
                izdvojUlica.add(student);
            }
        }
        return izdvojUlica;
    }

    public static ArrayList<Student> izdvojProlaz(ArrayList<Student> students, boolean polozio) {
        ArrayList<Student> izdvojProlaz = new ArrayList<>();
        for (Student student : students) {
            if (student.isPolozio() == polozio) {
                izdvojProlaz.add(student);
            }
        }
        return izdvojProlaz;
    }
}
