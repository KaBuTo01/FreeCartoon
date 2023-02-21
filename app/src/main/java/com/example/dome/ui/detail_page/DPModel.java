package com.example.dome.ui.detail_page;

import com.example.dome.network.service.OkhttpUit;
import com.example.dome.network.service.callback;
import com.example.dome.ui.recommend.RcdContract;

import java.io.IOException;

/**
 * @ClassName DPmodel
 * @Author name
 * @Date 2023/2/14
 * @Description
 */
public class DPModel implements DPContract.IDPModel {
    @Override
    public void getDetail(String url, RcdContract.IRecModelRec.onCallBack onCallBack) {
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
