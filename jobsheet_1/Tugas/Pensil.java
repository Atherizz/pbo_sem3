public class Pensil extends AlatTulis {
    String jenis; 
    boolean adaPenghapus;

    public Pensil(String merk, String warna, double harga, String jenis, boolean adaPenghapus) {
        super(merk, warna, harga);
        this.jenis = jenis;
        this.adaPenghapus = adaPenghapus;
    }

    public void raut() {
        System.out.println("Pensil " + merk + " sedang diraut...");
    }

    public void menghapus() {
        if (adaPenghapus) {
            System.out.println("Penghapus pada pensil " + merk + " sedang digunakan untuk menghapus tulisan...");
        } else {
            System.out.println("Pensil " + merk + " tidak memiliki penghapus.");
        }
    }

    @Override
    public void info() {
        super.info();
        System.out.println("Jenis: " + jenis);
        System.out.println("Ada penghapus: " + (adaPenghapus ? "Ya" : "Tidak"));
    }
}
