package com.mycompany.mavenproject1;

/**
 * Model class untuk Produk
 * Mendemonstrasikan konsep Encapsulation
 */
public class Produk {
    private String nama;
    private String kategori;
    private double harga;
    private int stok;
    private int jumlah; // jumlah yang dibeli
    
    public Produk(String nama, String kategori, double harga, int stok) {
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;
        this.jumlah = 1;
    }
    
    /**
     * Method untuk menghitung nilai (harga * jumlah)
     */
    public double hitungNilai() {
        return harga * jumlah;
    }
    
    // Getters and Setters (Encapsulation)
    public String getNama() {
        return nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public double getHarga() {
        return harga;
    }
    
    public void setHarga(double harga) {
        this.harga = harga;
    }
    
    public String getKategori() {
        return kategori;
    }
    
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    
    public int getStok() {
        return stok;
    }
    
    public void setStok(int stok) {
        this.stok = stok;
    }
    
    public int getJumlah() {
        return jumlah;
    }
    
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
