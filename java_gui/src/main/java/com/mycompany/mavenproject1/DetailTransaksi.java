package com.mycompany.mavenproject1;

/**
 * Model class untuk Detail Transaksi
 * Mendemonstrasikan konsep Encapsulation
 */
public class DetailTransaksi {
    private int idDetail;
    private int idTransaksi;
    private String namaProduk;
    private String kategori;
    private double harga;
    private int jumlah;
    private double subtotal;
    
    public DetailTransaksi() {
    }
    
    public DetailTransaksi(String namaProduk, String kategori, double harga, int jumlah) {
        this.namaProduk = namaProduk;
        this.kategori = kategori;
        this.harga = harga;
        this.jumlah = jumlah;
        this.subtotal = harga * jumlah;
    }
    
    public DetailTransaksi(int idDetail, int idTransaksi, String namaProduk, 
            String kategori, double harga, int jumlah, double subtotal) {
        this.idDetail = idDetail;
        this.idTransaksi = idTransaksi;
        this.namaProduk = namaProduk;
        this.kategori = kategori;
        this.harga = harga;
        this.jumlah = jumlah;
        this.subtotal = subtotal;
    }
    
    // Getters and Setters
    public int getIdDetail() {
        return idDetail;
    }
    
    public void setIdDetail(int idDetail) {
        this.idDetail = idDetail;
    }
    
    public int getIdTransaksi() {
        return idTransaksi;
    }
    
    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }
    
    public String getNamaProduk() {
        return namaProduk;
    }
    
    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }
    
    public String getKategori() {
        return kategori;
    }
    
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    
    public double getHarga() {
        return harga;
    }
    
    public void setHarga(double harga) {
        this.harga = harga;
    }
    
    public int getJumlah() {
        return jumlah;
    }
    
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    
    public double getSubtotal() {
        return subtotal;
    }
    
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
