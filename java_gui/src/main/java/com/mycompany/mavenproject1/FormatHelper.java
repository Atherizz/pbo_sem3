package com.mycompany.mavenproject1;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Utility class untuk formatting
 */
public class FormatHelper {
    
    public static String formatRupiah(double amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return formatter.format(amount).replace(",00", "");
    }
    
    public static double parseRupiah(String rupiah) {
        return Double.parseDouble(rupiah.replace("Rp", "")
                .replace(".", "").replace(",", ".").trim());
    }
}
