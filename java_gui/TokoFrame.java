import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

public class TokoFrame {
    private static DefaultTableModel tableModel;
    private static JTable table;
    private static JTextArea receiptArea;
    private static double totalBelanja = 0;
    
    public TokoFrame(){

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Aplikasi Kasir Toko");
        frame.setSize(1366, 768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Panel Input Produk
        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(10, 10, 420, 350);
        inputPanel.setLayout(null);
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Produk"));
        frame.add(inputPanel);

        JLabel namaProdukLabel = new JLabel("Nama Produk:");
        inputPanel.add(namaProdukLabel);
        namaProdukLabel.setBounds(10, 30, 100, 25);

        JTextField namaProdukField = new JTextField();
        namaProdukField.setBounds(120, 30, 280, 25);
        inputPanel.add(namaProdukField);
        
        // KOMPONEN 1: JComboBox untuk kategori produk
        JLabel kategoriLabel = new JLabel("Kategori:");
        kategoriLabel.setBounds(10, 70, 100, 25);
        inputPanel.add(kategoriLabel);
        
        String[] kategoriOptions = {"Makanan", "Minuman", "Elektronik", "Pakaian", "Alat Tulis"};
        JComboBox<String> kategoriComboBox = new JComboBox<>(kategoriOptions);
        kategoriComboBox.setBounds(120, 70, 280, 25);
        inputPanel.add(kategoriComboBox);
        
        JLabel hargaProdukLabel = new JLabel("Harga:");
        hargaProdukLabel.setBounds(10, 110, 100, 25);
        inputPanel.add(hargaProdukLabel);

        JTextField hargaProdukField = new JTextField();
        hargaProdukField.setBounds(120, 110, 280, 25);
        inputPanel.add(hargaProdukField);
        
        // KOMPONEN 2: JSpinner untuk jumlah barang
        JLabel jumlahLabel = new JLabel("Jumlah:");
        jumlahLabel.setBounds(10, 150, 100, 25);
        inputPanel.add(jumlahLabel);
        
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 100, 1);
        JSpinner jumlahSpinner = new JSpinner(spinnerModel);
        jumlahSpinner.setBounds(120, 150, 100, 25);
        inputPanel.add(jumlahSpinner);

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
        JButton cetakButton = new JButton("Cetak Struk");
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
        
        // Label Total
        JLabel totalLabel = new JLabel("TOTAL: Rp 0");
        totalLabel.setBounds(10, 270, 300, 30);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setForeground(new Color(192, 57, 43));
        inputPanel.add(totalLabel);

        // KOMPONEN 3: JTable untuk menampilkan daftar belanja
        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(440, 10, 500, 350);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Daftar Belanja"));
        frame.add(tablePanel);
        
        String[] columnNames = {"No", "Nama Produk", "Kategori", "Harga", "Jumlah", "Subtotal"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Membuat tabel tidak bisa diedit langsung
            }
        };
        table = new JTable(tableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        
        // KOMPONEN 4: JScrollPane untuk tabel
        JScrollPane tableScrollPane = new JScrollPane(table);
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        
        // KOMPONEN 5: JTextArea untuk struk belanja
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
        infoPanel.setBounds(10, 370, 930, 340);
        infoPanel.setLayout(null);
        infoPanel.setBorder(BorderFactory.createTitledBorder("Informasi"));
        frame.add(infoPanel);
        
        JLabel infoLabel = new JLabel("<html><b>TOKO SERBAGUNA</b><br>" +
                "Jl. Raya Merdeka No. 123<br>" +
                "Telp: (021) 12345678<br><br>" +
                "<i>Terima kasih atas kunjungan Anda!</i></html>");
        infoLabel.setBounds(10, 20, 300, 100);
        infoPanel.add(infoLabel);

        // Action Listener untuk tombol Tambah
        tambahButton.addActionListener(e -> {
            String namaProduk = namaProdukField.getText().trim();
            String hargaText = hargaProdukField.getText().trim();
            String kategori = (String) kategoriComboBox.getSelectedItem();
            int jumlah = (Integer) jumlahSpinner.getValue();
            
            if (namaProduk.isEmpty() || hargaText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nama produk dan harga harus diisi!", 
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
                
                // Clear input
                namaProdukField.setText("");
                hargaProdukField.setText("");
                jumlahSpinner.setValue(1);
                namaProdukField.requestFocus();
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Harga harus berupa angka!", 
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
        
        // Action Listener untuk tombol Cetak Struk
        cetakButton.addActionListener(e -> {
            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(frame, "Tidak ada item untuk dicetak!", 
                        "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            StringBuilder struk = new StringBuilder();
            struk.append("=====================================\n");
            struk.append("         TOKO SERBAGUNA\n");
            struk.append("     Jl. Raya Merdeka No. 123\n");
            struk.append("      Telp: (021) 12345678\n");
            struk.append("=====================================\n\n");
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
            struk.append("=====================================\n");
            
            receiptArea.setText(struk.toString());
            JOptionPane.showMessageDialog(frame, "Struk berhasil dicetak!", 
                    "Sukses", JOptionPane.INFORMATION_MESSAGE);
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
                namaProdukField.setText("");
                hargaProdukField.setText("");
                jumlahSpinner.setValue(1);
            }
        });
    
        frame.setVisible(true);
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