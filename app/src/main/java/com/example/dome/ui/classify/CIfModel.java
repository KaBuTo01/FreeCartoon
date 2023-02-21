package com.example.dome.ui.classify;

import com.example.dome.network.service.OkhttpUit;
import com.example.dome.network.service.callback;
import com.example.dome.ui.recommend.RcdContract;

import java.io.IOException;

/**
 * @ClassName CIfModel
 * @Author name
 * @Date 2023/2/18
 * @Description
 */
public class CIfModel implements CIfContract.ICIfModel {
    final private static String GET_URL = "https://m.dushimh.com";
    @Override
    public void getData(String url, RcdContract.IRecModelRec.onCallBack onCallBack) {
        OkhttpUit.getInstance().doGet(GET_URL+url, new callback() {
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
