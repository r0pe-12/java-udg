public class DrugiZadatak {
//    c000s000g000t082r000p000h48b10022*3C

    static String dajPodatke(String input) {
        int pozicija_c = input.indexOf('c');
        int pozicija_s = input.indexOf('s');
        int pozicija_g = input.indexOf('g');
        int pozicija_t = input.indexOf('t');
        int pozicija_r = input.indexOf('r');
        int pozicija_p = input.indexOf('p');
        int pozicija_h = input.indexOf('h');
        int pozicija_b = input.indexOf('b');


        String smjer_vjetra = input.substring(pozicija_c + 1, pozicija_c + 4);
//        double prosjek_brzine_vjetra = Math.round((Float.parseFloat(input.substring(pozicija_s + 1, pozicija_s + 4)) * 0.44704) * 100) / 100.0;
//        double max_brzina_vjetra = Math.round((Float.parseFloat(input.substring(pozicija_g + 1, pozicija_g + 4)) * 0.44704) * 100) / 100.0;
        String temperatura = input.substring(pozicija_t + 1, pozicija_t + 4);
        String kolicina_padavina_1 = input.substring(pozicija_r + 1, pozicija_r + 4);
        String kolicina_padavina_24 = input.substring(pozicija_p + 1, pozicija_p + 4);
        String vlaznost = input.substring(pozicija_h + 1, pozicija_h + 3);
        String pritisak = input.substring(pozicija_b + 1, pozicija_b + 6);


        String prosjek_format = String.valueOf((Float.parseFloat(input.substring(pozicija_s + 1, pozicija_s + 4)) * 0.44704));
        String maks_format = String.valueOf((Float.parseFloat(input.substring(pozicija_g + 1, pozicija_g + 4)) * 0.44704));
        prosjek_format = prosjek_format.substring(0, prosjek_format.indexOf('.') + 3);
        maks_format = maks_format.substring(0, maks_format.indexOf('.') + 3);

        return "{smjer:" + smjer_vjetra + ", 'brzinaSr':" + prosjek_format + ", brzinaMax:" + maks_format + "}";
    }

    public static void main(String[] args) {
        String podaci = "c123s015g028t068r002p017h53b10020*CS";
        String formatirano = dajPodatke(podaci);

        System.out.println(formatirano);
    }
}
