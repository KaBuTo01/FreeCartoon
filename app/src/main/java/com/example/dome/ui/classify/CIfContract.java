package com.example.dome.ui.classify;

import com.example.dome.ui.recommend.RcdContract;

import java.io.IOException;

/**
 * @ClassName CIfContract
 * @Author name
 * @Date 2023/2/18
 * @Description
 */
public interface CIfContract {
    interface ICIfModel {
        interface onCallBack{
            void onSuccess(String responseMsg);
            void onFailure(IOException e);
        }
        void getData(String url, RcdContract.IRecModelRec.onCallBack onCallBack);
    }
    interface ICIfView{
        void onDataSuccess(String data);
        void onDataFail(IOException e);
    }
    interface ICIfPresenter{
        void getData(String url);
    }
}
