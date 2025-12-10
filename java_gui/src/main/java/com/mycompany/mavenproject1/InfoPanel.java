package com.mycompany.mavenproject1;

import javax.swing.*;

/**
 * Panel untuk menampilkan informasi toko
 * Mendemonstrasikan konsep Inheritance (extends BasePanel)
 */
public class InfoPanel extends BasePanel {
    
    public InfoPanel() {
        super("Informasi");
        setLayout(null);
        setBounds(10, 420, 930, 290);
        
        initComponents();
        setupListeners();
    }
    
    /**
     * Override method abstract dari BasePanel
     */
    @Override
    protected void initComponents() {
        JLabel infoLabel = new JLabel("<html><b>TOKO SERBAGUNA</b><br>" +
                "Jl. Raya Merdeka No. 123<br>" +
                "Telp: (021) 12345678<br><br>" +
                "<i>Terima kasih atas kunjungan Anda!</i><br><br>" +
                "<font color='blue'>✓ Terintegrasi dengan Database MySQL</font></html>");
        setComponentBounds(infoLabel, 10, 20, 400, 150);
        add(infoLabel);
    }
    
    /**
     * Override method abstract dari BasePanel
     */
    @Override
    protected void setupListeners() {
        // Tidak ada listener khusus untuk panel ini
    }
}
