import java.util.ArrayList;

public class Instruktur extends User {
    private String nama;
    private String keahlian;
    private ArrayList<Kursus> daftarKursus;

    Instruktur(String username, String email, String password, String nama, String keahlian) {
        super(username, email, password);
        this.nama = nama;
        this.keahlian = keahlian;
        this.daftarKursus = new ArrayList<>();
    }
    public ArrayList<Kursus> getDaftarKursus() {
        return daftarKursus;
    }
    
    public String getNama() {
        return nama;
    }

    public String getKeahlian() {
        return keahlian;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setKeahlian(String keahlian) {
        this.keahlian = keahlian;
    }

    public void tampilkanInfo() {
        System.out.println("Nama Instruktur: " + nama);
        System.out.println("Keahlian: " + keahlian);
        System.out.println("Username: " + getName());
        System.out.println("Email: " + getEmail());
    }

    public void tambahKursus(Kursus kursus){
        daftarKursus.add(kursus);
        kursus.setInstruktur(this);
    }


}
