package com.example.dome.ui.recommend;

import com.example.dome.bean.Image;
import com.example.dome.network.service.OkhttpUit;
import com.example.dome.network.service.callback;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName RecModel
 * @Author name
 * @Date 2023/2/13
 * @Description
 */
public class RecModel implements RcdContract.IRecModelRec{
    private static final String GET_BANNER = "https://m.dushimh.com/";
    @Override
    public void getBanner(onCallBack onCallBack) {
        OkhttpUit.getInstance().doGet(GET_BANNER, new callback() {
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
