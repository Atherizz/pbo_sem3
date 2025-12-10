package com.mycompany.mavenproject1;

/**
 * Aplikasi Kasir Toko dengan Implementasi Konsep OOP
 * - Interface: ProdukOperations, TransaksiOperations
 * - Abstract Class: BasePanel, BaseProduk
 * - Inheritance: InputPanel, TablePanel, ReceiptPanel, InfoPanel extends BasePanel
 *                Produk extends BaseProduk
 * - Polymorphism: Service classes implements interfaces, method overriding
 * - Encapsulation: Private fields dengan getter/setter
 * 
 * @author Atherizz
 */
import javax.swing.*;

public class TokoFrame extends JFrame {
    // Mendemonstrasikan Composition - TokoFrame memiliki panel-panel
    private InputPanel inputPanel;
    private TablePanel tablePanel;
    private ReceiptPanel receiptPanel;
    private InfoPanel infoPanel;
    
    // Mendemonstrasikan Polymorphism - menggunakan interface reference
    private TransaksiOperations transaksiService;
    
    public TokoFrame() {
        super("Aplikasi Kasir Toko - OOP Implementation");
        
        // Dependency Injection & Polymorphism
        this.transaksiService = new TransaksiService();
        
        initializeFrame();
        initializePanels();
        setupEventListeners();
    }
    
