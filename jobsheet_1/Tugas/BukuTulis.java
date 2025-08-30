public class BukuTulis {
    String merk;
    int jumlahHalaman;
    String jenisKertas;

    public BukuTulis(String merk, int jumlahHalaman, String jenisKertas) {
        this.merk = merk;
        this.jumlahHalaman = jumlahHalaman;
        this.jenisKertas = jenisKertas;
    }

    public void bukaHalaman() {
        System.out.println("Membuka buku tulis " + merk + " dengan " + jumlahHalaman + " halaman...");
    }

    public void tulisCatatan(Pensil pensil, String isi) {
        System.out.println("Menulis catatan di buku " + merk + ": " + isi + " menggunakan pensil " + pensil.merk);
    }

    public void info() {
        System.out.println("Merk: " + merk);
        System.out.println("Jumlah halaman: " + jumlahHalaman);
        System.out.println("Jenis kertas: " + jenisKertas);
    }
}
