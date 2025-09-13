package tugas;

public class TokoGameDemo {
    public static void main(String[] args) {
        Game game1 = new Game("The Legend of Zelda", "Action-Adventure",
         50000);
        Game game2 = new Game("Super Mario Odyssey", "Platformer",
         40000);

        Peminjaman peminjaman1 = new Peminjaman(1, "Budi", game1, 
        3);
        Peminjaman peminjaman2 = new Peminjaman(2, "Siti", game2, 
        2);

        System.out.println("Detail Peminjaman 1:");
        peminjaman1.tampilkanPeminjaman();
        System.out.println();

        System.out.println("Detail Peminjaman 2:");
        peminjaman2.tampilkanPeminjaman();
    }
}
