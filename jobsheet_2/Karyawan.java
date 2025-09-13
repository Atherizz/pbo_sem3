class Karyawan {
    int id;
    String nama;
    JenisKelamin jenisKelamin;
    String jabatan;
    int gaji;

    void tampilkanData() {
        System.out.println("ID: " + id);
        System.out.println("Nama: " + nama);
        System.out.println("Jenis Kelamin: " + jenisKelamin);
        System.out.println("Jabatan: " + jabatan);
    }

    void lihatGaji() {
        System.out.println("Gaji: Rp" + gaji);
    }
}

enum JenisKelamin {
    LAKI_LAKI,
    PEREMPUAN
}


