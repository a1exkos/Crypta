package com.example.crypta.Model.DataBase;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public enum ShPrefManager {

    INSTANCE;

    private SharedPreferences coinStatePref;
    private SharedPreferences globalCoinPref;
    private SharedPreferences currencyCoinPref;

    public void init(Context context) {
        this.coinStatePref = context.getSharedPreferences("CoinState", MODE_PRIVATE);
        this.globalCoinPref = context.getSharedPreferences("GlobalCoin", MODE_PRIVATE);
        this.currencyCoinPref = context.getSharedPreferences("CurrencyCoin", MODE_PRIVATE);
    }

    public void saveCoinState(String CryptoName, boolean state) {
        coinStatePref.edit().putBoolean(CryptoName, state).apply();
    }

    public boolean loadCoinState(String CryptoName) {
        return coinStatePref.getBoolean(CryptoName, false);
    }

    public void saveGlobalCoin(String name) {
        globalCoinPref.edit().putString("GLOBAL", name).apply();
    }

    public String loadGlobalCoin() {
        return globalCoinPref.getString("GLOBAL", "BTC");
    }

    public void saveCurrencyCoin(String name) {
        currencyCoinPref.edit().putString("CURRENCY", name).apply();
    }

    public String loadCurrencyCoin() {
        return currencyCoinPref.getString("CURRENCY", "USD");
    }
}
