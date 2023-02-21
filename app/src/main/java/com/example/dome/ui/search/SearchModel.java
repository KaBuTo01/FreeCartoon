package com.example.dome.ui.search;

import com.example.dome.network.service.OkhttpUit;
import com.example.dome.network.service.callback;
import com.example.dome.ui.recommend.RcdContract;

import java.io.IOException;

/**
 * @ClassName SearchModel
 * @Author name
 * @Date 2023/2/15
 * @Description
 */
public class SearchModel implements SearchContract.ISearchModel {

    @Override
    public void getSearch(String input, int page, RcdContract.IRecModelRec.onCallBack onCallBack) {
        OkhttpUit.getInstance().doGet("https://m.dushimh.com/search/?keywords=" + input + "&page=" + page, new callback() {
            @Override
            public void onSuccess(String response) {
                onCallBack.onSuccess(response);
            }

            @Override
            public void onFail(IOException e) {
                onCallBack.onFailure(e);
            }
        });
    }
}
