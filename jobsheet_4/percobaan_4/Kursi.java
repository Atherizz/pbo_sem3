package percobaan_4;

public class Kursi {
    private String nomor;
    private Penumpang penumpang;

    public Kursi(String nomor) {
        this.nomor = nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getNomor() {
        return nomor;
    }

    public void setPenumpang(Penumpang penumpang) {
        if (this.penumpang != null) {
            System.out.println("Kursi sudah terisi");
            return;
        }
        this.penumpang = penumpang;
    }

    public Penumpang getPenumpang() {
        return penumpang;
    }

    public String info() {
        String info = "";
        info += "Nomor: " + this.nomor + "\n";
        if (this.penumpang != null) {
            info += "Penumpang: " + this.penumpang.info() + "\n";
        } 
        return info;
    }
}
