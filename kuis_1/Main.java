import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Kursus> daftarKursus = new ArrayList<>();
    static ArrayList<Konten> daftarKonten = new ArrayList<>();
    static ArrayList<MataPelajaran> daftarMataPelajaran = new ArrayList<>();
    static ArrayList<User> daftarUsers = new ArrayList<>();

    public static void tampilkanKursus(ArrayList<MataPelajaran> daftarMataPelajaran) {
        for (MataPelajaran mp : daftarMataPelajaran) {
            System.out.println("Mata Pelajaran : " + mp.getNama());
            for (Kursus kursus : mp.getListKursus()) {
                String infoHarga = kursus.getIsBerbayar()
                        ? "Rp" + kursus.getHarga()
                        : "FREE";

                System.out.println(
                        kursus.getId() + ". " + kursus.getJudul() +
                                " | Instruktur: " + kursus.getInstruktur().getNama() +
                                " | " + infoHarga);

            }
        }
    }

    public static void tampilkanKonten(Kursus kursus) {
        ArrayList<Konten> daftarKonten = kursus.getDaftarKonten();
        for (Konten konten : daftarKonten) {

            if (konten instanceof Artikel) {
                ((Artikel) konten).tampilkanKonten();
            } else if (konten instanceof Video) {
                ((Video) konten).tampilkanKonten();
            } else if (konten instanceof Kuis) {
                ((Kuis) konten).tampilkanKonten();
            }
        }
    }

    public static void menuKelolaKonten(Kursus kursus, Scanner sc) {
        int pilih;
        do {
            System.out.println("\n=== Kelola Konten: " + kursus.getJudul() + " ===");
            System.out.println("1. Lihat Konten");
            System.out.println("2. Tambah Konten");
            System.out.println("3. Hapus Konten");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            pilih = sc.nextInt();

            switch (pilih) {
                case 1:
                    tampilkanKonten(kursus);
                    break;
                case 2:
                    int id = daftarKonten.size() + 1;
                    System.out.println("=== Tambah Konten ===");
                    System.out.println("1. Artikel");
                    System.out.println("2. Kuis");
                    System.out.println("3. Video");
                    System.out.print("Masukkan nomor tipe konten: ");
                    int tipe = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Judul konten: ");
                    String judul = sc.nextLine();

                    System.out.print("Deskripsi: ");
                    String deskripsi = sc.nextLine();

                    Konten kontenBaru = null;

                    switch (tipe) {
                        case 1: {
                            System.out.print("Penulis: ");
                            String penulis = sc.nextLine();
                            kontenBaru = new Artikel(id, judul, kursus, deskripsi, penulis);
                            break;
                        }
                        case 2: {
                            System.out.print("Jumlah soal: ");
                            int jumlahSoal = sc.nextInt();
                            sc.nextLine();
                            kontenBaru = new Kuis(id, judul, kursus, deskripsi, jumlahSoal);
                            break;
                        }
                        case 3: {
                            System.out.print("Durasi (detik): ");
                            int durasi = sc.nextInt();
                            sc.nextLine();
                            kontenBaru = new Video(id, judul, kursus, deskripsi, durasi);
                            break;
                        }
                        default:
                            System.out.println("Tipe tidak valid.");
                            break;
                    }

                    if (kontenBaru != null) {
                        kursus.getDaftarKonten().add(kontenBaru);
                        daftarKonten.add(kontenBaru);
                        System.out.println("Konten berhasil ditambahkan dengan ID: " + kontenBaru.getId());
                    }
                    break;

                case 3:
                    tampilkanKonten(kursus);
                    System.out.print("Masukkan id konten yang akan dihapus: ");
                    int idKonten = sc.nextInt();
                    kursus.hapusKonten(idKonten, daftarKonten);
                    break;
                case 0:
                    System.out.println("Kembali ke menu kursus.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilih != 0);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        InisiasiObjek.initiateObject(daftarKursus, daftarKonten, daftarMataPelajaran, daftarUsers);

        while (true) { 
            int pilihan;
            User userLogin = null;

            do {
                System.out.println("===== MENU =====");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Keluar");
                System.out.print("Pilih menu: ");
                pilihan = sc.nextInt();

                switch (pilihan) {
                    case 1:
                        System.out.println("=== REGISTER PESERTA ===");
                        System.out.print("Masukkan nama       : ");
                        String regNama = sc.next();
                        String regEmail;

                        while (true) {
                            System.out.print("Masukkan email      : ");
                            regEmail = sc.next();

                            boolean isRegistered = false;
                            for (User user : daftarUsers) {
                                if (user.getEmail().equals(regEmail)) {
                                    isRegistered = true;
                                    break;
                                }
                            }

                            if (isRegistered) {
                                System.out.println("Email sudah digunakan! silahkan coba lagi");
                            } else {
                                break;
                            }
                        }

                        System.out.print("Masukkan password   : ");
                        String regPassword = sc.next();

                        System.out.print("Masukkan pendidikan : ");
                        String regPendidikan = sc.next();

                        int nextId = daftarUsers.size() + 1;
                        Peserta pesertaBaru = new Peserta(nextId, regNama, regEmail, regPassword, regPendidikan);

                        daftarUsers.add(pesertaBaru);
                        System.out.println("Akun berhasil didaftarkan!\n");

                        pilihan = 2;
                       
                        break;

                    case 2:
                        while (true) {
                            System.out.println("======= LOGIN ========");
                            System.out.print("Masukkan email : ");
                            String inputEmail = sc.next();
                            System.out.print("Masukkan password : ");
                            String inputPassword = sc.next();

                            for (User user : daftarUsers) {
                                if (user.getEmail().equalsIgnoreCase(inputEmail) &&
                                        user.authenticate(inputPassword)) {
                                    userLogin = user;
                                    break;
                                }
                            }

                            if (userLogin != null) {
                                System.out.println("Login berhasil!\n");
                            
                                pilihan = 3;
                                break;
                            } else {
                                System.out.println("Email atau password salah.\n");
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Keluar dari program...");
                        break;

                    default:
                        System.out.println("Pilihan tidak valid, coba lagi!\n");
                }
            } while (pilihan != 3 && userLogin == null);

            if (userLogin == null && pilihan == 3) {
                break;
            }

            if (userLogin instanceof Peserta) {
                int pilih;
                do {
                    System.out.println("=== MENU PESERTA ===");
                    System.out.println("1. Melihat Daftar Kursus");
                    System.out.println("2. Pendaftaran Kursus");
                    System.out.println("3. Kursus Saya");
                    System.out.println("4. Cek Saldo");
                    System.out.println("5. Isi Saldo");
                    System.out.println("6. Informasi Peserta");
                    System.out.println("0. Logout");
                    System.out.print("Pilih: ");
                    pilih = sc.nextInt();

                    switch (pilih) {
                        case 1:
                            System.out.println("Menampilkan daftar kursus...");
                            tampilkanKursus(daftarMataPelajaran);
                            break;
                        case 2:
                            while (true) {
                                tampilkanKursus(daftarMataPelajaran);
                                System.out.print("Masukkan ID kursus yang ingin diikuti: ");
                                int idKursus = sc.nextInt();
                                Kursus kursusDiikuti = null;
                                for (Kursus kursus : daftarKursus) {
                                    if (kursus.getId() == idKursus) {
                                        kursusDiikuti = kursus;
                                        break;
                                    }
                                }
                                if (kursusDiikuti == null) {
                                    System.out.println("Kursus tidak ditemukan! ");
                                } else {
                                    ((Peserta) userLogin).pendaftaranKursus(kursusDiikuti);
                                    break;
                                }
                            }
                            break;
                        case 3:
                            Kursus kursusPilihan = null;
                            while (true) {
                                System.out.println("=== Kursus yang diikuti ===");
                                ArrayList<Kursus> kursusPeserta = ((Peserta) userLogin).getDaftarKursus();
                                if (kursusPeserta.isEmpty()) {
                                    System.out.println("Anda belum terdaftar pada kursus apapun!");
                                    break;
                                }
                                for (Kursus kursus : kursusPeserta) {
                                    System.out.println(kursus.getId() + ". " + kursus.getJudul() +
                                            " | Instruktur: " + kursus.getInstruktur().getNama());
                                }
                                System.out.print("Masuk ke kursus (masukkan id): ");
                                int idKursusPilihan = sc.nextInt();
                                for (Kursus kursus : kursusPeserta) {
                                    if (kursus.getId() == idKursusPilihan) {
                                        kursusPilihan = kursus;
                                        break;
                                    }
                                }
                                if (kursusPilihan == null) {
                                    System.out.println("Kursus tidak ditemukan!");
                                } else {
                                    break;
                                }
                            }
                            if (kursusPilihan != null) {
                                tampilkanKonten(kursusPilihan);
                            }
                            break;
                        case 4:
                            System.out.println("=== Cek Saldo User : " + userLogin.getName() + " ===");
                            System.out.println("Saldo : Rp. " + userLogin.getSaldo());
                            break;
                        case 5:
                            System.out.println("=== Isi Saldo ===");
                            int nominal;
                            while (true) {
                                System.out.print("Masukkan Nominal Saldo : ");
                                nominal = sc.nextInt();
                                if (nominal < 0) {
                                    System.out.println("Saldo tidak boleh negatif!");
                                } else {
                                    userLogin.setSaldo(nominal);
                                    break;
                                }
                            }
                            break;
                        case 6:
                            System.out.println("== Informasi User ==");
                            ((Peserta) userLogin).tampilkanInfo();
                            break;
                        case 0:
                            System.out.println("=== Logout ===");
                            break;
                        default:
                            System.out.println("Pilihan tidak valid!");
                    }
                } while (pilih != 0);

                continue;
            } else if (userLogin instanceof Instruktur) {
                int pilih = 0;
                do {
                    System.out.println("=== MENU INSTRUKTUR ===");
                    System.out.println("1. Kursus Saya");
                    System.out.println("2. Cek Saldo");
                    System.out.println("3. Informasi User");
                    System.out.println("4. Tambah Kursus");
                    System.out.println("0. Logout");
                    System.out.print("Pilih: ");
                    pilih = sc.nextInt();

                    switch (pilih) {
                        case 1:
                            System.out.println("=== Kursus Saya ===");
                            ArrayList<Kursus> listKursusInstruktur = ((Instruktur) userLogin).getDaftarKursus();
                            if (listKursusInstruktur.isEmpty()) {
                                System.out.println("Anda belum memiliki kursus.");
                                break;
                            }
                            for (Kursus kursus : listKursusInstruktur) {
                                String infoHarga = kursus.getIsBerbayar() ? "Rp" + kursus.getHarga() : "FREE";
                                System.out.println(
                                        kursus.getId() + ". " + kursus.getJudul() +
                                                " | Instruktur: " + kursus.getInstruktur().getNama() +
                                                " | " + infoHarga);
                            }
                            System.out.print("Masukkan id kursus yang ingin dikelola : ");
                            int idPilihan = sc.nextInt();
                            Kursus kursusDipilih = null;
                            for (Kursus k : listKursusInstruktur) {
                                if (k.getId() == idPilihan) {
                                    kursusDipilih = k;
                                    break;
                                }
                            }
                            if (kursusDipilih != null) {
                                menuKelolaKonten(kursusDipilih, sc);
                            } else {
                                System.out.println("Kursus tidak ditemukan.");
                            }
                            break;

                        case 2:
                            System.out.println("=== Cek Saldo User : " + userLogin.getName() + " ===");
                            System.out.println("Saldo : Rp. " + userLogin.getSaldo());
                            break;

                        case 3:
                            System.out.println("== Informasi User ==");
                            ((Instruktur) userLogin).tampilkanInfo();
                            break;

                        case 4:
                            System.out.println("== Tambah Kursus Baru");
                            sc.nextLine();
                            System.out.print("Judul kursus: ");
                            String judul = sc.nextLine().trim();

                            System.out.println("Pilih Mata Pelajaran (ketik ID):");
                            for (MataPelajaran m : daftarMataPelajaran) {
                                System.out.println(m.getId() + " - " + m.getNama());
                            }

                            MataPelajaran mp = null;
                            while (mp == null) {
                                System.out.print("ID Mapel: ");
                                int idMapel = sc.nextInt();
                                for (MataPelajaran m : daftarMataPelajaran) {
                                    if (m.getId() == idMapel) {
                                        mp = m;
                                    }
                                }
                                if (mp == null)
                                    System.out.println("ID Mapel tidak ditemukan. Coba lagi.");
                            }

                            boolean isBerbayar = false;
                            while (true) {
                                System.out.print("Apakah kursus berbayar? (y/n): ");
                                String yn = sc.next().trim().toLowerCase();
                                if (yn.equals("y")) {
                                    isBerbayar = true;
                                    break;
                                }
                                if (yn.equals("n")) {
                                    isBerbayar = false;
                                    break;
                                }
                                System.out.println("Input harus 'y' atau 'n'.");
                            }

                            int harga = 0;
                            if (isBerbayar) {
                                while (true) {
                                    System.out.print("Harga (>= 0): ");
                                    if (sc.hasNextInt()) {
                                        harga = sc.nextInt();
                                        if (harga >= 0)
                                            break;
                                        System.out.println("Harga tidak boleh negatif. Coba lagi.");
                                    } else {
                                        System.out.println("Masukkan angka yang valid.");
                                        sc.next();
                                    }
                                }
                            }

                            int newId = daftarKursus.size() + 1;
                            Kursus baru = new Kursus(newId, judul, mp, ((Instruktur) userLogin), isBerbayar);
                            baru.setHarga(isBerbayar ? harga : 0);
                            daftarKursus.add(baru);

                            System.out.println("\nKursus berhasil dibuat!");
                            System.out.println("- ID        : " + newId);
                            System.out.println("- Judul     : " + judul);
                            System.out.println("- Mapel     : " + mp.getNama());
                            System.out.println("- Instruktur: " + userLogin.getName());
                            System.out.println("- Berbayar  : " + (isBerbayar ? "Ya" : "Tidak"));
                            if (isBerbayar)
                                System.out.println("- Harga     : " + harga);
                            System.out.println();
                            break;

                        case 0:
                            System.out.println("Logout...");
                            break;

                        default:
                            System.out.println("Pilihan tidak valid, coba lagi!");
                    }
                } while (pilih != 0);

                continue;
            }
        } 
    }

}
