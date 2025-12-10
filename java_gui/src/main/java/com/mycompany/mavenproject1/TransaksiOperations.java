package com.mycompany.mavenproject1;

import java.util.List;


public interface TransaksiOperations {

    int saveTransaksi(Transaksi transaksi) throws Exception;
    List<Transaksi> getRiwayatTransaksi(int limit);
    List<DetailTransaksi> getDetailTransaksi(int idTransaksi);
}
