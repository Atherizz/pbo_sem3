package com.mycompany.mavenproject1;

import javax.swing.*;
import java.awt.*;

/**
 * Abstract class untuk panel dasar
 * Mendemonstrasikan konsep Abstract Class dalam OOP
 */
public abstract class BasePanel extends JPanel {
    protected String panelTitle;
    
    public BasePanel(String title) {
        this.panelTitle = title;
        this.setBorder(BorderFactory.createTitledBorder(title));
    }
    
    /**
     * Method abstract yang harus diimplementasikan oleh subclass
     */
    protected abstract void initComponents();
    
    /**
     * Method abstract untuk setup event listeners
     */
    protected abstract void setupListeners();
    
    /**
     * Method concrete yang dapat digunakan oleh semua subclass
     */
    protected void setComponentBounds(Component comp, int x, int y, int width, int height) {
        comp.setBounds(x, y, width, height);
    }
    
    /**
     * Method untuk reset panel (dapat di-override)
     */
    public void resetPanel() {
        // Default implementation - dapat di-override
    }
}
