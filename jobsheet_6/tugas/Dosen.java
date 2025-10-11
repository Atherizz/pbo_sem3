package tugas;

public class Dosen extends Pegawai {
    private int jumlahSKS;
    private static final int TARIF_SKS = 150000;

    public Dosen(String nip, String nama, String alamat) {
        super(nip, nama, alamat);
    }

    public void setSKS(int sks) {
        this.jumlahSKS = sks;
    }

    @Override
    public int getGaji() {
        return jumlahSKS * TARIF_SKS;
    }
}
