package tugas;

public class Lingkaran {
    double phi = 3.14;
    double r;

    Lingkaran(double r) {
        this.r = r;
    }

    double hitungLuas() {
        return phi * r * r;
    }   
    double hitungKeliling() {
        return 2 * phi * r;
    }
}
