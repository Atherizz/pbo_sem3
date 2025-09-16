package tugas;

public class Anggota {
    private String noKTP;
    private String nama;
    private int limitPinjaman;  
    private int jumlahPinjaman;

    Anggota(String noKTP, String nama, int limitPinjaman) {
        this.noKTP = noKTP;
        this.nama = nama;
        this.limitPinjaman = limitPinjaman;
        this.jumlahPinjaman = 0;
    }

    public String getNoKTP() {
        return noKTP;
    }
    
    public String getNama() {
        return nama;
    }

    public int getLimitPinjaman() {
        return limitPinjaman;
    }   

    public int getJumlahPinjaman() {
        return jumlahPinjaman;
    }

    public void pinjam(int uang) {
        if (uang > limitPinjaman) {
            System.out.println("Maaf, jumlah pinjaman melebihi limit.");
        } else if (jumlahPinjaman + uang > limitPinjaman) {
            System.out.println("Maaf, jumlah pinjaman melebihi limit.");
        } else {
            jumlahPinjaman += uang;
        }
    }

    public void angsur(int uang) {
        int minimalAngsuran = (int) (0.1 * jumlahPinjaman);
        if (uang < minimalAngsuran) {
            System.out.println("Maaf, angsuran harus 10% dari jumlah pinjaman");
        } else if (uang < 0) {
            System.out.println("Maaf, jumlah angsuran harus positif.");
        } else if (uang > jumlahPinjaman) {
            System.out.println("Maaf, jumlah angsuran melebihi jumlah pinjaman.");
        } else {
            jumlahPinjaman -= uang;
        }
    }
}
