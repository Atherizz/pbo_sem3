package com.mycompany.mavenproject1;

/**
 * Model class untuk Produk
 * Mendemonstrasikan konsep Inheritance (extends BaseProduk) dan Encapsulation
 */
public class Produk extends BaseProduk {
    private String kategori;
    private int stok;
    private int jumlah; // jumlah yang dibeli
    
    public Produk(String nama, String kategori, double harga, int stok) {
        super(nama, harga);
        this.kategori = kategori;
        this.stok = stok;
        this.jumlah = 1;
    }
    
    /**
     * Implementasi abstract method dari BaseProduk
     * Mendemonstrasikan Polymorphism
     */
    @Override
    public double hitungNilai() {
        return harga * jumlah;
    }
    
    // Getters and Setters (Encapsulation)
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
