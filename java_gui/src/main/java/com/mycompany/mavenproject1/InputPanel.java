package com.mycompany.mavenproject1;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Panel untuk input produk
 * Mendemonstrasikan konsep Inheritance (extends BasePanel)
 */
public class InputPanel extends BasePanel {
    private JComboBox<String> kategoriComboBox;
    private JComboBox<String> produkComboBox;
    private JTextField hargaProdukField;
    private JSpinner jumlahSpinner;
    private JLabel stokLabel;
    private JLabel totalLabel;
    private JButton tambahButton;
    private JButton hapusButton;
    private JButton cetakButton;
    private JButton resetButton;
    private JButton riwayatButton;
    
    private ProdukOperations produkService;
    private double totalBelanja;
    
    public InputPanel() {
        super("Input Produk");
        this.produkService = new ProdukService(); // Polymorphism
        this.totalBelanja = 0;
        setLayout(null);
        setBounds(10, 10, 420, 400);
        
        initComponents();
        setupListeners();
        loadKategori();
    }
    
    /**
     * Override method abstract dari BasePanel
     */
    @Override
    protected void initComponents() {
        // Kategori ComboBox
        JLabel kategoriLabel = new JLabel("Kategori:");
        setComponentBounds(kategoriLabel, 10, 30, 100, 25);
        add(kategoriLabel);
        
        kategoriComboBox = new JComboBox<>();
        setComponentBounds(kategoriComboBox, 120, 30, 280, 25);
        add(kategoriComboBox);
        
        // Produk ComboBox
        JLabel produkLabel = new JLabel("Produk:");
        setComponentBounds(produkLabel, 10, 70, 100, 25);
        add(produkLabel);
        
        produkComboBox = new JComboBox<>();
        setComponentBounds(produkComboBox, 120, 70, 280, 25);
        add(produkComboBox);
        
        // Harga Field
        JLabel hargaProdukLabel = new JLabel("Harga:");
        setComponentBounds(hargaProdukLabel, 10, 110, 100, 25);
        add(hargaProdukLabel);

        hargaProdukField = new JTextField();
        setComponentBounds(hargaProdukField, 120, 110, 280, 25);
        hargaProdukField.setEditable(false);
        hargaProdukField.setBackground(Color.LIGHT_GRAY);
        add(hargaProdukField);
        
        // Jumlah Spinner
        JLabel jumlahLabel = new JLabel("Jumlah:");
        setComponentBounds(jumlahLabel, 10, 150, 100, 25);
        add(jumlahLabel);
        
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 100, 1);
        jumlahSpinner = new JSpinner(spinnerModel);
        setComponentBounds(jumlahSpinner, 120, 150, 100, 25);
        add(jumlahSpinner);
        
        stokLabel = new JLabel("Stok tersedia: -");
        setComponentBounds(stokLabel, 230, 150, 170, 25);
        stokLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        add(stokLabel);

        // Tombol Tambah ke Keranjang
        tambahButton = new JButton("Tambah ke Keranjang");
        setComponentBounds(tambahButton, 10, 190, 180, 30);
        tambahButton.setBackground(new Color(46, 204, 113));
        tambahButton.setForeground(Color.WHITE);
        add(tambahButton);

        // Tombol Hapus Item
        hapusButton = new JButton("Hapus Item");
        setComponentBounds(hapusButton, 200, 190, 120, 30);
        hapusButton.setBackground(new Color(231, 76, 60));
        hapusButton.setForeground(Color.WHITE);
        add(hapusButton);

        // Tombol Cetak Struk
        cetakButton = new JButton("Cetak & Simpan");
        setComponentBounds(cetakButton, 10, 230, 150, 30);
        cetakButton.setBackground(new Color(52, 152, 219));
        cetakButton.setForeground(Color.WHITE);
        add(cetakButton);
        
        // Tombol Reset
        resetButton = new JButton("Reset");
        setComponentBounds(resetButton, 170, 230, 150, 30);
        resetButton.setBackground(new Color(149, 165, 166));
        resetButton.setForeground(Color.WHITE);
        add(resetButton);
        
        // Tombol Riwayat Transaksi
        riwayatButton = new JButton("Riwayat Transaksi");
        setComponentBounds(riwayatButton, 10, 270, 310, 30);
        riwayatButton.setBackground(new Color(155, 89, 182));
        riwayatButton.setForeground(Color.WHITE);
        add(riwayatButton);
        
        // Label Total
        totalLabel = new JLabel("TOTAL: Rp 0");
        setComponentBounds(totalLabel, 10, 310, 300, 30);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setForeground(new Color(192, 57, 43));
        add(totalLabel);
    }
    
    /**
     * Override method abstract dari BasePanel
     */
    @Override
    protected void setupListeners() {
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
            
            // Set max spinner sesuai stok
            SpinnerNumberModel model = (SpinnerNumberModel) jumlahSpinner.getModel();
            model.setMaximum(produk.getStok() > 0 ? produk.getStok() : 1);
        }
    }
    
    /**
     * Override method dari BasePanel
     */
    @Override
    public void resetPanel() {
        totalBelanja = 0;
        totalLabel.setText("TOTAL: Rp 0");
        jumlahSpinner.setValue(1);
        hargaProdukField.setText("");
        stokLabel.setText("Stok tersedia: -");
    }
    
    // Getters
    public JComboBox<String> getKategoriComboBox() {
        return kategoriComboBox;
    }
    
    public JComboBox<String> getProdukComboBox() {
        return produkComboBox;
    }
    
    public JTextField getHargaProdukField() {
        return hargaProdukField;
    }
    
    public JSpinner getJumlahSpinner() {
        return jumlahSpinner;
    }
    
    public JButton getTambahButton() {
        return tambahButton;
    }
    
    public JButton getHapusButton() {
        return hapusButton;
    }
    
    public JButton getCetakButton() {
        return cetakButton;
    }
    
    public JButton getResetButton() {
        return resetButton;
    }
    
    public JButton getRiwayatButton() {
        return riwayatButton;
    }
    
    public double getTotalBelanja() {
        return totalBelanja;
    }
    
    public void setTotalBelanja(double total) {
        this.totalBelanja = total;
        totalLabel.setText("TOTAL: " + FormatHelper.formatRupiah(total));
    }
    
    public void updateTotal(double amount) {
        this.totalBelanja += amount;
        totalLabel.setText("TOTAL: " + FormatHelper.formatRupiah(totalBelanja));
    }
}
