package tugas;

import java.util.Scanner;

public class TestKoperasi
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        Anggota donny = new Anggota("111333444", "Donny", 5000000);

        int pilihan;
        do {
            System.out.println("\n=== Menu Koperasi ===");
            System.out.println("1. Tampilkan Data Anggota");
            System.out.println("2. Pinjam Uang");
            System.out.println("3. Bayar Angsuran");
            System.out.println("4. Tampilkan Jumlah Pinjaman");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    System.out.println("Nama Anggota: " + donny.getNama());
                    System.out.println("Limit Pinjaman: " + donny.getLimitPinjaman());
                    break;
                case 2:
                    System.out.print("Masukkan jumlah pinjaman: ");
                    int pinjam = input.nextInt();
                    donny.pinjam(pinjam);
                    System.out.println("Jumlah pinjaman saat ini: " + donny.getJumlahPinjaman());
                    break;
                case 3:
                    System.out.print("Masukkan jumlah angsuran: ");
                    int angsur = input.nextInt();
                    donny.angsur(angsur);
                    System.out.println("Jumlah pinjaman saat ini: " + donny.getJumlahPinjaman());
                    break;
                case 4:
                    System.out.println("Jumlah pinjaman saat ini: " + donny.getJumlahPinjaman());
                    break;
                case 5:
                    System.out.println("Keluar dari program.");
                    break;
                default:
                    System.out.println("Menu tidak valid!");
            }
        } while (pilihan != 5);

        input.close();
    }
}
