public class Video extends Konten {
    private int durasi;

public Video(int id, String judul, Kursus kursus, String deskripsi, int durasi) {
    super(id, judul, kursus, deskripsi);
    this.durasi = durasi;
}

public void setDurasi(int durasi) {
    this.durasi = durasi;
}
public int getDurasi() {
    return durasi;
}

@Override
public void tampilkanKonten() {
    System.out.println("Judul : " + super.getJudul());
    System.out.println("Konten : ");
    System.out.println(super.getDeskripsi() + " | durasi : " + this.getDurasi());
}

public void tampilkan(){
    System.out.println("Video: " + getJudul() + " dengan durasi (" + durasi + " Menit) " + getDeskripsi());
}

}
