import java.util.ArrayList;

public class TabelaPrinter {

    public static void stampajZaposlene(Zaposleni[] zaposleni) {
        if (zaposleni.length == 0) {
            System.out.println("Nema podataka za prikaz.");
            return;
        }

        int widthId = 3;
        int widthIme = "Ime i Prezime".length();
        int widthTip = 8;
        int widthSatnica = 8;
        int widthSati = 4;
        int widthDodatno = 8;
        int widthPlata = 13;

        for (Zaposleni z : zaposleni) {
            String imePrezime = z.getIme() + " " + z.getPrezime();
            String tip = z.getTipZaposlenog().toString();
            String dodatno = "";

            if (z.getTipZaposlenog() == TipZaposlenog.KONOBAR && z.getPrekovremenihSati() > 0) {
                dodatno = "Prekovr: " + z.getPrekovremenihSati() + "h";
            } else if (z.getTipZaposlenog() == TipZaposlenog.MENADZER && z.getBonus() > 0) {
                dodatno = String.format("Bonus: %.0f EUR", z.getBonus());
            } else {
                dodatno = "-";
            }

            String plata = String.format("%,.2f", z.izracunajMjesecnuPlatu());

            widthId = Math.max(widthId, String.valueOf(z.getId()).length());
            widthIme = Math.max(widthIme, imePrezime.length());
            widthTip = Math.max(widthTip, tip.length());
            widthDodatno = Math.max(widthDodatno, dodatno.length());
            widthPlata = Math.max(widthPlata, plata.length());
        }

        String separator = String.format("+-%s-+-%s-+-%s-+-%s-+-%s-+-%s-+-%s-+",
                "-".repeat(widthId),
                "-".repeat(widthIme),
                "-".repeat(widthTip),
                "-".repeat(widthSatnica),
                "-".repeat(widthSati),
                "-".repeat(widthDodatno),
                "-".repeat(widthPlata));

        String headerFormat = String.format("| %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds |",
                widthId, widthIme, widthTip, widthSatnica, widthSati, widthDodatno, widthPlata);

        String rowFormat = String.format("| %%-%dd | %%-%ds | %%-%ds | %%%d.2f | %%%dd | %%-%ds | %%%ds |",
                widthId, widthIme, widthTip, widthSatnica, widthSati, widthDodatno, widthPlata);

        System.out.println(separator);
        System.out.printf(headerFormat + "%n", "ID", "Ime i Prezime", "Tip", "Satnica", "Sati", "Dodatno", "Plata (EUR)");
        System.out.println(separator);

        for (Zaposleni z : zaposleni) {
            String imePrezime = z.getIme() + " " + z.getPrezime();
            String tip = z.getTipZaposlenog().toString();
            String dodatno = "";

            if (z.getTipZaposlenog() == TipZaposlenog.KONOBAR && z.getPrekovremenihSati() > 0) {
                dodatno = "Prekovr: " + z.getPrekovremenihSati() + "h";
            } else if (z.getTipZaposlenog() == TipZaposlenog.MENADZER && z.getBonus() > 0) {
                dodatno = String.format("Bonus: %.0f EUR", z.getBonus());
            } else {
                dodatno = "-";
            }

            String plata = String.format("%,.2f", z.izracunajMjesecnuPlatu());

            System.out.printf(rowFormat + "%n",
                    z.getId(),
                    imePrezime,
                    tip,
                    z.getPlataPoSatu(),
                    z.getUkupanBrojSati(),
                    dodatno,
                    plata);
        }
        System.out.println(separator);
    }

    public static void stampajObracune(ArrayList<ObracunPlate> obracuni) {
        if (obracuni.isEmpty()) {
            System.out.println("Nema obračuna za prikaz.");
            return;
        }

        int widthId = 3;
        int widthIme = "Ime i Prezime".length();
        int widthTip = 8;
        int widthSati = 4;
        int widthNapomena = 10;
        int widthPlata = 13;

        for (ObracunPlate obr : obracuni) {
            Zaposleni z = obr.getZaposleni();
            String imePrezime = z.getIme() + " " + z.getPrezime();
            String tip = z.getTipZaposlenog().toString();
            String napomena = obr.getNapomena();
            String plata = String.format("%,.2f", obr.getObracunatIznos());

            widthId = Math.max(widthId, String.valueOf(z.getId()).length());
            widthIme = Math.max(widthIme, imePrezime.length());
            widthTip = Math.max(widthTip, tip.length());
            widthNapomena = Math.max(widthNapomena, napomena.length());
            widthPlata = Math.max(widthPlata, plata.length());
        }

        String separator = String.format("+-%s-+-%s-+-%s-+-%s-+-%s-+-%s-+",
                "-".repeat(widthId),
                "-".repeat(widthIme),
                "-".repeat(widthTip),
                "-".repeat(widthSati),
                "-".repeat(widthNapomena),
                "-".repeat(widthPlata));

        String headerFormat = String.format("| %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds |",
                widthId, widthIme, widthTip, widthSati, widthNapomena, widthPlata);

        String rowFormat = String.format("| %%-%dd | %%-%ds | %%-%ds | %%%dd | %%-%ds | %%%ds |",
                widthId, widthIme, widthTip, widthSati, widthNapomena, widthPlata);

        System.out.println(separator);
        System.out.printf(headerFormat + "%n", "ID", "Ime i Prezime", "Tip", "Sati", "Napomena", "Plata (EUR)");
        System.out.println(separator);

        for (ObracunPlate obr : obracuni) {
            Zaposleni z = obr.getZaposleni();
            String imePrezime = z.getIme() + " " + z.getPrezime();
            String tip = z.getTipZaposlenog().toString();
            String plata = String.format("%,.2f", obr.getObracunatIznos());

            System.out.printf(rowFormat + "%n",
                    z.getId(),
                    imePrezime,
                    tip,
                    z.getUkupanBrojSati(),
                    obr.getNapomena(),
                    plata);
        }
        System.out.println(separator);

        double ukupno = 0;
        for (ObracunPlate obr : obracuni) {
            ukupno += obr.getObracunatIznos();
        }
        System.out.printf("\nUKUPAN TROŠAK PLATA: %,.2f EUR\n", ukupno);
    }
}
