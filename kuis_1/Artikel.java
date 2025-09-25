public class Artikel extends Konten{
    private String penulis;

    public Artikel(int id, String judul, Kursus kursus, String deskripsi, String penulis) {
        super(id, judul, kursus, deskripsi);
        this.penulis = penulis;
    }

        @Override
    public void tampilkanKonten() {
    System.out.println("Judul : " + super.getJudul());
    System.out.println("Konten : ");
    System.out.println(super.getDeskripsi() + " | penulis : " + this.getPenulis());
}

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }
    public String getPenulis() {
        return penulis;
    }

    public void tampilkan(){
        System.out.println("Artikel: " + getJudul() + " di rilis oleh " + penulis + " " + getDeskripsi());
    }
}
