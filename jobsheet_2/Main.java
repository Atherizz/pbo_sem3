public class Main {
    public static void main(String[] args) {
        Karyawan k1 = new Karyawan();
        k1.id = 1;
        k1.nama = "Andi";
        k1.jenisKelamin = JenisKelamin.LAKI_LAKI;
        k1.jabatan = "Software Engineer";
        k1.gaji = 8000000;

        k1.tampilkanData();
        k1.lihatGaji();
    }
}