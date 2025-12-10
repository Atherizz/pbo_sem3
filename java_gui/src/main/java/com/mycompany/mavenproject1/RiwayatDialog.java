package com.mycompany.mavenproject1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Dialog untuk menampilkan riwayat transaksi
 */
public class RiwayatDialog extends JDialog {
    private TransaksiOperations transaksiService;
    
    public RiwayatDialog(JFrame parent) {
        super(parent, "Riwayat Transaksi", true);
        this.transaksiService = new TransaksiService(); // Polymorphism
        
        setSize(900, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        
        initComponents();
    }
    
    private void initComponents() {
        // Table untuk riwayat
        String[] columns = {"ID", "Tanggal", "Total Belanja", "Jumlah Item", "Detail"};
        DefaultTableModel riwayatModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable riwayatTable = new JTable(riwayatModel);
        
        // Load data transaksi
        List<Transaksi> transaksiList = transaksiService.getRiwayatTransaksi(50);
        for (Transaksi transaksi : transaksiList) {
            Object[] row = {
                transaksi.getIdTransaksi(),
                transaksi.getTanggal(),
                FormatHelper.formatRupiah(transaksi.getTotalBelanja()),
                transaksi.getJumlahItem(),
                "Lihat Detail"
            };
            riwayatModel.addRow(row);
        }
        
        JScrollPane scrollPane = new JScrollPane(riwayatTable);
        add(scrollPane, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton detailButton = new JButton("Lihat Detail");
        JButton closeButton = new JButton("Tutup");
        
        detailButton.addActionListener(e -> {
            int selectedRow = riwayatTable.getSelectedRow();
            if (selectedRow != -1) {
                int idTransaksi = (int) riwayatModel.getValueAt(selectedRow, 0);
                showDetailTransaksi(idTransaksi);
            } else {
                JOptionPane.showMessageDialog(this, "Pilih transaksi terlebih dahulu!");
            }
        });
        
        closeButton.addActionListener(e -> dispose());
        
        buttonPanel.add(detailButton);
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void showDetailTransaksi(int idTransaksi) {
        JDialog detailDialog = new JDialog(this, "Detail Transaksi #" + idTransaksi, true);
        detailDialog.setSize(700, 400);
        detailDialog.setLocationRelativeTo(this);
        detailDialog.setLayout(new BorderLayout());
        
        String[] columns = {"No", "Produk", "Kategori", "Harga", "Jumlah", "Subtotal"};
        DefaultTableModel detailModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable detailTable = new JTable(detailModel);
        
        List<DetailTransaksi> detailList = transaksiService.getDetailTransaksi(idTransaksi);
        int no = 1;
        for (DetailTransaksi detail : detailList) {
            Object[] row = {
                no++,
                detail.getNamaProduk(),
                detail.getKategori(),
                FormatHelper.formatRupiah(detail.getHarga()),
                detail.getJumlah(),
                FormatHelper.formatRupiah(detail.getSubtotal())
            };
            detailModel.addRow(row);
        }
        
        JScrollPane scrollPane = new JScrollPane(detailTable);
        detailDialog.add(scrollPane, BorderLayout.CENTER);
        
        JButton closeButton = new JButton("Tutup");
        closeButton.addActionListener(e -> detailDialog.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        detailDialog.add(buttonPanel, BorderLayout.SOUTH);
        
        detailDialog.setVisible(true);
    }
}
