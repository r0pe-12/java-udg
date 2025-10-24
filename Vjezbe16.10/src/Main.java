import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void println(String string) {
        System.out.println(string);
    }

    static void main() throws IOException {
        Student.setProlazNaIspitu(50);
//        Student student = new Student("Pera", "Peric", "123/2020", "Neka adresa 1", 85.5f);
        ArrayList<Student> students = new ArrayList<Student>();
        Scanner sc = new Scanner(new File("C:\\Users\\simon\\Desktop\\UDG\\JAVA\\Vjezbe16.10\\studenti.txt"));
        sc.useDelimiter("[|\n]");
        String ime;
        String prezime;
        String index;
        String adresa;
        float bodovi;
        while (sc.hasNext()) {
            ime = sc.next();
            prezime = sc.next();
            index = sc.next();
            adresa = sc.next();
            bodovi = Float.parseFloat(sc.next());
            students.add(new Student(ime, prezime, index, adresa, bodovi));
        }
        sc.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\simon\\Desktop\\UDG\\JAVA\\Vjezbe16.10\\studenti_out.txt"));
        for (Student student : students) {
            writer.write(student.toString());
            writer.newLine();
            println(student.toString());
        }
        writer.close();
    }
}