    private void initializeFrame() {
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        // Window closing event
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                DatabaseConnection.closeConnection();
            }
        });
    }
    
    private void initializePanels() {
        // Mendemonstrasikan Polymorphism - semua panel adalah BasePanel
        inputPanel = new InputPanel();
        tablePanel = new TablePanel();
        receiptPanel = new ReceiptPanel();
        infoPanel = new InfoPanel();
        
        add(inputPanel);
        add(tablePanel);
        add(receiptPanel);
        add(infoPanel);
    }
    
    private void setupEventListeners() {
        // Event Listener untuk tombol Tambah
        inputPanel.getTambahButton().addActionListener(e -> handleTambahProduk());
        
        // Event Listener untuk tombol Hapus
        inputPanel.getHapusButton().addActionListener(e -> handleHapusProduk());
        
        // Event Listener untuk tombol Cetak & Simpan
        inputPanel.getCetakButton().addActionListener(e -> handleCetakTransaksi());
        
        // Event Listener untuk tombol Reset
        inputPanel.getResetButton().addActionListener(e -> handleReset());
        
        // Event Listener untuk tombol Riwayat
        inputPanel.getRiwayatButton().addActionListener(e -> handleRiwayat());
    }
    
    private void handleTambahProduk() {
        String namaProduk = (String) inputPanel.getProdukComboBox().getSelectedItem();
        String kategori = (String) inputPanel.getKategoriComboBox().getSelectedItem();
        String hargaText = inputPanel.getHargaProdukField().getText().trim();
        int jumlah = (Integer) inputPanel.getJumlahSpinner().getValue();
        
        if (namaProduk == null || namaProduk.equals("-- Pilih Produk --") || hargaText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih produk terlebih dahulu!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            double harga = Double.parseDouble(hargaText);
            double subtotal = harga * jumlah;
            
            int rowCount = tablePanel.getRowCount();
            Object[] row = {
                rowCount + 1,
                namaProduk,
                kategori,
                FormatHelper.formatRupiah(harga),
                jumlah,
                FormatHelper.formatRupiah(subtotal)
            };
            
            tablePanel.addRow(row);
            inputPanel.updateTotal(subtotal);
            
            // Reset
            inputPanel.getJumlahSpinner().setValue(1);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga tidak valid!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleHapusProduk() {
        int selectedRow = tablePanel.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih item yang akan dihapus!", 
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String subtotalText = tablePanel.getValueAt(selectedRow, 5).toString();
        double subtotal = FormatHelper.parseRupiah(subtotalText);
        inputPanel.updateTotal(-subtotal);
        
        tablePanel.removeRow(selectedRow);
        
        // Update nomor urut
        for (int i = 0; i < tablePanel.getRowCount(); i++) {
            tablePanel.updateRowNumber(i, i + 1);
        }
    }
    
    private void handleCetakTransaksi() {
        if (tablePanel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tidak ada item untuk dicetak!", 
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Buat objek Transaksi
        Transaksi transaksi = new Transaksi();
        
        // Tambahkan detail transaksi
        for (int i = 0; i < tablePanel.getRowCount(); i++) {
            String namaProduk = tablePanel.getValueAt(i, 1).toString();
            String kategori = tablePanel.getValueAt(i, 2).toString();
            double harga = FormatHelper.parseRupiah(tablePanel.getValueAt(i, 3).toString());
            int jumlah = Integer.parseInt(tablePanel.getValueAt(i, 4).toString());
            
            DetailTransaksi detail = new DetailTransaksi(namaProduk, kategori, harga, jumlah);
            transaksi.addDetail(detail);
        }
        
        transaksi.hitungTotal();
        
        // Simpan ke database menggunakan Polymorphism
        try {
            int idTransaksi = transaksiService.saveTransaksi(transaksi);
            
            if (idTransaksi > 0) {
                // Generate struk
                String strukText = generateStruk(idTransaksi);
                receiptPanel.setReceiptText(strukText);
                
                JOptionPane.showMessageDialog(this, 
                    "Transaksi berhasil disimpan!\nID Transaksi: #" + idTransaksi, 
                    "Sukses", JOptionPane.INFORMATION_MESSAGE);
                
                // Reset setelah simpan
                resetAll();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Gagal menyimpan transaksi: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String generateStruk(int idTransaksi) {
        StringBuilder struk = new StringBuilder();
        struk.append("=====================================\n");
        struk.append("         TOKO SERBAGUNA\n");
        struk.append("     Jl. Raya Merdeka No. 123\n");
        struk.append("      Telp: (021) 12345678\n");
        struk.append("=====================================\n\n");
        struk.append(String.format("No. Transaksi: #%d\n", idTransaksi));
        struk.append(String.format("Tanggal: %tD %tT\n\n", 
                new java.util.Date(), new java.util.Date()));
        struk.append("-------------------------------------\n");
        struk.append(String.format("%-3s %-15s %5s %10s\n", 
                "No", "Produk", "Qty", "Subtotal"));
        struk.append("-------------------------------------\n");
        
        for (int i = 0; i < tablePanel.getRowCount(); i++) {
            String nama = tablePanel.getValueAt(i, 1).toString();
            if (nama.length() > 15) nama = nama.substring(0, 12) + "...";
            
            struk.append(String.format("%-3s %-15s %5s %10s\n",
                tablePanel.getValueAt(i, 0),
                nama,
                tablePanel.getValueAt(i, 4),
                tablePanel.getValueAt(i, 5)));
        }
        
        struk.append("-------------------------------------\n");
        struk.append(String.format("%-24s %10s\n", "TOTAL BELANJA:", 
                FormatHelper.formatRupiah(inputPanel.getTotalBelanja())));
        struk.append("=====================================\n");
        struk.append("\n  Terima kasih atas kunjungan Anda!\n");
        struk.append("     Transaksi tersimpan di database\n");
        struk.append("=====================================\n");
        
        return struk.toString();
    }
    
    private void handleReset() {
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Apakah Anda yakin ingin mereset semua data?", 
                "Konfirmasi", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            resetAll();
        }
    }
    
    private void resetAll() {
        // Mendemonstrasikan Polymorphism - memanggil method yang di-override
        tablePanel.resetPanel();
        receiptPanel.resetPanel();
        inputPanel.resetPanel();
        inputPanel.getJumlahSpinner().setValue(1);
    }
    
    private void handleRiwayat() {
        // Mendemonstrasikan penggunaan dialog terpisah
        RiwayatDialog dialog = new RiwayatDialog(this);
        dialog.setVisible(true);
    }
    
    public static void main(String[] args) {
        // Test koneksi database
        if (!DatabaseConnection.testConnection()) {
            JOptionPane.showMessageDialog(null, 
                "Gagal terkoneksi ke database!\nPastikan MySQL sudah running dan database 'toko_kasir' sudah dibuat.", 
                "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        SwingUtilities.invokeLater(() -> {
            TokoFrame frame = new TokoFrame();
            frame.setVisible(true);
        });
    }
}
