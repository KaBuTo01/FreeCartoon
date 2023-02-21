package com.example.dome.ui.read;

import com.example.dome.network.service.OkhttpUit;
import com.example.dome.network.service.callback;
import com.example.dome.ui.recommend.RcdContract;

import java.io.IOException;

/**
 * @ClassName ReadModel
 * @Author name
 * @Date 2023/2/15
 * @Description
 */
public class ReadModel implements ReadContract.IReadModel {
    @Override
    public void getRead(String url, RcdContract.IRecModelRec.onCallBack onCallBack) {
        OkhttpUit.getInstance().doGet(url, new callback() {
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
