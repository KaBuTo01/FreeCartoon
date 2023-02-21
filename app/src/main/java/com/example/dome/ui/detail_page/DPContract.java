package com.example.dome.ui.detail_page;

import com.example.dome.ui.recommend.RcdContract;

import java.io.IOException;

/**
 * @ClassName DPContract
 * @Author name
 * @Date 2023/2/14
 * @Description
 */
public interface DPContract {
    interface IDPModel {
        interface onCallBack{
            void onSuccess(String responseMsg);
            void onFailure(IOException e);
        }
        void getDetail(String url,RcdContract.IRecModelRec.onCallBack onCallBack);

    }
    interface IDPView{
        void onDetailSuccess(String data);
        void onDetailFail(IOException e);
    }
    interface IDPPresenter{
        void getDetail(String url);
    }
}
