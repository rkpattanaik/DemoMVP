package com.rkpattanaik.demomvp;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rkpattanaik.demomvp.model.Item;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rajesh Pattanaik
 */

class FetchItemsInteractorImpl implements FetchItemsInteractor {

    private Context mContext;

    FetchItemsInteractorImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void fetchItems(int fileNo, OnItemsFetchedListener listener) {
        String fileName = "dummy_json" + fileNo + ".json";

        // We use Handler here to offload our work to a worker thread to avoid UI lag
        new Handler().post(() -> listener.onItemsFetched(getItemsFromJson(fileName)));
    }

    /**
     * This method fetches the json array from the json file.
     * The using Gson, we map the json array to our List<Item>
     * @param jsonFileName The name of the json file we want to load
     * @return List<Item>
     */
    private List<Item> getItemsFromJson(String jsonFileName) {
        String json;
        List<Item> items = new ArrayList<>();

        try {
            InputStream is = mContext.getAssets().open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<Item>>(){}.getType();
        items.addAll(gson.fromJson(json, type));
        return items;
    }
}
