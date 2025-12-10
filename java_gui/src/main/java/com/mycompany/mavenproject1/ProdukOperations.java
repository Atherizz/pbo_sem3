package com.mycompany.mavenproject1;

import java.util.List;

/**
 * Interface untuk operasi produk
 */
public interface ProdukOperations {

    List<String> loadKategori();
    List<String> loadProdukByKategori(String kategori);
    Produk getProdukDetails(String namaProduk);
    void updateStok(String namaProduk, int jumlah) throws Exception;
}
