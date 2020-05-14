package com.example.crypta.Model;

import android.util.Log;

import com.example.crypta.Helper.CurrencySymbol;
import com.example.crypta.Helper.DoubleFormat;
import com.example.crypta.Model.Items.Coin;
import com.example.crypta.Model.Retrofit2.UtilsApi;
import com.example.crypta.Presenter.MainPresenter;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.AllArgsConstructor;
import okhttp3.ResponseBody;

@AllArgsConstructor
public
class MainModel {

    private MainPresenter presenter;

    public Disposable parse(String currency, String coins_name){
        return UtilsApi.getAllRequests()
                .getAllCoin(currency, coins_name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSuccess(response -> {
                    ResponseBody body = response.body();
                    if (body == null) return;

                    char symbol = CurrencySymbol.getCurrencyChar(currency);

                    TreeMap<String, String> retMap = new Gson()
                            .fromJson(new JsonParser().parse(body.string()).getAsJsonObject(),
                                    new TypeToken<TreeMap<String, String>>() {}.getType());

                    ArrayList<Coin> arrayList = new ArrayList<>();
                    for(Map.Entry<String, String> entry : retMap.entrySet()) {
                        String name = entry.getKey();
                        double price = Double.valueOf(entry.getValue());
                        Coin item = new Coin(name, DoubleFormat.format(1/price,5), symbol);
                        arrayList.add(item);
                        if (name.equals(presenter.getGlobalCoinName())) presenter.changeGlobalCoin(item);
                    }

                    Log.i("PARSE", "Спарсило " + arrayList.size() + " элементов");

                    presenter.updateInfo(arrayList);
                }).doOnError(throwable -> Log.e("PARSE_ERROR", "Ошибка")).subscribe();
    }
}
