import java.util.ArrayList;

public class Peserta extends User {
    private String jenjangPendidikan;
    private ArrayList<Kursus> daftarKursus;

    public Peserta(int id, String name, String email, String password, String jenjangPendidikan) {
        super(id, name, email, password);
        this.jenjangPendidikan = jenjangPendidikan;
        this.daftarKursus = new ArrayList<>();
    }

    public String getJenjangPendidikan() {
        return jenjangPendidikan;
    }

    public ArrayList<Kursus> getDaftarKursus() {
        return daftarKursus;
    }

    public void setJenjangPendidikan(String jenjangPendidikan) {
        this.jenjangPendidikan = jenjangPendidikan;
    }

    public void pendaftaranKursus(Kursus kursus) {
        if (kursus.getIsBerbayar()) {
            System.out.println("Kursus " + kursus.getMataPelajaran().getNama()
                    + " adalah kursus berbayar, pastikan anda memiliki saldo yang cukup");
            System.out.println("Harga : " + kursus.getHarga());
            Pembayaran bayar = new Pembayaran(this, kursus, kursus.getInstruktur());
            bayar.prosesPembayaran();
            if (bayar.getStatus()) {
                System.out.println("Kursus " + kursus.getMataPelajaran().getNama() + " berhasil ditambahkan!");
            } else {
                System.out.println("Pembayaran gagal. Kursus tidak dapat ditambahkan.");
            }

        } else {

            if (this.getDaftarKursus().contains(kursus) && kursus.getDaftarPeserta().contains(this)) {
                System.out.println("Anda sudah terdaftar pada kursus ini!");
                return;

            }
            daftarKursus.add(kursus);
            kursus.getDaftarPeserta().add(this);
            System.out.println("Kursus " + kursus.getMataPelajaran().getNama() + " berhasil ditambahkan!");
        }
    }

public void tampilkanInfo() {
    System.out.println("====================================");
    System.out.println("Nama              : " + getName());
    System.out.println("Jenjang Pendidikan: " + getJenjangPendidikan());
    System.out.println("Daftar Kursus     :");
    
    if (daftarKursus.isEmpty()) {
        System.out.println("- Belum mengambil kursus");
    } else {
        for (Kursus kursus : daftarKursus) {
            System.out.println("- " + kursus.getMataPelajaran().getNama() +
                               " oleh " + kursus.getInstruktur().getNama());
        }
    }
    System.out.println("====================================");
}

}
