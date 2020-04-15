package com.example.crypta.Model.DataBase;

import java.util.ArrayList;

public enum CoinNames {

    INSTANCE;
    private ArrayList<String> coin_names;

    public void init(){
        coin_names = new ArrayList<>();
        coin_names.add("BTC");
        coin_names.add("ETH");
        coin_names.add("XRP");
        coin_names.add("BCH");
        coin_names.add("XLM");
        coin_names.add("EOS");
        coin_names.add("LTC");
        coin_names.add("ADA");
        coin_names.add("XMR");
        coin_names.add("UDST");
        coin_names.add("TRX");
        coin_names.add("DASH");
        coin_names.add("MIOTA");
        coin_names.add("BNB");
        coin_names.add("NEO");
        coin_names.add("ETC");
        coin_names.add("XEM");
        coin_names.add("XTZ");
        coin_names.add("ZEC");
        coin_names.add("VET");
        coin_names.add("BTG");
        coin_names.add("MKR");
        coin_names.add("OMG");
        coin_names.add("ZRX");
        coin_names.add("DOGE");
        coin_names.add("DCR");
        coin_names.add("QTUM");
        coin_names.add("ONT");
        coin_names.add("LSK");
        coin_names.add("ZIL");
        coin_names.add("AE");
        coin_names.add("BCD");
        coin_names.add("BAT");
        coin_names.add("BTS");
        coin_names.add("NANO");
        coin_names.add("BCN");
        coin_names.add("ICX");
        coin_names.add("NPXS");
        coin_names.add("SC");
        coin_names.add("DGB");
        coin_names.add("STEEM");
        coin_names.add("XVG");
        coin_names.add("PPT");
        coin_names.add("BTM");
        coin_names.add("AOA");
        coin_names.add("LINK");
        coin_names.add("WAVES");
        coin_names.add("ETP");
        coin_names.add("REP");
        coin_names.add("GNT");
    }

    public String getNames(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < coin_names.size(); i++) {
            stringBuilder.append(coin_names.get(i));
            if (i!=coin_names.size()-1)
                stringBuilder.append(',');
        }
        return stringBuilder.toString();
    }

}
