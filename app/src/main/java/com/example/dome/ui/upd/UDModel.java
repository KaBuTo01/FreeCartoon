package com.example.dome.ui.upd;

import com.example.dome.network.service.OkhttpUit;
import com.example.dome.network.service.callback;
import com.example.dome.ui.detail_page.DPContract;
import com.example.dome.ui.recommend.RcdContract;

import java.io.IOException;

/**
 * @ClassName UDModel
 * @Author name
 * @Date 2023/2/16
 * @Description
 */
public class UDModel implements UDContract.IUDModel {
    final private static String UP_DATA_GET = "https://m.dushimh.com/update/?page=";
    @Override
    public void getData(int page, RcdContract.IRecModelRec.onCallBack onCallBack) {
        OkhttpUit.getInstance().doGet(UP_DATA_GET + page, new callback() {
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
