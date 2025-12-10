package com.mycompany.mavenproject1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProdukService implements ProdukOperations {
    
    /**
     * Implementasi method dari interface ProdukOperations
     * Mendemonstrasikan Polymorphism
     */
    @Override
    public List<String> loadKategori() {
        List<String> kategoriList = new ArrayList<>();
        kategoriList.add("-- Pilih Kategori --");
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nama_kategori FROM kategori ORDER BY nama_kategori");
            
            while (rs.next()) {
                kategoriList.add(rs.getString("nama_kategori"));
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return kategoriList;
    }
    
    @Override
    public List<String> loadProdukByKategori(String kategori) {
        List<String> produkList = new ArrayList<>();
        produkList.add("-- Pilih Produk --");
        
        if (kategori.equals("-- Pilih Kategori --")) {
            return produkList;
        }
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT p.nama_produk FROM produk p " +
                          "JOIN kategori k ON p.id_kategori = k.id_kategori " +
                          "WHERE k.nama_kategori = ? ORDER BY p.nama_produk";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, kategori);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                produkList.add(rs.getString("nama_produk"));
            }
            
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return produkList;
    }
    
    @Override
    public Produk getProdukDetails(String namaProduk) {
        Produk produk = null;
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT p.nama_produk, k.nama_kategori, p.harga, p.stok " +
                          "FROM produk p JOIN kategori k ON p.id_kategori = k.id_kategori " +
                          "WHERE p.nama_produk = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, namaProduk);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                produk = new Produk(
                    rs.getString("nama_produk"),
                    rs.getString("nama_kategori"),
                    rs.getDouble("harga"),
                    rs.getInt("stok")
                );
            }
            
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return produk;
    }
    
    @Override
    public void updateStok(String namaProduk, int jumlah) throws Exception {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "UPDATE produk SET stok = stok - ? WHERE nama_produk = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, jumlah);
            pstmt.setString(2, namaProduk);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new Exception("Gagal update stok: " + e.getMessage());
        }
    }
}
