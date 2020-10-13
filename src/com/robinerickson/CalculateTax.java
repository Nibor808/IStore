package com.robinerickson;

import java.util.AbstractMap;
import java.util.Map;

public abstract class CalculateTax  {
    private static final Map<String, Double> taxAmounts = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("AL", 0.05),
            new AbstractMap.SimpleEntry<>("BC", 0.12),
            new AbstractMap.SimpleEntry<>("MAN", 0.12),
            new AbstractMap.SimpleEntry<>("NB", 0.15),
            new AbstractMap.SimpleEntry<>("NFLD", 0.15),
            new AbstractMap.SimpleEntry<>("NWT", 0.05),
            new AbstractMap.SimpleEntry<>("NS", 0.15),
            new AbstractMap.SimpleEntry<>("NV", 0.05),
            new AbstractMap.SimpleEntry<>("ON", 0.13),
            new AbstractMap.SimpleEntry<>("PEI", 0.15),
            new AbstractMap.SimpleEntry<>("QC", 0.14975),
            new AbstractMap.SimpleEntry<>("SK", 0.11),
            new AbstractMap.SimpleEntry<>("YK", 0.05)
    );

    public static double calculate(double amount, String region) {

        double taxAmount = taxAmounts.get(region);

        if (amount <= 0.0) {
            System.out.println("Nothing to calculate.");
            return  0.0;
        }

        return amount * taxAmount;
    }
}
