package com.mycompany.mavenproject1;

import java.util.List;

/**
 * Interface untuk operasi transaksi
 * Mendemonstrasikan konsep Interface dalam OOP
 */
public interface TransaksiOperations {
    /**
     * Menyimpan transaksi ke database
     */
    int saveTransaksi(Transaksi transaksi) throws Exception;
    
    /**
     * Mendapatkan riwayat transaksi
     */
    List<Transaksi> getRiwayatTransaksi(int limit);
    
    /**
     * Mendapatkan detail transaksi berdasarkan ID
     */
    List<DetailTransaksi> getDetailTransaksi(int idTransaksi);
}
