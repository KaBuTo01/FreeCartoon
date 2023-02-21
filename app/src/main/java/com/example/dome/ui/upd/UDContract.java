package com.example.dome.ui.upd;

import com.example.dome.ui.recommend.RcdContract;

import java.io.IOException;

/**
 * @ClassName UDContract
 * @Author name
 * @Date 2023/2/16
 * @Description
 */
public interface UDContract {
    interface IUDModel {
        interface onCallBack{
            void onSuccess(String responseMsg);
            void onFailure(IOException e);
        }
        void getData(int page,RcdContract.IRecModelRec.onCallBack onCallBack);

    }
    interface IUDView{
        void onUDSuccess(String data);
        void onUDFail(IOException e);
    }
    interface IUDPresenter{
        void getData(int page);
    }
}
