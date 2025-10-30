class Main {
    static void main(String[] args) {
        Vozilo v1 = new Automobil("Mercedes", 2024, "benzin", 3000, "siva", 5);
        Vozilo v2 = new Kamion("Volvo", 2007, 5000, "crna", 14000, true);
        Vozilo v3 = new Kombi("Renault", 2020, 3000, "plava", 30);
        System.out.println(v1.toString());
        System.out.println(v2.toString());
        System.out.println(v3.toString());
    }
}