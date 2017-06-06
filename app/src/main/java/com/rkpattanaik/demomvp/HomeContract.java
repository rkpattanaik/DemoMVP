package com.rkpattanaik.demomvp;

import com.rkpattanaik.demomvp.model.Item;

import java.util.List;

/**
 * This is the contract for the presenter and view
 * for HomeActivity
 *
 * @author Rajesh Pattanaik
 */

interface HomeContract {

    interface Presenter {
        void attachView(HomeContract.View view);
        void detachView();
        void onRefreshed();
    }

    interface View {
        void hideSwipeRefreshLoading();
        void setCurrentJsonFileNo(int currentJsonFileNo);
        int getCurrentJsonFileNo();
        void showItems(List<Item> items);
        void showError(String message);
    }

}
