public class Kuis extends Konten{
    private int jumlahSoal;

    public Kuis(int id, String judul, Kursus kursus, String deskripsi, int jumlahSoal) {
        super(id, judul, kursus, deskripsi);
        this.jumlahSoal = jumlahSoal;
    }

    public void setJumSoal(int jumlahSoal) {
        this.jumlahSoal = jumlahSoal;
    }

    @Override
    public void tampilkanKonten() {
    System.out.println("Judul : " + super.getJudul());
    System.out.println("Konten : ");
    System.out.println(super.getDeskripsi() + " | jumlah soal : " + this.getJumSoal());
}

    public int getJumSoal() {
        return jumlahSoal;
    }

    public void tampilkan(){
        System.out.println("Kuis: " + getJudul() + " dengan jumlah soal (" + jumlahSoal + " Soal) " + getDeskripsi());
    }
}
