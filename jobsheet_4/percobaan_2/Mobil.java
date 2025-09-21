package percobaan_2;

public class Mobil {
    private String nama;
    private String merk;
    private int biaya;

    public Mobil() {
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return this.nama;
    }

    public String getMerk() {
        return this.merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }
    public void setBiaya(int biaya) {
        this.biaya = biaya;
    }

    public int getBiaya() {
        return this.biaya;
    }

    public int hitungBiayaMobil(int hari) {
        return biaya * hari;
    }
}

