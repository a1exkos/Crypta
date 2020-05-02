package com.example.crypta.Activity.Adapter;

import android.widget.Filter;

import com.example.crypta.Model.DataBase.ShPrefManager;
import com.example.crypta.Model.Items.Coin;

import java.util.ArrayList;

import io.reactivex.Observable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CoinAdapterFilter extends Filter {

    private ArrayList<Coin> coin_list_all;
    private boolean isFavorite;
    private CoinAdapter adapter;

    @Override
    protected FilterResults performFiltering(CharSequence searchText) {
        FilterResults filterResults = new FilterResults();
        filterResults.values = (searchText.toString().isEmpty())?coin_list_all:getSearchList(searchText);
        return filterResults;
    }

    private ArrayList<Coin> getSearchList(CharSequence searchText) {
        ArrayList<Coin> searchList = new ArrayList<>();
        Observable.fromIterable(coin_list_all)
                .filter(x ->x.getName().toLowerCase().startsWith(searchText.toString().toLowerCase()))
                .doOnNext(searchList::add)
                .subscribe();
        return searchList;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults results) {
        ArrayList<Coin> arrayList = (ArrayList<Coin>) results.values;
        adapter.setCoin_list_view(isFavorite ? getSavedList(arrayList) : arrayList);
        adapter.notifyDataSetChanged();
    }

    private ArrayList<Coin> getSavedList(ArrayList<Coin> arrayList) {
        ArrayList<Coin> savedList = new ArrayList<>();
        Observable.fromIterable(arrayList)
                .filter(x ->ShPrefManager.INSTANCE.loadCoinState(x.getName()))
                .doOnNext(savedList::add)
                .subscribe();
        return savedList;
    }
}
