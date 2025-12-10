package com.mycompany.mavenproject1;

/**
 * Aplikasi Kasir Toko dengan Implementasi Konsep OOP
 * - Interface: ProdukOperations, TransaksiOperations
 * - Inheritance: Produk, Transaksi, DetailTransaksi
 * - Polymorphism: Service classes implements interfaces
 * - Encapsulation: Private fields dengan getter/setter
 * 
 * @author Atherizz
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TokoFrame extends JFrame {
    // UI Components
    private JComboBox<String> kategoriComboBox;
    private JComboBox<String> produkComboBox;
    private JTextField hargaProdukField;
    private JSpinner jumlahSpinner;
    private JLabel stokLabel;
    private JLabel totalLabel;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextArea receiptArea;
    
    // Business Logic - Mendemonstrasikan Polymorphism
    private ProdukOperations produkService;
    private TransaksiOperations transaksiService;
    private double totalBelanja = 0;
    
    public TokoFrame() {
        super("Aplikasi Kasir Toko - OOP Implementation");
        
        // Dependency Injection & Polymorphism
        this.produkService = new ProdukService();
        this.transaksiService = new TransaksiService();
        
        initializeFrame();
        initializeComponents();
        setupEventListeners();
    }
    
    private void initializeFrame() {
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                DatabaseConnection.closeConnection();
            }
        });
    }
    
    private void initializeComponents() {
        createInputPanel();
        createTablePanel();
        createReceiptPanel();
        createInfoPanel();
    }
    
    private void createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(10, 10, 420, 400);
        inputPanel.setLayout(null);
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Produk"));
        add(inputPanel);

        // Kategori
        JLabel kategoriLabel = new JLabel("Kategori:");
        kategoriLabel.setBounds(10, 30, 100, 25);
        inputPanel.add(kategoriLabel);
        
        kategoriComboBox = new JComboBox<>();
        kategoriComboBox.setBounds(120, 30, 280, 25);
        inputPanel.add(kategoriComboBox);
        loadKategori();
        
        // Produk
        JLabel produkLabel = new JLabel("Produk:");
        produkLabel.setBounds(10, 70, 100, 25);
        inputPanel.add(produkLabel);
        
        produkComboBox = new JComboBox<>();
        produkComboBox.setBounds(120, 70, 280, 25);
        inputPanel.add(produkComboBox);
        
        // Harga
        JLabel hargaProdukLabel = new JLabel("Harga:");
        hargaProdukLabel.setBounds(10, 110, 100, 25);
        inputPanel.add(hargaProdukLabel);

        hargaProdukField = new JTextField();
        hargaProdukField.setBounds(120, 110, 280, 25);
        hargaProdukField.setEditable(false);
        hargaProdukField.setBackground(Color.LIGHT_GRAY);
        inputPanel.add(hargaProdukField);
        
        // Jumlah
        JLabel jumlahLabel = new JLabel("Jumlah:");
        jumlahLabel.setBounds(10, 150, 100, 25);
        inputPanel.add(jumlahLabel);
        
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 100, 1);
        jumlahSpinner = new JSpinner(spinnerModel);
        jumlahSpinner.setBounds(120, 150, 100, 25);
        inputPanel.add(jumlahSpinner);
        
        stokLabel = new JLabel("Stok tersedia: -");
        stokLabel.setBounds(230, 150, 170, 25);
        stokLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        inputPanel.add(stokLabel);

        // Buttons
        JButton tambahButton = new JButton("Tambah ke Keranjang");
        tambahButton.setBounds(10, 190, 180, 30);
        tambahButton.setBackground(new Color(46, 204, 113));
        tambahButton.setForeground(Color.WHITE);
        tambahButton.addActionListener(e -> handleTambahProduk());
        inputPanel.add(tambahButton);

        JButton hapusButton = new JButton("Hapus Item");
        hapusButton.setBounds(200, 190, 120, 30);
        hapusButton.setBackground(new Color(231, 76, 60));
        hapusButton.setForeground(Color.WHITE);
        hapusButton.addActionListener(e -> handleHapusProduk());
        inputPanel.add(hapusButton);

        JButton cetakButton = new JButton("Cetak & Simpan");
        cetakButton.setBounds(10, 230, 150, 30);
        cetakButton.setBackground(new Color(52, 152, 219));
        cetakButton.setForeground(Color.WHITE);
        cetakButton.addActionListener(e -> handleCetakTransaksi());
        inputPanel.add(cetakButton);
        
        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(170, 230, 150, 30);
        resetButton.setBackground(new Color(149, 165, 166));
        resetButton.setForeground(Color.WHITE);
        resetButton.addActionListener(e -> handleReset());
        inputPanel.add(resetButton);
        
        JButton riwayatButton = new JButton("Riwayat Transaksi");
        riwayatButton.setBounds(10, 270, 310, 30);
        riwayatButton.setBackground(new Color(155, 89, 182));
        riwayatButton.setForeground(Color.WHITE);
        riwayatButton.addActionListener(e -> handleRiwayat());
        inputPanel.add(riwayatButton);
        
        // Total Label
        totalLabel = new JLabel("TOTAL: Rp 0");
        totalLabel.setBounds(10, 310, 300, 30);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setForeground(new Color(192, 57, 43));
        inputPanel.add(totalLabel);
    }
    
    private void createTablePanel() {
        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(440, 10, 500, 400);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Daftar Belanja"));
        add(tablePanel);
        
        String[] columnNames = {"No", "Nama Produk", "Kategori", "Harga", "Jumlah", "Subtotal"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        
        JScrollPane tableScrollPane = new JScrollPane(table);
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
    }
    
    private void createReceiptPanel() {
        JPanel receiptPanel = new JPanel();
        receiptPanel.setBounds(950, 10, 390, 700);
        receiptPanel.setLayout(new BorderLayout());
        receiptPanel.setBorder(BorderFactory.createTitledBorder("Struk Belanja"));
        add(receiptPanel);
        
        receiptArea = new JTextArea();
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane receiptScrollPane = new JScrollPane(receiptArea);
        receiptPanel.add(receiptScrollPane, BorderLayout.CENTER);
    }
    
    private void createInfoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setBounds(10, 420, 930, 290);
        infoPanel.setLayout(null);
        infoPanel.setBorder(BorderFactory.createTitledBorder("Informasi"));
        add(infoPanel);
        
        JLabel infoLabel = new JLabel("<html><b>TOKO SERBAGUNA</b><br>" +
                "Jl. Raya Merdeka No. 123<br>" +
                "Telp: (021) 12345678<br><br>" +
                "<i>Terima kasih atas kunjungan Anda!</i><br><br>" +
                "<font color='blue'>✓ Terintegrasi dengan Database MySQL</font></html>");
        infoLabel.setBounds(10, 20, 400, 150);
        infoPanel.add(infoLabel);
    }
    
    private void setupEventListeners() {
        kategoriComboBox.addActionListener(e -> {
            String selectedKategori = (String) kategoriComboBox.getSelectedItem();
            if (selectedKategori != null) {
                loadProdukByKategori(selectedKategori);
            }
        });
        
        produkComboBox.addActionListener(e -> {
            String selectedProduk = (String) produkComboBox.getSelectedItem();
            if (selectedProduk != null && !selectedProduk.equals("-- Pilih Produk --")) {
                loadProdukDetails(selectedProduk);
            }
        });
    }
    
    private void loadKategori() {
        kategoriComboBox.removeAllItems();
        List<String> kategoriList = produkService.loadKategori();
        for (String kategori : kategoriList) {
            kategoriComboBox.addItem(kategori);
        }
    }
    
    private void loadProdukByKategori(String kategori) {
        produkComboBox.removeAllItems();
        hargaProdukField.setText("");
        
        List<String> produkList = produkService.loadProdukByKategori(kategori);
        for (String produk : produkList) {
            produkComboBox.addItem(produk);
        }
    }
    
    private void loadProdukDetails(String namaProduk) {
        Produk produk = produkService.getProdukDetails(namaProduk);
        if (produk != null) {
            hargaProdukField.setText(String.valueOf(produk.getHarga()));
            stokLabel.setText("Stok tersedia: " + produk.getStok());
            
            SpinnerNumberModel model = (SpinnerNumberModel) jumlahSpinner.getModel();
            model.setMaximum(produk.getStok() > 0 ? produk.getStok() : 1);
        }
    }
    
    private void handleTambahProduk() {
        String namaProduk = (String) produkComboBox.getSelectedItem();
        String kategori = (String) kategoriComboBox.getSelectedItem();
        String hargaText = hargaProdukField.getText().trim();
        int jumlah = (Integer) jumlahSpinner.getValue();
        
        if (namaProduk == null || namaProduk.equals("-- Pilih Produk --") || hargaText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih produk terlebih dahulu!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            double harga = Double.parseDouble(hargaText);
            double subtotal = harga * jumlah;
            
            int rowCount = tableModel.getRowCount();
            Object[] row = {
                rowCount + 1,
                namaProduk,
                kategori,
                TransaksiService.formatRupiah(harga),
                jumlah,
                TransaksiService.formatRupiah(subtotal)
            };
            
            tableModel.addRow(row);
            totalBelanja += subtotal;
            totalLabel.setText("TOTAL: " + TransaksiService.formatRupiah(totalBelanja));
            
            // Reset
            jumlahSpinner.setValue(1);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga tidak valid!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleHapusProduk() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih item yang akan dihapus!", 
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String subtotalText = tableModel.getValueAt(selectedRow, 5).toString();
        double subtotal = TransaksiService.parseRupiah(subtotalText);
        totalBelanja -= subtotal;
        
        tableModel.removeRow(selectedRow);
        
        // Update nomor urut
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            tableModel.setValueAt(i + 1, i, 0);
        }
        
        totalLabel.setText("TOTAL: " + TransaksiService.formatRupiah(totalBelanja));
    }
    
    private void handleCetakTransaksi() {
        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tidak ada item untuk dicetak!", 
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Buat objek Transaksi
        Transaksi transaksi = new Transaksi();
        
        // Tambahkan detail transaksi
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String namaProduk = tableModel.getValueAt(i, 1).toString();
            String kategori = tableModel.getValueAt(i, 2).toString();
            double harga = TransaksiService.parseRupiah(tableModel.getValueAt(i, 3).toString());
            int jumlah = Integer.parseInt(tableModel.getValueAt(i, 4).toString());
            
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
                receiptArea.setText(strukText);
                
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
        
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String nama = tableModel.getValueAt(i, 1).toString();
            if (nama.length() > 15) nama = nama.substring(0, 12) + "...";
            
            struk.append(String.format("%-3s %-15s %5s %10s\n",
                tableModel.getValueAt(i, 0),
                nama,
                tableModel.getValueAt(i, 4),
                tableModel.getValueAt(i, 5)));
        }
        
        struk.append("-------------------------------------\n");
        struk.append(String.format("%-24s %10s\n", "TOTAL BELANJA:", 
                TransaksiService.formatRupiah(totalBelanja)));
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
        tableModel.setRowCount(0);
        receiptArea.setText("");
        totalBelanja = 0;
        totalLabel.setText("TOTAL: Rp 0");
        jumlahSpinner.setValue(1);
    }
    
    private void handleRiwayat() {
        showRiwayatDialog();
    }
    
    private void showRiwayatDialog() {
        JDialog dialog = new JDialog(this, "Riwayat Transaksi", true);
        dialog.setSize(900, 500);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        
        // Table untuk riwayat
        String[] columns = {"ID", "Tanggal", "Total Belanja", "Jumlah Item", "Detail"};
        DefaultTableModel riwayatModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable riwayatTable = new JTable(riwayatModel);
        
        // Load data transaksi menggunakan Polymorphism
        List<Transaksi> transaksiList = transaksiService.getRiwayatTransaksi(50);
        for (Transaksi t : transaksiList) {
            Object[] row = {
                t.getIdTransaksi(),
                t.getTanggal(),
                TransaksiService.formatRupiah(t.getTotalBelanja()),
                t.getJumlahItem(),
                "Lihat Detail"
            };
            riwayatModel.addRow(row);
        }
        
        JScrollPane scrollPane = new JScrollPane(riwayatTable);
        dialog.add(scrollPane, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton detailButton = new JButton("Lihat Detail");
        JButton closeButton = new JButton("Tutup");
        
        detailButton.addActionListener(e -> {
            int selectedRow = riwayatTable.getSelectedRow();
            if (selectedRow != -1) {
                int idTransaksi = (int) riwayatModel.getValueAt(selectedRow, 0);
                showDetailDialog(dialog, idTransaksi);
            } else {
                JOptionPane.showMessageDialog(dialog, "Pilih transaksi terlebih dahulu!");
            }
        });
        
        closeButton.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(detailButton);
        buttonPanel.add(closeButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.setVisible(true);
    }
    
    private void showDetailDialog(JDialog parent, int idTransaksi) {
        JDialog detailDialog = new JDialog(parent, "Detail Transaksi #" + idTransaksi, true);
        detailDialog.setSize(700, 400);
        detailDialog.setLocationRelativeTo(parent);
        detailDialog.setLayout(new BorderLayout());
        
        String[] columns = {"No", "Produk", "Kategori", "Harga", "Jumlah", "Subtotal"};
        DefaultTableModel detailModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable detailTable = new JTable(detailModel);
        
        // Load detail menggunakan Polymorphism
        List<DetailTransaksi> detailList = transaksiService.getDetailTransaksi(idTransaksi);
        int no = 1;
        for (DetailTransaksi detail : detailList) {
            Object[] row = {
                no++,
                detail.getNamaProduk(),
                detail.getKategori(),
                TransaksiService.formatRupiah(detail.getHarga()),
                detail.getJumlah(),
                TransaksiService.formatRupiah(detail.getSubtotal())
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
