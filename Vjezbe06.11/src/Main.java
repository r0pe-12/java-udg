//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    String input;
    ArrayList<EProizvodi> artikli = new ArrayList<>();
    do {
        System.out.print("Unesi komandu (1-unos, 2-pregled, 3-pregled tipa, exit): ");
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();

        switch (input) {
            case "1":
                System.out.print("Koji artikal zelite unijeti (1-racunar, 2-telefon, 3-tv): ");
                String artikal = sc.nextLine();
                System.out.print("Unesi (odvojeno zarezom) opis, sifru, uvoznu cijenu, ");
                switch (artikal) {
                    case "1":
                        System.out.print("procesor, memoriju: ");
                        artikal = sc.nextLine();
                        artikli.add(Racunari.fromCsv(artikal));
                        break;
                    case "2":
                        System.out.print("os, velicinu ekrana: ");
                        artikal = sc.nextLine();
                        artikli.add(Telefoni.fromCsv(artikal));
                        break;
                    case "3":
                        System.out.print("velicinu ekrana: ");
                        artikal = sc.nextLine();
                        artikli.add(TV.fromCsv(artikal));
                        break;
                    default:
                        System.out.print("Ne postoji");
                        break;
                }
                break;
            case "2":
                for (EProizvodi p : artikli) {
                    System.out.println(p.toString());
                }
                break;
            case "3":
                System.out.print("Koji artikal zelite listati (1-racunar, 2-telefon, 3-tv): ");
                String tip = sc.nextLine();
                ArrayList<EProizvodi> lista = new ArrayList<>();
                switch (tip) {
                    case "1":
                        lista = artikli.stream().filter(t -> t.getSifra().startsWith("RA")).collect(Collectors.toCollection(ArrayList::new));
                        break;
                    case "2":
                        lista = artikli.stream().filter(t -> t.getSifra().startsWith("TE")).collect(Collectors.toCollection(ArrayList::new));
                        break;
                    case "3":
                        lista = artikli.stream().filter(t -> t.getSifra().startsWith("TV")).collect(Collectors.toCollection(ArrayList::new));
                        break;
                    default:
                        System.out.print("Ne postoji");
                        break;
                }
                for (EProizvodi p : lista) {
                    System.out.println(p.toString());
                }
                break;
        }

    } while (!input.equals("exit"));
}
