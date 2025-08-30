public class AlatTulis {
    String merk;
    String warna;
    double harga;

    public AlatTulis(String merk, String warna, double harga) {
        this.merk = merk;
        this.warna = warna;
        this.harga = harga;
    }

    public void menulis() {
        System.out.println(merk + " warna " + warna + " sedang digunakan untuk menulis...");
    }

    public void info() {
        System.out.println("Merk: " + merk);
        System.out.println("Warna: " + warna);
        System.out.println("Harga: Rp" + harga);
    }
}
