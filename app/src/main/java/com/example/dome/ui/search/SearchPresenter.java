package com.example.dome.ui.search;

import com.example.dome.ui.recommend.RcdContract;

import java.io.IOException;

/**
 * @ClassName SearchPresenter
 * @Author name
 * @Date 2023/2/15
 * @Description
 */
public class SearchPresenter implements SearchContract.ISearchPresenter {
    private SearchContract.ISearchView IView;
    private SearchModel searchModel;
    public SearchPresenter(SearchContract.ISearchView IView) {
        this.IView = IView;
        searchModel = new SearchModel();
    }

    @Override
    public void getSearch(String input, int page) {
        searchModel.getSearch(input, page, new RcdContract.IRecModelRec.onCallBack() {
            @Override
            public void onSuccess(String responseMsg) {
                IView.onSearchSuccess(responseMsg);
            }

            @Override
            public void onFailure(IOException e) {
                IView.onSearchFail(e);
            }
        });
    }
}
