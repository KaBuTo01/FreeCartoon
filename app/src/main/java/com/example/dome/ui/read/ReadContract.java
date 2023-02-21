package com.example.dome.ui.read;

import com.example.dome.ui.recommend.RcdContract;

import java.io.IOException;

/**
 * @ClassName ReadContract
 * @Author name
 * @Date 2023/2/15
 * @Description
 */
public interface ReadContract {
    interface IReadModel {
        interface onCallBack{
            void onSuccess(String responseMsg);
            void onFailure(IOException e);
        }
        void getRead(String url ,RcdContract.IRecModelRec.onCallBack onCallBack);

    }
    interface IReadView{
        void onReadSuccess(String data);
        void onReadFail(IOException e);
    }
    interface IReadPresenter{
        void getRead(String url);
    }
}
