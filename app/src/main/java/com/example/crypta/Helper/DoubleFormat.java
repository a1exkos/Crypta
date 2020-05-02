package com.example.crypta.Helper;

import android.annotation.SuppressLint;

public class DoubleFormat {

    public static double format(double value, int size){
        @SuppressLint("DefaultLocale") String line = String.format("%."+size+"f",value).replace(',','.');
        return Double.valueOf(line);
    }
}
