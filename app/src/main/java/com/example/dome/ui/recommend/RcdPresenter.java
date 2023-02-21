package com.example.dome.ui.recommend;

import com.example.dome.bean.Image;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName RcdContrcat
 * @Author name
 * @Date 2023/2/13
 * @Description
 */
public class RcdPresenter implements RcdContract.IRcdPresenter {
    private RcdContract.IRcdView IView;
    private RcdContract.IRecModelRec IModel;
    public RcdPresenter(RcdContract.IRcdView IView) {
        this.IView = IView;
        IModel = new RecModel();
    }

    @Override
    public void getHome() {
        IModel.getBanner(new RcdContract.IRecModelRec.onCallBack() {
            @Override
            public void onSuccess(String responseMsg) {

                IView.onHomeSuccess(responseMsg);
            }

            @Override
            public void onFailure(IOException e) {
                IView.onHomeFail(e);
            }
        });
    }
}
