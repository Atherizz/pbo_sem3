package com.mycompany.mavenproject1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Model class untuk Transaksi
 * Mendemonstrasikan konsep Encapsulation dan Composition
 */
public class Transaksi {
    private int idTransaksi;
    private Date tanggal;
    private double totalBelanja;
    private int jumlahItem;
    private List<DetailTransaksi> detailList;
    
    public Transaksi() {
        this.detailList = new ArrayList<>();
        this.tanggal = new Date();
    }
    
    public Transaksi(int idTransaksi, Date tanggal, double totalBelanja, int jumlahItem) {
        this.idTransaksi = idTransaksi;
        this.tanggal = tanggal;
        this.totalBelanja = totalBelanja;
        this.jumlahItem = jumlahItem;
        this.detailList = new ArrayList<>();
    }
    
    public void addDetail(DetailTransaksi detail) {
        detailList.add(detail);
    }
    
    public void hitungTotal() {
        totalBelanja = 0;
        for (DetailTransaksi detail : detailList) {
            totalBelanja += detail.getSubtotal();
        }
        jumlahItem = detailList.size();
    }
    
    // Getters and Setters
    public int getIdTransaksi() {
        return idTransaksi;
    }
    
    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }
    
    public Date getTanggal() {
        return tanggal;
    }
    
    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
    
    public double getTotalBelanja() {
        return totalBelanja;
    }
    
    public void setTotalBelanja(double totalBelanja) {
        this.totalBelanja = totalBelanja;
    }
    
    public int getJumlahItem() {
        return jumlahItem;
    }
    
    public void setJumlahItem(int jumlahItem) {
        this.jumlahItem = jumlahItem;
    }
    
    public List<DetailTransaksi> getDetailList() {
        return detailList;
    }
    
    public void setDetailList(List<DetailTransaksi> detailList) {
        this.detailList = detailList;
    }
}
