package com.mycompany.mavenproject1;

/**
 *
 * @author Atherizz
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.text.NumberFormat;
import java.util.Locale;

public class TokoFrame {
    private static DefaultTableModel tableModel;
    private static JTable table;
    private static JTextArea receiptArea;
    private static double totalBelanja = 0;
    private static JComboBox<String> kategoriComboBox;
    private static JComboBox<String> produkComboBox;
    private static JTextField hargaProdukField;
    private static JSpinner jumlahSpinner;
    private static JLabel totalLabel;
    
    public static void main(String[] args) {
        // Test koneksi database
        if (!DatabaseConnection.testConnection()) {
            JOptionPane.showMessageDialog(null, 
                "Gagal terkoneksi ke database!\nPastikan MySQL sudah running dan database 'toko_kasir' sudah dibuat.", 
                "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
    
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Aplikasi Kasir Toko - Database Integrated");
        frame.setSize(1366, 768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Panel Input Produk
        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(10, 10, 420, 400);
        inputPanel.setLayout(null);
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Produk"));
        frame.add(inputPanel);

        // Kategori ComboBox
        JLabel kategoriLabel = new JLabel("Kategori:");
        kategoriLabel.setBounds(10, 30, 100, 25);
        inputPanel.add(kategoriLabel);
        
        kategoriComboBox = new JComboBox<>();
        kategoriComboBox.setBounds(120, 30, 280, 25);
        inputPanel.add(kategoriComboBox);
        loadKategoriFromDB();
        
        // Produk ComboBox
        JLabel produkLabel = new JLabel("Produk:");
        produkLabel.setBounds(10, 70, 100, 25);
        inputPanel.add(produkLabel);
        
        produkComboBox = new JComboBox<>();
        produkComboBox.setBounds(120, 70, 280, 25);
        inputPanel.add(produkComboBox);
        
        JLabel hargaProdukLabel = new JLabel("Harga:");
        hargaProdukLabel.setBounds(10, 110, 100, 25);
        inputPanel.add(hargaProdukLabel);

        hargaProdukField = new JTextField();
        hargaProdukField.setBounds(120, 110, 280, 25);
        hargaProdukField.setEditable(false);
        hargaProdukField.setBackground(Color.LIGHT_GRAY);
        inputPanel.add(hargaProdukField);
        
        JLabel jumlahLabel = new JLabel("Jumlah:");
        jumlahLabel.setBounds(10, 150, 100, 25);
        inputPanel.add(jumlahLabel);
        
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 100, 1);
        jumlahSpinner = new JSpinner(spinnerModel);
        jumlahSpinner.setBounds(120, 150, 100, 25);
        inputPanel.add(jumlahSpinner);
        
        JLabel stokLabel = new JLabel("Stok tersedia: -");
        stokLabel.setBounds(230, 150, 170, 25);
        stokLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        inputPanel.add(stokLabel);

        // Tombol Tambah ke Keranjang
        JButton tambahButton = new JButton("Tambah ke Keranjang");
        tambahButton.setBounds(10, 190, 180, 30);
        tambahButton.setBackground(new Color(46, 204, 113));
        tambahButton.setForeground(Color.WHITE);
        inputPanel.add(tambahButton);

        // Tombol Hapus Item
        JButton hapusButton = new JButton("Hapus Item");
        hapusButton.setBounds(200, 190, 120, 30);
        hapusButton.setBackground(new Color(231, 76, 60));
        hapusButton.setForeground(Color.WHITE);
        inputPanel.add(hapusButton);

        // Tombol Cetak Struk
        JButton cetakButton = new JButton("Cetak & Simpan");
        cetakButton.setBounds(10, 230, 150, 30);
        cetakButton.setBackground(new Color(52, 152, 219));
        cetakButton.setForeground(Color.WHITE);
        inputPanel.add(cetakButton);
        
        // Tombol Reset
        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(170, 230, 150, 30);
        resetButton.setBackground(new Color(149, 165, 166));
        resetButton.setForeground(Color.WHITE);
        inputPanel.add(resetButton);
        
        // Tombol Riwayat Transaksi
        JButton riwayatButton = new JButton("Riwayat Transaksi");
        riwayatButton.setBounds(10, 270, 310, 30);
        riwayatButton.setBackground(new Color(155, 89, 182));
        riwayatButton.setForeground(Color.WHITE);
        inputPanel.add(riwayatButton);
        
        // Label Total
        totalLabel = new JLabel("TOTAL: Rp 0");
        totalLabel.setBounds(10, 310, 300, 30);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setForeground(new Color(192, 57, 43));
        inputPanel.add(totalLabel);

        // JTable untuk menampilkan daftar belanja
        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(440, 10, 500, 400);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Daftar Belanja"));
        frame.add(tablePanel);
        
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
        
        // JTextArea untuk struk belanja
        JPanel receiptPanel = new JPanel();
        receiptPanel.setBounds(950, 10, 390, 700);
        receiptPanel.setLayout(new BorderLayout());
        receiptPanel.setBorder(BorderFactory.createTitledBorder("Struk Belanja"));
        frame.add(receiptPanel);
        
        receiptArea = new JTextArea();
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane receiptScrollPane = new JScrollPane(receiptArea);
        receiptPanel.add(receiptScrollPane, BorderLayout.CENTER);
        
        // Panel Info Toko
        JPanel infoPanel = new JPanel();
        infoPanel.setBounds(10, 420, 930, 290);
        infoPanel.setLayout(null);
        infoPanel.setBorder(BorderFactory.createTitledBorder("Informasi"));
        frame.add(infoPanel);
        
        JLabel infoLabel = new JLabel("<html><b>TOKO SERBAGUNA</b><br>" +
                "Jl. Raya Merdeka No. 123<br>" +
                "Telp: (021) 12345678<br><br>" +
                "<i>Terima kasih atas kunjungan Anda!</i><br><br>" +
                "<font color='blue'>✓ Terintegrasi dengan Database MySQL</font></html>");
        infoLabel.setBounds(10, 20, 400, 150);
        infoPanel.add(infoLabel);

        // Event Listeners
        
        // Ketika kategori dipilih, load produk
        kategoriComboBox.addActionListener(e -> {
            String selectedKategori = (String) kategoriComboBox.getSelectedItem();
            if (selectedKategori != null) {
                loadProdukByKategori(selectedKategori);
            }
        });
        
        // Ketika produk dipilih, tampilkan harga dan stok
        produkComboBox.addActionListener(e -> {
            String selectedProduk = (String) produkComboBox.getSelectedItem();
            if (selectedProduk != null && !selectedProduk.equals("-- Pilih Produk --")) {
                loadProdukDetails(selectedProduk, stokLabel);
            }
        });
        
        // Action Listener untuk tombol Tambah
        tambahButton.addActionListener(e -> {
            String namaProduk = (String) produkComboBox.getSelectedItem();
            String kategori = (String) kategoriComboBox.getSelectedItem();
            String hargaText = hargaProdukField.getText().trim();
            int jumlah = (Integer) jumlahSpinner.getValue();
            
            if (namaProduk == null || namaProduk.equals("-- Pilih Produk --") || hargaText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Pilih produk terlebih dahulu!", 
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
                    formatRupiah(harga),
                    jumlah,
                    formatRupiah(subtotal)
                };
                
                tableModel.addRow(row);
                totalBelanja += subtotal;
                totalLabel.setText("TOTAL: " + formatRupiah(totalBelanja));
                
                // Reset
                jumlahSpinner.setValue(1);
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Harga tidak valid!", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Action Listener untuk tombol Hapus
        hapusButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Pilih item yang akan dihapus!", 
                        "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String subtotalText = tableModel.getValueAt(selectedRow, 5).toString();
            double subtotal = parseRupiah(subtotalText);
            totalBelanja -= subtotal;
            
            tableModel.removeRow(selectedRow);
            
            // Update nomor urut
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                tableModel.setValueAt(i + 1, i, 0);
            }
            
            totalLabel.setText("TOTAL: " + formatRupiah(totalBelanja));
        });
        
        // Action Listener untuk tombol Cetak & Simpan
        cetakButton.addActionListener(e -> {
            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(frame, "Tidak ada item untuk dicetak!", 
                        "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Simpan ke database
            int idTransaksi = saveTransaksiToDatabase();
            
            if (idTransaksi > 0) {
                // Generate struk
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
                        formatRupiah(totalBelanja)));
                struk.append("=====================================\n");
                struk.append("\n  Terima kasih atas kunjungan Anda!\n");
                struk.append("     Transaksi tersimpan di database\n");
                struk.append("=====================================\n");
                
                receiptArea.setText(struk.toString());
                
                JOptionPane.showMessageDialog(frame, 
                    "Transaksi berhasil disimpan!\nID Transaksi: #" + idTransaksi, 
                    "Sukses", JOptionPane.INFORMATION_MESSAGE);
                
                // Reset setelah simpan
                tableModel.setRowCount(0);
                totalBelanja = 0;
                totalLabel.setText("TOTAL: Rp 0");
                jumlahSpinner.setValue(1);
            } else {
                JOptionPane.showMessageDialog(frame, 
                    "Gagal menyimpan transaksi ke database!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Action Listener untuk tombol Reset
        resetButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, 
                    "Apakah Anda yakin ingin mereset semua data?", 
                    "Konfirmasi", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                tableModel.setRowCount(0);
                receiptArea.setText("");
                totalBelanja = 0;
                totalLabel.setText("TOTAL: Rp 0");
                jumlahSpinner.setValue(1);
            }
        });
        
        // Action Listener untuk tombol Riwayat Transaksi
        riwayatButton.addActionListener(e -> {
            showRiwayatTransaksi(frame);
        });
        
        // Window closing event
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                DatabaseConnection.closeConnection();
            }
        });
    
        frame.setVisible(true);
    }
    
    // Load kategori dari database
    private static void loadKategoriFromDB() {
        kategoriComboBox.removeAllItems();
        kategoriComboBox.addItem("-- Pilih Kategori --");
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nama_kategori FROM kategori ORDER BY nama_kategori");
            
            while (rs.next()) {
                kategoriComboBox.addItem(rs.getString("nama_kategori"));
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal load kategori dari database!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Load produk berdasarkan kategori
    private static void loadProdukByKategori(String kategori) {
        produkComboBox.removeAllItems();
        produkComboBox.addItem("-- Pilih Produk --");
        hargaProdukField.setText("");
        
        if (kategori.equals("-- Pilih Kategori --")) {
            return;
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
                produkComboBox.addItem(rs.getString("nama_produk"));
            }
            
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Load detail produk (harga dan stok)
    private static void loadProdukDetails(String namaProduk, JLabel stokLabel) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT harga, stok FROM produk WHERE nama_produk = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, namaProduk);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                double harga = rs.getDouble("harga");
                int stok = rs.getInt("stok");
                
                hargaProdukField.setText(String.valueOf(harga));
                stokLabel.setText("Stok tersedia: " + stok);
                
                // Set max spinner sesuai stok
                SpinnerNumberModel model = (SpinnerNumberModel) jumlahSpinner.getModel();
                model.setMaximum(stok > 0 ? stok : 1);
            }
            
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Simpan transaksi ke database
    private static int saveTransaksiToDatabase() {
        int idTransaksi = -1;
        Connection conn = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction
            
            // Insert ke tabel transaksi
            String queryTransaksi = "INSERT INTO transaksi (total_belanja, jumlah_item) VALUES (?, ?)";
            PreparedStatement pstmtTransaksi = conn.prepareStatement(queryTransaksi, 
                    Statement.RETURN_GENERATED_KEYS);
            pstmtTransaksi.setDouble(1, totalBelanja);
            pstmtTransaksi.setInt(2, tableModel.getRowCount());
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
            
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String namaProduk = tableModel.getValueAt(i, 1).toString();
                String kategori = tableModel.getValueAt(i, 2).toString();
                double harga = parseRupiah(tableModel.getValueAt(i, 3).toString());
                int jumlah = Integer.parseInt(tableModel.getValueAt(i, 4).toString());
                double subtotal = parseRupiah(tableModel.getValueAt(i, 5).toString());
                
                pstmtDetail.setInt(1, idTransaksi);
                pstmtDetail.setString(2, namaProduk);
                pstmtDetail.setString(3, kategori);
                pstmtDetail.setDouble(4, harga);
                pstmtDetail.setInt(5, jumlah);
                pstmtDetail.setDouble(6, subtotal);
                pstmtDetail.addBatch();
                
                // Update stok produk
                updateStokProduk(conn, namaProduk, jumlah);
            }
            
            pstmtDetail.executeBatch();
            pstmtDetail.close();
            
            conn.commit(); // Commit transaction
            conn.setAutoCommit(true);
            
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback jika error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        return idTransaksi;
    }
    
    // Update stok produk
    private static void updateStokProduk(Connection conn, String namaProduk, int jumlahTerjual) 
            throws SQLException {
        String query = "UPDATE produk SET stok = stok - ? WHERE nama_produk = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, jumlahTerjual);
        pstmt.setString(2, namaProduk);
        pstmt.executeUpdate();
        pstmt.close();
    }
    
    // Tampilkan riwayat transaksi
    private static void showRiwayatTransaksi(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, "Riwayat Transaksi", true);
        dialog.setSize(900, 500);
        dialog.setLocationRelativeTo(parentFrame);
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
        
        // Load data transaksi
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT * FROM transaksi ORDER BY tanggal DESC LIMIT 50";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id_transaksi"),
                    rs.getTimestamp("tanggal"),
                    formatRupiah(rs.getDouble("total_belanja")),
                    rs.getInt("jumlah_item"),
                    "Lihat Detail"
                };
                riwayatModel.addRow(row);
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
                showDetailTransaksi(dialog, idTransaksi);
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
    
    // Tampilkan detail transaksi
    private static void showDetailTransaksi(JDialog parentDialog, int idTransaksi) {
        JDialog detailDialog = new JDialog(parentDialog, "Detail Transaksi #" + idTransaksi, true);
        detailDialog.setSize(700, 400);
        detailDialog.setLocationRelativeTo(parentDialog);
        detailDialog.setLayout(new BorderLayout());
        
        String[] columns = {"No", "Produk", "Kategori", "Harga", "Jumlah", "Subtotal"};
        DefaultTableModel detailModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable detailTable = new JTable(detailModel);
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT * FROM detail_transaksi WHERE id_transaksi = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idTransaksi);
            ResultSet rs = pstmt.executeQuery();
            
            int no = 1;
            while (rs.next()) {
                Object[] row = {
                    no++,
                    rs.getString("nama_produk"),
                    rs.getString("kategori"),
                    formatRupiah(rs.getDouble("harga")),
                    rs.getInt("jumlah"),
                    formatRupiah(rs.getDouble("subtotal"))
                };
                detailModel.addRow(row);
            }
            
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
    
    // Helper method untuk format Rupiah
    private static String formatRupiah(double amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return formatter.format(amount).replace(",00", "");
    }
    
    // Helper method untuk parse Rupiah ke double
    private static double parseRupiah(String rupiah) {
        return Double.parseDouble(rupiah.replace("Rp", "")
                .replace(".", "").replace(",", ".").trim());
    }
}
