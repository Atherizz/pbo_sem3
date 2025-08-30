public class Penghapus {
    String merk;
    String bahan;
    double harga;

    public Penghapus(String merk, String bahan, double harga) {
        this.merk = merk;
        this.bahan = bahan;
        this.harga = harga;
    }

    public void hapus() {
        System.out.println("Penghapus " + merk + " sedang digunakan untuk menghapus tulisan pensil...");
    }

    public void bersihkan() {
    System.out.println("Penghapus " + merk + " sedang dibersihkan dari sisa kotoran...");
    }

    public void info() {
        System.out.println("Merk: " + merk);
        System.out.println("Bahan: " + bahan);
        System.out.println("Harga: Rp" + harga);
    }
}
