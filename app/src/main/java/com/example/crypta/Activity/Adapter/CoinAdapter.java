package com.example.crypta.Activity.Adapter;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.crypta.Helper.ListMerger;
import com.example.crypta.Helper.PopupManager;
import com.example.crypta.Model.DataBase.ShPrefManager;
import com.example.crypta.Model.Items.Coin;
import com.example.crypta.R;

import java.util.ArrayList;

import lombok.Setter;

public class CoinAdapter extends RecyclerView.Adapter<CoinViewHolder> implements Filterable {

    @Setter private boolean isFavorite;
    @Setter private ArrayList<Coin> coin_list_view;
    private ArrayList<Coin> coin_list_all;
    private com.example.crypta.Presenter.MainPresenter presenter;

    public CoinAdapter(com.example.crypta.Presenter.MainPresenter presenter) {
        this.coin_list_view = new ArrayList<>();
        this.coin_list_all = new ArrayList<>();
        this.presenter = presenter;
        this.isFavorite = false;
    }

    public void updateList(ArrayList<Coin> arrayList) {
        coin_list_all = ListMerger.merge(coin_list_all, arrayList);
        start_filter();
    }

    public void start_filter(){
        getFilter().filter(presenter.getSearchText());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CoinViewHolder holder, int pos) {
        Coin item = coin_list_view.get(pos);
        String name = item.getName();
        double price = item.getPrice();
        char symbol = item.getSymbol();
        boolean state = ShPrefManager.INSTANCE.loadCoinState(name);

        holder.name.setText(name);
        holder.price.setText("" + price + symbol);
        holder.saved_state.setImageResource(state ? R.drawable.star : R.drawable.star_outline);

        holder.saved_state.setOnClickListener(star -> {
            boolean new_state = !ShPrefManager.INSTANCE.loadCoinState(name);
            ShPrefManager.INSTANCE.saveCoinState(name, new_state);
            holder.saved_state.setImageResource(new_state ? R.drawable.star : R.drawable.star_outline);
            start_filter();
        });

        holder.saved_state.setOnLongClickListener(star -> {
            PopupManager.builder().context(presenter.getView()).view(star).id(R.menu.saved_menu)
                    .listener(item1 -> {
                        ShPrefManager.INSTANCE.saveGlobalCoin(name);
                        presenter.changeGlobalCoin(item);
                        return true;
                    }).build().show();
            return true;
        });
    }


    @NonNull
    @Override
    public CoinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int pos) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coin, parent, false);
        return new CoinViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return coin_list_view.size();
    }

    @Override
    public Filter getFilter() {
        return new CoinAdapterFilter(coin_list_all,isFavorite,this);
    }

}
