package tugas;

public class Game {
    String namaGame;
    String genre;
    int hargaSewa;

    Game(String namaGame, String genre, int hargaSewa) {
        this.namaGame = namaGame;
        this.genre = genre;
        this.hargaSewa = hargaSewa;
    }

    void tampilkanGame() {
        System.out.println("Nama Game : " + namaGame);
        System.out.println("Genre     : " + genre);
        System.out.println("Harga Sewa: " + hargaSewa);
    }
}


