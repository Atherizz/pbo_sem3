package com.mycompany.mavenproject1;

public class Produk extends BaseModel {
    private String nama;
    private String kategori;
    private double harga;
    private int stok;
    private int jumlah; 
    
    public Produk(String nama, String kategori, double harga, int stok) {
        super(); 
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;
        this.jumlah = 1;
    }
    

    public double hitungNilai() {
        return harga * jumlah;
    }
    
    @Override
    public String getDisplayInfo() {
        return String.format("%s - %s (Rp %.0f)", nama, kategori, harga);
    }

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
