package com.example.dome.ui.search;

import com.example.dome.ui.recommend.RcdContract;

import java.io.IOException;

/**
 * @ClassName SearchContract
 * @Author name
 * @Date 2023/2/15
 * @Description
 */
public interface SearchContract {
    interface ISearchModel {
        interface onCallBack{
            void onSuccess(String responseMsg);
            void onFailure(IOException e);
        }
        void getSearch(String input ,int page, RcdContract.IRecModelRec.onCallBack onCallBack);

    }
    interface ISearchView{
        void onSearchSuccess(String data);
        void onSearchFail(IOException e);
    }
    interface ISearchPresenter{
        void getSearch(String input,int page);
    }
}
