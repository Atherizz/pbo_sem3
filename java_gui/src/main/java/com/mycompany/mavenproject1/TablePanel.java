package com.mycompany.mavenproject1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Panel untuk menampilkan tabel belanja
 * Mendemonstrasikan konsep Inheritance (extends BasePanel)
 */
public class TablePanel extends BasePanel {
    private DefaultTableModel tableModel;
    private JTable table;
    
    public TablePanel() {
        super("Daftar Belanja");
        setLayout(new BorderLayout());
        setBounds(440, 10, 500, 400);
        
        initComponents();
        setupListeners();
    }
    
    /**
     * Override method abstract dari BasePanel
     */
    @Override
    protected void initComponents() {
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
        add(tableScrollPane, BorderLayout.CENTER);
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
        tableModel.setRowCount(0);
    }
    
    public void addRow(Object[] rowData) {
        tableModel.addRow(rowData);
    }
    
    public void removeRow(int rowIndex) {
        tableModel.removeRow(rowIndex);
    }
    
    public void updateRowNumber(int rowIndex, int newNumber) {
        tableModel.setValueAt(newNumber, rowIndex, 0);
    }
    
    public int getRowCount() {
        return tableModel.getRowCount();
    }
    
    public Object getValueAt(int row, int column) {
        return tableModel.getValueAt(row, column);
    }
    
    public int getSelectedRow() {
        return table.getSelectedRow();
    }
    
    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
