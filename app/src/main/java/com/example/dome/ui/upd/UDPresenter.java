package com.example.dome.ui.upd;

import com.example.dome.ui.detail_page.DPContract;
import com.example.dome.ui.detail_page.DPModel;
import com.example.dome.ui.recommend.RcdContract;

import java.io.IOException;

/**
 * @ClassName UDPresenter
 * @Author name
 * @Date 2023/2/16
 * @Description
 */
public class UDPresenter implements UDContract.IUDPresenter {
    private UDContract.IUDView IView;
    private UDModel udModel;

    public UDPresenter(UDContract.IUDView  IView) {
        this.IView = IView;
        this.udModel = new UDModel();
    }

    @Override
    public void getData(int page) {
        udModel.getData(page, new RcdContract.IRecModelRec.onCallBack() {
            @Override
            public void onSuccess(String responseMsg) {
                IView.onUDSuccess(responseMsg);
            }

            @Override
            public void onFailure(IOException e) {
                IView.onUDFail(e);
            }
        });
    }
}
