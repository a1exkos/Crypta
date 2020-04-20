package com.example.crypta.Model.Items;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coin {
    private String name;
    private double price;
    private char symbol;
}
