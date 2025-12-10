package com.mycompany.mavenproject1;

import javax.swing.*;
import java.awt.*;

/**
 * Panel untuk menampilkan struk belanja
 * Mendemonstrasikan konsep Inheritance (extends BasePanel)
 */
public class ReceiptPanel extends BasePanel {
    private JTextArea receiptArea;
    
    public ReceiptPanel() {
        super("Struk Belanja");
        setLayout(new BorderLayout());
        setBounds(950, 10, 390, 700);
        
        initComponents();
        setupListeners();
    }
    
    /**
     * Override method abstract dari BasePanel
     */
    @Override
    protected void initComponents() {
        receiptArea = new JTextArea();
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane receiptScrollPane = new JScrollPane(receiptArea);
        add(receiptScrollPane, BorderLayout.CENTER);
    }
    
    /**
     * Override method abstract dari BasePanel
     */
    @Override
    protected void setupListeners() {
        // Tidak ada listener khusus untuk panel ini
    }
    
    /**
     * Override method dari BasePanel
     */
    @Override
    public void resetPanel() {
        receiptArea.setText("");
    }
    
    public void setReceiptText(String text) {
        receiptArea.setText(text);
    }
    
    public String getReceiptText() {
        return receiptArea.getText();
    }
}
