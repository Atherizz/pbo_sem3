import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void tampilkanKursus(ArrayList<Kursus> daftarKursus) {
        for (Kursus kursus : daftarKursus) {
            String infoHarga = kursus.getIsBerbayar()
                    ? "Rp" + kursus.getHarga()
                    : "FREE";

            System.out.println(
                    kursus.getId() + ". " + kursus.getJudul() +
                            " | Instruktur: " + kursus.getInstruktur().getNama() +
                            " | " + infoHarga);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Peserta peserta1 = new Peserta("budi", "budi@mail.com", "pass1", "S1 Informatika");
        Peserta peserta2 = new Peserta("sari", "sari@mail.com", "pass2", "SMA");
        Peserta peserta3 = new Peserta("doni", "doni@mail.com", "pass3", "S1 Sistem Informasi");
        Peserta peserta4 = new Peserta("lisa", "lisa@mail.com", "pass4", "S2 Teknik Informatika");
        Peserta peserta5 = new Peserta("agus", "agus@mail.com", "pass5", "Diploma TI");

        Instruktur instruktur1 = new Instruktur("andi123", "andi@mail.com", "passA", "Andi", "Pemrograman Java");
        Instruktur instruktur2 = new Instruktur("rani456", "rani@mail.com", "passB", "Rani", "Desain UI/UX");

        ArrayList<User> daftarUsers = new ArrayList<>();
        daftarUsers.add(peserta1);
        daftarUsers.add(peserta2);
        daftarUsers.add(peserta3);
        daftarUsers.add(peserta4);
        daftarUsers.add(peserta5);
        daftarUsers.add(instruktur1);
        daftarUsers.add(instruktur2);

        ArrayList<Kursus> daftarKursus = new ArrayList<>();

        daftarKursus.add(new Kursus(1, "Belajar Java Dasar", new MataPelajaran("Java Dasar"), instruktur1, true));
        daftarKursus.add(new Kursus(2, "Belajar Java Lanjutan", new MataPelajaran("Java Lanjutan"), instruktur1, true));
        daftarKursus.add(new Kursus(3, "UI/UX untuk Pemula", new MataPelajaran("UI/UX Dasar"), instruktur2, false));
        daftarKursus
                .add(new Kursus(4, "Membangun Mobile App", new MataPelajaran("Desain Mobile App"), instruktur2, true));
        daftarKursus
                .add(new Kursus(5, "Database MySQL Praktis", new MataPelajaran("Database MySQL"), instruktur1, true));
        daftarKursus.add(new Kursus(6, "Algoritma & Pemrograman", new MataPelajaran("Algoritma Pemrograman"),
                instruktur1, false));
        daftarKursus
                .add(new Kursus(7, "Frontend dengan React", new MataPelajaran("React Frontend"), instruktur2, true));
        daftarKursus.add(new Kursus(8, "Backend dengan Spring Boot", new MataPelajaran("Spring Boot Backend"),
                instruktur1, true));
        daftarKursus.add(new Kursus(9, "Machine Learning Dasar", new MataPelajaran("Machine Learning Dasar"),
                instruktur1, true));
        daftarKursus
                .add(new Kursus(10, "UI/UX Prototyping", new MataPelajaran("UI/UX Prototyping"), instruktur2, true));

        daftarKursus.get(0).setHarga(500000);
        daftarKursus.get(1).setHarga(750000);
        daftarKursus.get(2).setHarga(0);
        daftarKursus.get(3).setHarga(400000);
        daftarKursus.get(4).setHarga(600000);
        daftarKursus.get(5).setHarga(0);
        daftarKursus.get(6).setHarga(550000);
        daftarKursus.get(7).setHarga(800000);
        daftarKursus.get(8).setHarga(1000000);
        daftarKursus.get(9).setHarga(450000);

        Kursus k1 = daftarKursus.get(0);
        Video c1 = new Video("Video: Intro Java", k1, "Pengenalan Java & tooling", 600);
        k1.getDaftarKonten().add(c1);

        Kursus k2 = daftarKursus.get(1);
        Kuis c2 = new Kuis("Kuis: OOP Dasar", k2, "Uji konsep class, object, dan encapsulation", 12);
        k2.getDaftarKonten().add(c2);

        Kursus k3 = daftarKursus.get(2);
        Artikel c3 = new Artikel("Artikel: Prinsip UI/UX", k3, "Heuristics dan user flow dasar",
                k3.getInstruktur().getNama());
        k3.getDaftarKonten().add(c3);

        Kursus k4 = daftarKursus.get(3);
        Video c4 = new Video("Video: Layout Mobile Grid", k4, "Grid system & spacing scale", 630);
        k4.getDaftarKonten().add(c4);

        Kursus k5 = daftarKursus.get(4);
        Artikel c5 = new Artikel("Artikel: Indexing MySQL", k5, "EXPLAIN, index, dan optimasi query",
                k5.getInstruktur().getNama());
        k5.getDaftarKonten().add(c5);

        Kursus k6 = daftarKursus.get(5);
        Kuis c6 = new Kuis("Kuis: Algoritma Dasar", k6, "Seleksi, perulangan, array sederhana", 15);
        k6.getDaftarKonten().add(c6);

        Kursus k7 = daftarKursus.get(6);
        Video c7 = new Video("Video: React Hooks 101", k7, "useState, useEffect, lifting state up", 700);
        k7.getDaftarKonten().add(c7);

        Kursus k8 = daftarKursus.get(7);
        Artikel c8 = new Artikel("Artikel: REST API Spring Boot", k8, "Controller, service, repository pattern",
                k8.getInstruktur().getNama());
        k8.getDaftarKonten().add(c8);

        Kursus k9 = daftarKursus.get(8);
        Video c9 = new Video("Video: ML untuk Pemula", k9, "Supervised vs unsupervised, dataset & metric", 900);
        k9.getDaftarKonten().add(c9);

        Kursus k10 = daftarKursus.get(9);
        Kuis c10 = new Kuis("Kuis: Prototyping UI/UX", k10, "Wireframe, flow, dan usability", 8);
        k10.getDaftarKonten().add(c10);

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

                    Peserta pesertaBaru = new Peserta(regNama, regEmail, regPassword, regPendidikan);

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
        } while (pilihan != 3);

        if (userLogin instanceof Peserta) {
            int pilih;
            do {
                System.out.println("=== MENU PESERTA ===");
                System.out.println("1. Melihat Daftar Kursus");
                System.out.println("2. Pendaftaran Kursus");
                System.out.println("3. Kursus Saya");
                System.out.println("4. Cek Saldo");
                System.out.println("5. Isi Saldo");
                System.out.println("0. Logout");
                System.out.print("Pilih: ");
                pilih = sc.nextInt();

                switch (pilih) {
                    case 1:
                        System.out.println("Menampilkan daftar kursus...");
                        tampilkanKursus(daftarKursus);
                        break;
                    case 2:
                        while (true) {
                            tampilkanKursus(daftarKursus);
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
                            System.out.println("Kursus yang diikuti....");
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
                            ArrayList<Konten> daftarKonten = kursusPilihan.getDaftarKonten();
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

                        break;
                    case 4:
                        System.out.println("Cek Saldo User : " + userLogin.getName());
                        System.out.println("Saldo : Rp. " + userLogin.getSaldo());
                        break;
                    case 5:
                        System.out.println("Isi Saldo");
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
                    case 0:
                        System.out.println("Logout...");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } while (pilih != 0);

        } else if (userLogin instanceof Instruktur) {
            System.out.println("=== MENU INSTRUKTUR ===");

        }

        sc.close();
    }

}
