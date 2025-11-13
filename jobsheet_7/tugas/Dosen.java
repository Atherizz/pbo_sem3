package tugas;

public class Dosen extends Manusia {
    @Override
    public void makan() {
        System.out.println("Dosen makan di sela-sela mengoreksi tugas.");
    }

    public void lembur() {
        System.out.println("Dosen sering lembur sampai malam.");
    }
}