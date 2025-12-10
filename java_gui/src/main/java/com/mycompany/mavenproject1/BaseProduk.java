package com.mycompany.mavenproject1;

/**
 * Abstract class untuk model produk
 * Mendemonstrasikan konsep Abstract Class dan Encapsulation
 */
public abstract class BaseProduk {
    protected String nama;
    protected double harga;
    
    public BaseProduk(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }
    
    // Abstract method untuk menghitung nilai
    public abstract double hitungNilai();
    
    // Concrete methods (Encapsulation)
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
}
