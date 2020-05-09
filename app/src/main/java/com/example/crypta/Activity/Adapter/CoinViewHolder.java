package com.example.crypta.Activity.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.crypta.R;

import butterknife.BindView;
import butterknife.ButterKnife;

class CoinViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_coin_saved_state) ImageView saved_state;
    @BindView(R.id.item_coin_name) TextView name;
    @BindView(R.id.item_coin_price) TextView price;

    CoinViewHolder(@NonNull View holderView) {
        super(holderView);
        ButterKnife.bind(this,holderView);
    }
}