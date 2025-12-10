package com.mycompany.mavenproject1;

public class DetailTransaksi extends BaseModel {
    private int idTransaksi;
    private String namaProduk;
    private String kategori;
    private double harga;
    private int jumlah;
    private double subtotal;
    
    public DetailTransaksi() {
        super();
    }
    
    public DetailTransaksi(String namaProduk, String kategori, double harga, int jumlah) {
        super();
        this.namaProduk = namaProduk;
        this.kategori = kategori;
        this.harga = harga;
        this.jumlah = jumlah;
        this.subtotal = harga * jumlah;
    }
    
    public DetailTransaksi(int idDetail, int idTransaksi, String namaProduk, 
            String kategori, double harga, int jumlah, double subtotal) {
        super(idDetail);
        this.idTransaksi = idTransaksi;
        this.namaProduk = namaProduk;
        this.kategori = kategori;
        this.harga = harga;
        this.jumlah = jumlah;
        this.subtotal = subtotal;
    }
    
    /**
     * Implementasi abstract method dari BaseModel
     * Mendemonstrasikan Polymorphism
     */
    @Override
    public String getDisplayInfo() {
        return String.format("%s x%d = Rp %.0f", namaProduk, jumlah, subtotal);
    }
    
    // Getters and Setters
    public int getIdDetail() {
        return id; // Using inherited id from BaseModel
    }
    
    public void setIdDetail(int idDetail) {
        this.id = idDetail; // Using inherited id from BaseModel
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
