package com.example.crypta.Helper;

public class CurrencySymbol {

    public static char getCurrencyChar(String currency){
            switch (currency){
                case "CNY": return 'Ұ';
                case "JPY": return '¥';
                case "GBP": return '£';
                case "EUR": return '€';
                case "USD": return '$';
                case "UAH": return '₴';
                case "RUB": return '\u20BD';
            }

            return '?';
    }
}
