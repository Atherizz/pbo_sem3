import java.util.ArrayList;

public class Kursus {

    private int id;
    private String judul;
    private MataPelajaran mataPelajaran;
    private ArrayList<Konten> daftarKonten;
    private Instruktur instruktur;
    private ArrayList<Peserta> daftarPeserta;
    private boolean isBerbayar;
    private int harga = 0;

    public Kursus(int id, String judul, MataPelajaran mataPelajaran, Instruktur instruktur, boolean isBerbayar) {
        this.id = id;
        this.judul = judul;
        this.mataPelajaran = mataPelajaran;
        this.instruktur = instruktur;
        this.isBerbayar = isBerbayar;
        this.daftarKonten = new ArrayList<>();
        this.daftarPeserta = new ArrayList<>();
    }

    public int getHarga() {
        return harga;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public MataPelajaran getMataPelajaran() {
        return mataPelajaran;
    }

    public void setMataPelajaran(MataPelajaran mataPelajaran) {
        this.mataPelajaran = mataPelajaran;
    }

    public Instruktur getInstruktur() {
        return instruktur;
    }

    public void setInstruktur(Instruktur instruktur) {
        this.instruktur = instruktur;
    }

    public boolean getIsBerbayar() {
        return isBerbayar;
    }

    public ArrayList<Peserta> getDaftarPeserta() {
        return daftarPeserta;
    }

    public ArrayList<Konten> getDaftarKonten() {
        return daftarKonten;
    }

    public void setBerbayar(boolean berbayar) {
        isBerbayar = berbayar;
    }

    public void tambahKonten(Konten konten) {
        daftarKonten.add(konten);
    }

    public String toString() {
        String info = "";
        info += "Mata Pelajaran: " + mataPelajaran.getNama() + "\n";
        info += "Instruktur: " + instruktur.getNama() + "\n";
        info += "Daftar Konten:\n";
        for (Konten konten : daftarKonten) {
            info += "- " + konten.getJudul() + "\n";
        }
        return info;
    }

}