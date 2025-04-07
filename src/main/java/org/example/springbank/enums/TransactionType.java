package org.example.springbank.enums;

public enum TransactionType {
    deposit, convert, transfer;

    public static CurrencyType fromString(String input) {
        try {
            return CurrencyType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}