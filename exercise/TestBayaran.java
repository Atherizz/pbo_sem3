public class TestBayaran {

    public static void main(String[] args) {
        // Membuat objek
        Manajer man = new Manajer("Agus", 800, 50);
        Programmer prog = new Programmer("Budi", 600, 30);
        Bayaran hr = new Bayaran();

        // Menampilkan hasil
        System.out.println("Bayaran manajer: " + hr.hitungBayaran(man));
        System.out.println("Bayaran programmer: " + hr.hitungBayaran(prog));
    }
}