package com.mycompany.mavenproject1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class untuk operasi Transaksi
 * Mendemonstrasikan konsep Interface Implementation dan Polymorphism
 */
public class TransaksiService implements TransaksiOperations {
    private ProdukOperations produkService;
    
    public TransaksiService() {
        // Dependency Injection - Polymorphism
        this.produkService = new ProdukService();
    }
    
    /**
     * Implementasi method dari interface TransaksiOperations
     * Mendemonstrasikan Polymorphism
     */
    @Override
    public int saveTransaksi(Transaksi transaksi) throws Exception {
        int idTransaksi = -1;
        Connection conn = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction
            
            // Insert ke tabel transaksi
            String queryTransaksi = "INSERT INTO transaksi (total_belanja, jumlah_item) VALUES (?, ?)";
            PreparedStatement pstmtTransaksi = conn.prepareStatement(queryTransaksi, 
                    Statement.RETURN_GENERATED_KEYS);
            pstmtTransaksi.setDouble(1, transaksi.getTotalBelanja());
            pstmtTransaksi.setInt(2, transaksi.getJumlahItem());
            pstmtTransaksi.executeUpdate();
            
            // Dapatkan ID transaksi yang baru dibuat
            ResultSet rs = pstmtTransaksi.getGeneratedKeys();
            if (rs.next()) {
                idTransaksi = rs.getInt(1);
            }
            rs.close();
            pstmtTransaksi.close();
            
            // Insert ke tabel detail_transaksi
            String queryDetail = "INSERT INTO detail_transaksi " +
                    "(id_transaksi, nama_produk, kategori, harga, jumlah, subtotal) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmtDetail = conn.prepareStatement(queryDetail);
            
            for (DetailTransaksi detail : transaksi.getDetailList()) {
                pstmtDetail.setInt(1, idTransaksi);
                pstmtDetail.setString(2, detail.getNamaProduk());
                pstmtDetail.setString(3, detail.getKategori());
                pstmtDetail.setDouble(4, detail.getHarga());
                pstmtDetail.setInt(5, detail.getJumlah());
                pstmtDetail.setDouble(6, detail.getSubtotal());
                pstmtDetail.addBatch();
                
                // Update stok produk menggunakan polymorphism
                produkService.updateStok(detail.getNamaProduk(), detail.getJumlah());
            }
            
            pstmtDetail.executeBatch();
            pstmtDetail.close();
            
            conn.commit(); // Commit transaction
            conn.setAutoCommit(true);
            
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback jika error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw new Exception("Gagal menyimpan transaksi: " + e.getMessage());
        }
        
        return idTransaksi;
    }
    
    @Override
    public List<Transaksi> getRiwayatTransaksi(int limit) {
        List<Transaksi> transaksiList = new ArrayList<>();
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT * FROM transaksi ORDER BY tanggal DESC LIMIT ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, limit);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Transaksi transaksi = new Transaksi(
                    rs.getInt("id_transaksi"),
                    rs.getTimestamp("tanggal"),
                    rs.getDouble("total_belanja"),
                    rs.getInt("jumlah_item")
                );
                transaksiList.add(transaksi);
            }
            
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return transaksiList;
    }
    
    @Override
    public List<DetailTransaksi> getDetailTransaksi(int idTransaksi) {
        List<DetailTransaksi> detailList = new ArrayList<>();
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT * FROM detail_transaksi WHERE id_transaksi = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idTransaksi);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                DetailTransaksi detail = new DetailTransaksi(
                    rs.getInt("id_detail"),
                    rs.getInt("id_transaksi"),
                    rs.getString("nama_produk"),
                    rs.getString("kategori"),
                    rs.getDouble("harga"),
                    rs.getInt("jumlah"),
                    rs.getDouble("subtotal")
                );
                detailList.add(detail);
            }
            
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return detailList;
    }
}
