package tugas;

public class Peminjaman {
    int id;
    String namaMember;
    Game game;
    int durasiPinjam; 
    int totalBiaya;

    Peminjaman(int id, String namaMember, Game game, int durasiPinjam) {
        this.id = id;
        this.namaMember = namaMember;
        this.game = game;
        this.durasiPinjam = durasiPinjam;
        this.totalBiaya = hitungTotalBiaya();
    }

    int hitungTotalBiaya() {
        return game.hargaSewa * durasiPinjam;
    }

    void tampilkanPeminjaman() {
        System.out.println("ID Member   : " + id);
        System.out.println("Nama Member : " + namaMember);
        game.tampilkanGame();
        System.out.println("Durasi Pinjam: " + durasiPinjam + " hari");
        System.out.println("Total Biaya  : Rp" + totalBiaya);
    }

}
