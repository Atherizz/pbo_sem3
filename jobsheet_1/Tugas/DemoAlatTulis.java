public class DemoAlatTulis {
    public static void main(String[] args) {
        Pensil pensil = new Pensil("Faber-Castell", "Hitam", 3000, "2B", true);
        pensil.info();     
        pensil.menulis();   
        pensil.raut();
        pensil.menghapus();
        System.out.println("---------------");

        Pulpen pulpen = new Pulpen("Kokoro", "Biru", 8000, "Gel", true);
        pulpen.menulis();     
        pulpen.isiUlang();
        pulpen.setJenisTinta("Ballpoint");
        pulpen.info();
        System.out.println("---------------");

        Penghapus penghapus = new Penghapus("Joyko", "Karet", 2000);
        penghapus.info();
        penghapus.hapus();
        penghapus.bersihkan();
        System.out.println("---------------");


        BukuTulis buku = new BukuTulis("Sinar Dunia", 80, "Bergaris");
        buku.info();
        buku.bukaHalaman();
        buku.tulisCatatan(pensil, "Catatan materi PBO: class, object, inheritance.");
    }
}
