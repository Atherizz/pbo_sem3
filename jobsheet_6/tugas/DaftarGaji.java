package tugas;

public class DaftarGaji {
    private Pegawai[] listPegawai;
    private int index = 0;

    public DaftarGaji(int kapasitas) {
        this.listPegawai = new Pegawai[kapasitas];
    }

    public void addPegawai(Pegawai p) {
        if (index < listPegawai.length) {
            listPegawai[index++] = p;
        }
    }

    public void printSemuaGaji() {
        for (int i = 0; i < index; i++) {
            Pegawai p = listPegawai[i];
            System.out.println(p.getNama() + " : " + p.getGaji());
        }
    }
}
