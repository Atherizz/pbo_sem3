import tugas.DaftarGaji;
import tugas.Dosen;

public class MainApp {
    public static void main(String[] args) {
        
        Dosen d1 = new Dosen("19876", "Budi", "Malang");
        d1.setSKS(12);

        Dosen d2 = new Dosen("20411", "Sinta", "Surabaya");
        d2.setSKS(8);

        DaftarGaji daftar = new DaftarGaji(5);
        daftar.addPegawai(d1);
        daftar.addPegawai(d2);

        daftar.printSemuaGaji();
    }
}
