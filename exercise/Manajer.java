public class Manajer extends Pegawai {
    private int tunjangan;

    public Manajer(String nama, int gaji, int tunjangan) {
        super(nama, gaji); 
        this.tunjangan = tunjangan;
    }

    // Method khusus Manajer
    public int getTunjangan() {
        return tunjangan;
    }
}