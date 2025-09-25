import java.util.ArrayList;

public class MataPelajaran {
    private String nama;
    private int id;
    private ArrayList<Kursus> listKursus; 

    public MataPelajaran(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void tambahKursus(Kursus kursus) {
        listKursus.add(kursus);
    }

    public ArrayList<Kursus> getListKursus() {
        return listKursus;
    }

    public String toString() {
        String info = "Mata Pelajaran: " + nama  + "\nDaftar Kursus:\n";
        for (Kursus kursus : listKursus) {
            info += "- " + kursus.getMataPelajaran().getNama() + " oleh " + kursus.getInstruktur().getNama() + "\n";
        }
        return info;
    }

}
