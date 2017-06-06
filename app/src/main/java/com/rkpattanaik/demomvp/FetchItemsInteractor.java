package com.rkpattanaik.demomvp;

import com.rkpattanaik.demomvp.model.Item;

import java.util.List;

/**
 * This is the interface for the interactor to handle fetching of
 * the items.
 *
 * @author Rajesh Pattanaik
 */

interface FetchItemsInteractor {
    void fetchItems(int currentJsonFileNo, OnItemsFetchedListener listener);

    interface OnItemsFetchedListener {
        void onItemsFetched(List<Item> items);
    }

}
