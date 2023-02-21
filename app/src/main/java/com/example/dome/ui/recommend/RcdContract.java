package com.example.dome.ui.recommend;

import com.example.dome.bean.Image;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName RcdContract
 * @Author name
 * @Date 2023/2/13
 * @Description
 */
public interface RcdContract {

     interface IRecModelRec {
         interface onCallBack{
             void onSuccess(String responseMsg);
             void onFailure(IOException e);
         }
         void getBanner(onCallBack onCallBack);

    }
    interface IRcdView{
        void onHomeSuccess(String data);
        void onHomeFail(IOException e);
    }
    interface IRcdPresenter{
        void getHome();
    }
}
