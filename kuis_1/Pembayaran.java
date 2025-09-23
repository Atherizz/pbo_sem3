public class Pembayaran {
    private Peserta peserta;
    private Instruktur instruktur;
    private Kursus kursus;
    private int jumlah; // harga yang harus dibayar
    private boolean status; // true = sukses, false = gagal

    // --- constructor ---
    public Pembayaran(Peserta peserta, Kursus kursus, Instruktur instruktur) {
        this.peserta = peserta;
        this.kursus = kursus;
        this.instruktur = instruktur;
        this.jumlah = kursus.getHarga();
        this.status = false; // default: belum dibayar
    }

    // --- method utama ---
    public void prosesPembayaran() {
        if (!cekSaldo()) {
            this.status = false;
            System.out.println("Saldo tidak cukup. Pembayaran gagal.");
            System.out.println("Saldo anda : " + this.peserta.getSaldo());
            return;
        }

        if (peserta == null || instruktur == null || kursus == null) {
            System.out.println("Data transaksi tidak lengkap.");
            return;
        }

        int harga = kursus.getHarga();
        if (harga < 0) {
            System.out.println("Harga kursus tidak valid.");
            return;
        }

    if (peserta.getDaftarKursus().contains(kursus) && kursus.getDaftarPeserta().contains(peserta)) {
        System.out.println("Anda sudah terdaftar pada kursus ini!");
        return;
    }
    
        potongSaldoPeserta();
        transferKeInstruktur();
        this.status = true;
        kursus.getDaftarPeserta().add(peserta);
        peserta.getDaftarKursus().add(kursus);
        System.out.println("Pembayaran berhasil! "
                + peserta.getName() + " sekarang terdaftar di kursus "
                + kursus.getMataPelajaran().getNama());
        
    }

    // --- method pecahan ---
    private boolean cekSaldo() {
        return peserta.getSaldo() >= jumlah;
    }

    private void potongSaldoPeserta() {
        peserta.setSaldo(peserta.getSaldo() - jumlah);
    }

    private void transferKeInstruktur() {
        int bagiInstruktur = (int) (jumlah * 0.7);
        instruktur.setSaldo(instruktur.getSaldo() + bagiInstruktur);
        int bagiSistem = kursus.getHarga() - bagiInstruktur;
        DompetSistem.tambahSaldo(bagiSistem);
    }

    // --- getter ---

    public Instruktur getInstruktur() {
        return instruktur;
    }

    public void setInstruktur(Instruktur instruktur) {
        this.instruktur = instruktur;

    }

    public boolean getStatus() {
        return status;
    }

    public int getJumlah() {
        return jumlah;
    }

    public Peserta getPeserta() {
        return peserta;
    }

    public Kursus getKursus() {
        return kursus;
    }
}
