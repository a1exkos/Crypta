package com.example.crypta.Helper;

import android.content.Context;
import androidx.appcompat.widget.PopupMenu;
import android.view.View;

import lombok.Builder;


@Builder
public class PopupManager {

    private Context context;
    private View view;
    private int id;
    private PopupMenu.OnMenuItemClickListener listener;

    public void show(){
        PopupMenu popup = new PopupMenu(context, view);
        popup.getMenuInflater().inflate(id, popup.getMenu());
        popup.setOnMenuItemClickListener(listener);
        popup.show();
    }

}
