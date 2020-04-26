package com.example.crypta.Presenter;

import com.example.crypta.Activity.MainActivity;
import com.example.crypta.Helper.Internet;
import com.example.crypta.Model.DataBase.CoinNames;
import com.example.crypta.Model.DataBase.ShPrefManager;
import com.example.crypta.Model.Items.Coin;
import com.example.crypta.Model.MainModel;

import java.util.ArrayList;

import io.reactivex.Completable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;

public class MainPresenter {

    @Getter
    private MainActivity view;
    private MainModel model;

    private Disposable disposableIn;
    private Disposable disposableOut;

    public MainPresenter(MainActivity view) {
        this.view = view;
        this.model = new MainModel(this);
    }

    public void changeGlobalCoin(Coin item) {
        String name = item.getName();
        String price = "" + item.getPrice() + item.getSymbol();
        view.changeGlobalCoin(name, price);
    }

    public void updateInfo(ArrayList<Coin> arrayList) {
        view.updateInfo(arrayList);
    }

    public void startParse() {
        disposableOut = Completable.fromAction(this::loopParse)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    private void loopParse() throws InterruptedException {
        if (Internet.hasConnection(view))
            disposableIn = model.parse(ShPrefManager.INSTANCE.loadCurrencyCoin(), CoinNames.INSTANCE.getNames());
        Thread.sleep(3000);
        loopParse();
    }

    public void stopParse() {
        if (disposableIn != null)
            disposableIn.dispose();
        if (disposableOut != null)
            disposableOut.dispose();
    }

    public void unbind() {
        view.unbind();
    }

    public String getGlobalCoinName() {
        return ShPrefManager.INSTANCE.loadGlobalCoin();
    }

    public CharSequence getSearchText() {
        return view.getSearchText();
    }

}
