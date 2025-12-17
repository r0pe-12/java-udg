import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 1) Kreiranje korisnika (automatski upis u users.json ako si ostavio UserStore.add(this) u konstruktoru)
        User u1 = new User(1, "Marko", "Markovic", 0, User.Role.KLIJENT);
        User u2 = new User(2, "Ana", "Anic", 15, User.Role.RADNIK);

        // 2) Ucitaj sve iz fajla i ispisi
        System.out.println("=== SVI KORISNICI (posle kreiranja) ===");
        List<User> svi = UserStore.all();
        svi.forEach(System.out::println);

        // 3) Update kroz settere (ovo treba da pozove UserStore.update(u1) i snimi u JSON)
        u1.setIme("Petar");
        u1.setPrezime("Petrovic");
        u1.setRole(User.Role.RADNIK);

        // 4) Ponovo ucitaj iz fajla i ispisi (da vidis da je update stvarno sacuvan)
        System.out.println("\n=== SVI KORISNICI (posle update) ===");
        UserStore.all().forEach(System.out::println);
    }
}
