package com.example.dome.ui.classify;

import com.example.dome.ui.recommend.RcdContract;

import java.io.IOException;

/**
 * @ClassName CIfPresenter
 * @Author name
 * @Date 2023/2/18
 * @Description
 */
public class CIfPresenter implements CIfContract.ICIfPresenter {
    private CIfContract.ICIfView IView;
    private CIfModel cifModel;

    public CIfPresenter(CIfContract.ICIfView IView) {
        this.IView = IView;
        this.cifModel = new CIfModel();
    }

    @Override
    public void getData(String url) {
        cifModel.getData(url, new RcdContract.IRecModelRec.onCallBack() {
            @Override
            public void onSuccess(String responseMsg) {
                IView.onDataSuccess(responseMsg);
            }
            @Override
            public void onFailure(IOException e) {
                IView.onDataFail(e);
            }
        });
    }
}
