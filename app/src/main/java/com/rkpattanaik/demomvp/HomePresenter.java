package com.rkpattanaik.demomvp;

import com.rkpattanaik.demomvp.model.Item;

import java.util.List;

/**
 * @author Rajesh Pattanaik
 */

class HomePresenter implements HomeContract.Presenter, FetchItemsInteractor.OnItemsFetchedListener {
    private HomeContract.View mView;
    private FetchItemsInteractor mFetchItemsInteractor;
    private int mFileNo;

    HomePresenter() {
        mFetchItemsInteractor = new FetchItemsInteractorImpl(MainApp.getInstance().getApplicationContext());
    }

    @Override

    public void attachView(HomeContract.View view) {
        mView = view;
        mFileNo = mView.getCurrentJsonFileNo();
        mFetchItemsInteractor.fetchItems(mFileNo, this);
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }

    @Override
    public void onRefreshed() {
         mFileNo = mView.getCurrentJsonFileNo();

        if (mFileNo != 6) mFileNo = mFileNo + 1;
        else mFileNo = 1;

        mFetchItemsInteractor.fetchItems(mFileNo, this);
    }

    @Override
    public void onItemsFetched(List<Item> items) {
        if (mView != null) {
            mView.hideSwipeRefreshLoading();
            mView.setCurrentJsonFileNo(mFileNo);
            if (items != null && items.size() > 0) {
                mView.showItems(items);
            } else {
                mView.showError("Unable to fetch!");
            }
        }
    }
}
