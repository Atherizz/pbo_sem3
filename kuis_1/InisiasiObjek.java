import java.util.ArrayList;

public class InisiasiObjek {
    public static void initiateObject(ArrayList<Kursus> daftarKursus, ArrayList<Konten> daftarKonten,
            ArrayList<MataPelajaran> daftarMataPelajaran, ArrayList<User> daftarUsers) {
        // --- Peserta ---
        Peserta peserta1 = new Peserta(1, "budi", "budi@mail.com", "pass1", "S1 Informatika");
        Peserta peserta2 = new Peserta(2, "sari", "sari@mail.com", "pass2", "SMA");
        Peserta peserta3 = new Peserta(3, "doni", "doni@mail.com", "pass3", "S1 Sistem Informasi");
        Peserta peserta4 = new Peserta(4, "lisa", "lisa@mail.com", "pass4", "S2 Teknik Informatika");
        Peserta peserta5 = new Peserta(5, "agus", "agus@mail.com", "pass5", "Diploma TI");

        // --- Instruktur ---
        Instruktur instruktur1 = new Instruktur(6, "andi123", "andi@mail.com", "passA", "Andi", "Pemrograman Java");
        Instruktur instruktur2 = new Instruktur(7, "rani456", "rani@mail.com", "passB", "Rani", "Desain UI/UX");

        daftarUsers.add(peserta1);
        daftarUsers.add(peserta2);
        daftarUsers.add(peserta3);
        daftarUsers.add(peserta4);
        daftarUsers.add(peserta5);
        daftarUsers.add(instruktur1);
        daftarUsers.add(instruktur2);

        MataPelajaran mp1 = new MataPelajaran(1, "Java");
        MataPelajaran mp2 = new MataPelajaran(2, "UI/UX");
        MataPelajaran mp3 = new MataPelajaran(3, "Mobile Development");
        MataPelajaran mp4 = new MataPelajaran(4, "Database MySQL");
        MataPelajaran mp5 = new MataPelajaran(5, "Machine Learning");

        daftarMataPelajaran.add(mp1);
        daftarMataPelajaran.add(mp2);
        daftarMataPelajaran.add(mp3);
        daftarMataPelajaran.add(mp4);
        daftarMataPelajaran.add(mp5);

        // Java
        Kursus k1 = new Kursus(1, "Belajar Java Dasar", mp1, instruktur1, true);
        Kursus k2 = new Kursus(2, "Belajar Java Lanjutan", mp1, instruktur1, true);
        mp1.tambahKursus(k1); mp1.tambahKursus(k2);

        // UI/UX
        Kursus k3 = new Kursus(3, "UI/UX untuk Pemula", mp2, instruktur2, false);
        Kursus k4 = new Kursus(4, "UI/UX Prototyping", mp2, instruktur2, true);
        mp2.tambahKursus(k3); mp2.tambahKursus(k4);

        // Mobile Development
        Kursus k5 = new Kursus(5, "Dasar Pengembangan Mobile", mp3, instruktur2, true);
        Kursus k6 = new Kursus(6, "Kotlin Android Pemula", mp3, instruktur2, false);
        mp3.tambahKursus(k5); mp3.tambahKursus(k6);

        // Database MySQL
        Kursus k7 = new Kursus(7, "Database MySQL Praktis", mp4, instruktur1, true);
        Kursus k8 = new Kursus(8, "Optimasi Query MySQL", mp4, instruktur1, true);
        mp4.tambahKursus(k7); mp4.tambahKursus(k8);

        // Machine Learning
        Kursus k9 = new Kursus(9, "Machine Learning Dasar", mp5, instruktur1, true);
        Kursus k10 = new Kursus(10, "Evaluasi Model ML", mp5, instruktur1, true);
        mp5.tambahKursus(k9); mp5.tambahKursus(k10);
        // === Hubungkan ke instruktur + daftarkan ke daftarKursus (global) ===
        // Andi (instruktur1)
        instruktur1.tambahKursus(k1);
        daftarKursus.add(k1);
        instruktur1.tambahKursus(k2);
        daftarKursus.add(k2);
        instruktur1.tambahKursus(k7);
        daftarKursus.add(k7);
        instruktur1.tambahKursus(k8);
        daftarKursus.add(k8);
        instruktur1.tambahKursus(k9);
        daftarKursus.add(k9);
        instruktur1.tambahKursus(k10);
        daftarKursus.add(k10);

        // Rani (instruktur2)
        instruktur2.tambahKursus(k3);
        daftarKursus.add(k3);
        instruktur2.tambahKursus(k4);
        daftarKursus.add(k4);
        instruktur2.tambahKursus(k5);
        daftarKursus.add(k5);
        instruktur2.tambahKursus(k6);
        daftarKursus.add(k6);

        daftarKursus.add(k1);
        daftarKursus.add(k2);
        daftarKursus.add(k3);
        daftarKursus.add(k4);
        daftarKursus.add(k5);
        daftarKursus.add(k6);
        daftarKursus.add(k7);
        daftarKursus.add(k8);
        daftarKursus.add(k9);
        daftarKursus.add(k10);

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

        int nextKontenId = 1;

// --- Java (mp1): k1, k2 ---
Video v1 = new Video(nextKontenId++, "Video: Intro Java", k1, "Pengenalan Java & tooling", 600);
k1.getDaftarKonten().add(v1); daftarKonten.add(v1);

Kuis q1 = new Kuis(nextKontenId++, "Kuis: Variabel & Tipe Data", k1, "Dasar tipe data & operator", 12);
k1.getDaftarKonten().add(q1); daftarKonten.add(q1);

Artikel a2 = new Artikel(nextKontenId++, "Artikel: OOP Lanjutan", k2, "Inheritance, polymorphism, abstraction", k2.getInstruktur().getNama());
k2.getDaftarKonten().add(a2); daftarKonten.add(a2);

Video v2 = new Video(nextKontenId++, "Video: Collections & Generics", k2, "List, Map, Set, generic type", 720);
k2.getDaftarKonten().add(v2); daftarKonten.add(v2);

// --- UI/UX (mp2): k3, k4 ---
Artikel a3 = new Artikel(nextKontenId++, "Artikel: Heuristics Nielsen", k3, "10 heuristics & contoh", k3.getInstruktur().getNama());
k3.getDaftarKonten().add(a3); daftarKonten.add(a3);

Video v3 = new Video(nextKontenId++, "Video: User Flow Dasar", k3, "Task flow & wireframe low-fidelity", 540);
k3.getDaftarKonten().add(v3); daftarKonten.add(v3);

Video v4 = new Video(nextKontenId++, "Video: Wireframe ke Prototype", k4, "Low-fi → hi-fi di Figma", 700);
k4.getDaftarKonten().add(v4); daftarKonten.add(v4);

Kuis q4 = new Kuis(nextKontenId++, "Kuis: Usability Metrics", k4, "SUS, task success, time-on-task", 8);
k4.getDaftarKonten().add(q4); daftarKonten.add(q4);

// --- Mobile Development (mp3): k5, k6 ---
Video v5 = new Video(nextKontenId++, "Video: Arsitektur Aplikasi Mobile", k5, "Activity/Fragment, MVVM", 660);
k5.getDaftarKonten().add(v5); daftarKonten.add(v5);

Artikel a5 = new Artikel(nextKontenId++, "Artikel: Responsive Layout", k5, "Density, constraint, adaptive UI", k5.getInstruktur().getNama());
k5.getDaftarKonten().add(a5); daftarKonten.add(a5);

Video v6 = new Video(nextKontenId++, "Video: Kotlin Dasar", k6, "Syntax, nullable, when", 620);
k6.getDaftarKonten().add(v6); daftarKonten.add(v6);

Kuis q6 = new Kuis(nextKontenId++, "Kuis: Kotlin Fundamentals", k6, "Null-safety, collections", 10);
k6.getDaftarKonten().add(q6); daftarKonten.add(q6);

// --- Database MySQL (mp4): k7, k8 ---
Artikel a7 = new Artikel(nextKontenId++, "Artikel: Normalisasi Singkat", k7, "1NF–3NF & functional dependency", k7.getInstruktur().getNama());
k7.getDaftarKonten().add(a7); daftarKonten.add(a7);

Video v7 = new Video(nextKontenId++, "Video: JOINs Praktis", k7, "INNER/LEFT/RIGHT/FULL (simulasi)", 780);
k7.getDaftarKonten().add(v7); daftarKonten.add(v7);

Artikel a8 = new Artikel(nextKontenId++, "Artikel: Index & EXPLAIN", k8, "BTREE, cover index, query plan", k8.getInstruktur().getNama());
k8.getDaftarKonten().add(a8); daftarKonten.add(a8);

Video v8 = new Video(nextKontenId++, "Video: Optimasi Query", k8, "Profiling, slow query log", 720);
k8.getDaftarKonten().add(v8); daftarKonten.add(v8);

// --- Machine Learning (mp5): k9, k10 ---
Video v9 = new Video(nextKontenId++, "Video: Supervised vs Unsupervised", k9, "Task, dataset, metric", 900);
k9.getDaftarKonten().add(v9); daftarKonten.add(v9);

Artikel a9 = new Artikel(nextKontenId++, "Artikel: Data Splitting & Leakage", k9, "Train/val/test, stratified split", k9.getInstruktur().getNama());
k9.getDaftarKonten().add(a9); daftarKonten.add(a9);

Artikel a10 = new Artikel(nextKontenId++, "Artikel: Evaluasi Model", k10, "Accuracy vs F1, ROC-AUC", k10.getInstruktur().getNama());
k10.getDaftarKonten().add(a10); daftarKonten.add(a10);

Video v10 = new Video(nextKontenId++, "Video: Confusion Matrix", k10, "TP/FP/TN/FN & interpretasi", 660);
k10.getDaftarKonten().add(v10); daftarKonten.add(v10);


        
    }
}
