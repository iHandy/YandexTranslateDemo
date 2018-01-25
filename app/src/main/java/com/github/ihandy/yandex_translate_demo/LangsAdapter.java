package com.github.ihandy.yandex_translate_demo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Handy on 01/25/2018.
 */

public class LangsAdapter extends ArrayAdapter<String> {

    private ArrayList<Map.Entry<String, String>> items;

    public LangsAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<Map.Entry<String, String>> items) {
        super(context, resource, R.id.spinner_text);
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return items.get(position).getValue();
    }

    public String getKeyByPosition(int position) {
        return items.get(position).getKey();
    }
}
