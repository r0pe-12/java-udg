public class KompleksniBroj {
    private float realniDio;
    private float imaginarniDio;

    //    konstruktori
    public KompleksniBroj() {
        realniDio = 0;
        imaginarniDio = 0;
    }

    public KompleksniBroj(float realniDio, float imaginarniDio) {
        this.realniDio = realniDio;
        this.imaginarniDio = imaginarniDio;
    }

    public KompleksniBroj(float realniDio) {
        this.realniDio = realniDio;
        this.imaginarniDio = 0;
    }

    //    getteri i setteri
    public float getRealniDio() {
        return realniDio;
    }

    public void setRealniDio(float realniDio) {
        this.realniDio = realniDio;
    }

    public float getImaginarniDio() {
        return imaginarniDio;
    }

    public void setImaginarniDio(float imaginarniDio) {
        this.imaginarniDio = imaginarniDio;
    }

    public void stampaj() {
        if (imaginarniDio >= 0) {
            System.out.printf("%.2f + %.2fi\n", realniDio, imaginarniDio);
        } else {
            System.out.printf("%.2f - %.2fi\n", realniDio, -imaginarniDio);
        }
    }

    public float modul() {
        return (float) Math.sqrt(realniDio * realniDio + imaginarniDio * imaginarniDio);
    }

    //    sabiranje dva kompleksna broja
    public KompleksniBroj saberi(KompleksniBroj z) {
        return new KompleksniBroj(this.realniDio + z.realniDio, this.imaginarniDio + z.imaginarniDio);
    }



}
