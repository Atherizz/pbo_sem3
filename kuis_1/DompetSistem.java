public class DompetSistem {
    private static int saldo = 0;

    public static void tambahSaldo(int nominal) {
        saldo += nominal;
    }

    public static int getSaldo() {
        return saldo;
    }
}
