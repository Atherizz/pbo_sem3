package percobaan_4;

public class MainPercobaan4 {
    public static void main(String[] args) {
        Penumpang p = new Penumpang("12345", "Mr. Krab");
        Gerbong gerbong = new Gerbong("A", 10);
        gerbong.getArrayKursi()[0].setPenumpang(p);
        System.out.println(gerbong.info());

        Penumpang budi = new Penumpang("1122334", "budi");
        gerbong.setPenumpang(budi, 1);
        System.out.println(gerbong.info());

    }


}
