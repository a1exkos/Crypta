package com.example.crypta.Activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.crypta.Activity.Adapter.CoinAdapter;
import com.example.crypta.Helper.PopupManager;
import com.example.crypta.Model.DataBase.ShPrefManager;
import com.example.crypta.Model.Items.Coin;
import com.example.crypta.Presenter.MainPresenter;
import com.example.crypta.R;
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_name) TextView global_name;
    @BindView(R.id.toolbar_price) TextView global_price;
    @BindView(R.id.recycler_view) RecyclerView recycler;
    private CoinAdapter adapter;
    private SearchView searchView;
    private MainPresenter presenter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);
        presenter = new MainPresenter(this);
        getLifecycle().addObserver(new MainLifecycle(this, presenter));
        adapter = new CoinAdapter(presenter);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    public void unbind() {
        unbinder.unbind();
    }

    @OnClick({R.id.action_all, R.id.action_saved})
    public void click_action_menu(View view) {
        switch (view.getId()) {
            case R.id.action_all:
                adapter.setFavorite(false);
                break;
            case R.id.action_saved:
                adapter.setFavorite(true);
                break;
        }
        adapter.start_filter();
    }

    public void updateInfo(ArrayList<Coin> arrayList) {
        adapter.updateList(arrayList);
    }

    public void changeGlobalCoin(String name, String price) {
        global_name.setText(name);
        global_price.setText(price);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        RxSearchView.queryTextChanges(searchView)
                .subscribe(charSequence -> adapter.start_filter());
        return true;
    }

    public CharSequence getSearchText() {
        return searchView.getQuery();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.currency:
                PopupManager.builder().context(this).view(findViewById(R.id.currency)).id(R.menu.currency_menu)
                        .listener(menuItem -> {
                            ShPrefManager.INSTANCE.saveCurrencyCoin(String.valueOf(menuItem.getTitle()));
                            presenter.stopParse();
                            presenter.startParse();
                            return true;
                        }).build().show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
