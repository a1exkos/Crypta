package com.example.crypta.Helper;

import com.example.crypta.Model.Items.Coin;

import java.util.ArrayList;

public class ListMerger {

    public static ArrayList<Coin> merge(ArrayList<Coin> main_list, ArrayList<Coin> arrayList) {

        boolean conformity = false;

        if (main_list.isEmpty())
            main_list = arrayList;

        for (int i = 0; i < main_list.size(); i++) {
            for (int j = 0; j < arrayList.size(); j++) {
                if (main_list.get(i).getName().equals(arrayList.get(j).getName())) {
                    main_list.set(i, arrayList.get(j));
                    conformity = true;
                } else if (j == arrayList.size() - 1)
                    if (!conformity)
                        main_list.add(arrayList.get(j));
            }
            conformity = false;
        }

        return main_list;
    }
}
