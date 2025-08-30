public class Pulpen extends AlatTulis {

    String jenisTinta;
    boolean bisaDiisiUlang;

    public Pulpen(String merk, String warna, double harga, String jenisTinta, boolean bisaDiisiUlang) {
        super(merk, warna, harga);
        this.jenisTinta = jenisTinta;
        this.bisaDiisiUlang = bisaDiisiUlang;
    }

    public void isiUlang() {
        if (bisaDiisiUlang) {
            System.out.println("Pulpen " + merk + " sedang diisi ulang tintanya...");
        } else {
            System.out.println("Pulpen " + merk + " tidak bisa diisi ulang.");
        }
    }
    
    public void setJenisTinta(String jenisTinta) {
        this.jenisTinta = jenisTinta;
        System.out.println("Jenis tinta pulpen " + merk + " diganti menjadi: " + jenisTinta);
    }

    @Override
    public void info() {
        super.info();
        System.out.println("Jenis tinta: " + jenisTinta);
        System.out.println("Bisa diisi ulang: " + (bisaDiisiUlang ? "Ya" : "Tidak"));
    }
}
