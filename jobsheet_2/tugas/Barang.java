package tugas;

public class Barang {
    String kode;
    String namaBarang;
    int hargaDasar;
    float diskon; 

    Barang(String kode, String namaBarang, int hargaDasar, float diskon) {
        this.kode = kode;
        this.namaBarang = namaBarang;
        this.hargaDasar = hargaDasar;
        this.diskon = diskon;
    }

    int hitungHargaJual() {
        return (int) (hargaDasar - (diskon / 100 * hargaDasar));
    }

    void tampilBarang() {
        System.out.println("Kode Barang : " + kode);
        System.out.println("Nama Barang : " + namaBarang);
        System.out.println("Harga Dasar : Rp" + hargaDasar);
        System.out.println("Diskon      : " + diskon + "%");
        System.out.println("Harga Jual  : Rp" + hitungHargaJual());
    }   
}
