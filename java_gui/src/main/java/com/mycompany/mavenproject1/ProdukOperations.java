package com.mycompany.mavenproject1;

import java.util.List;

/**
 * Interface untuk operasi produk
 * Mendemonstrasikan konsep Interface dalam OOP
 */
public interface ProdukOperations {
    /**
     * Memuat daftar kategori dari database
     */
    List<String> loadKategori();
    
    /**
     * Memuat daftar produk berdasarkan kategori
     */
    List<String> loadProdukByKategori(String kategori);
    
    /**
     * Mendapatkan detail produk
     */
    Produk getProdukDetails(String namaProduk);
    
    /**
     * Update stok produk
     */
    void updateStok(String namaProduk, int jumlah) throws Exception;
}
