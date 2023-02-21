package com.example.dome.ui.detail_page;

import com.example.dome.ui.recommend.RcdContract;

import java.io.IOException;

/**
 * @ClassName DPPresenter
 * @Author name
 * @Date 2023/2/14
 * @Description
 */
public class DPPresenter implements DPContract.IDPPresenter {
    private DPContract.IDPView IView;
    private DPModel dpModel;

    public DPPresenter(DPContract.IDPView IView) {
        this.IView = IView;
        this.dpModel = new DPModel();
    }

    @Override
    public void getDetail(String url) {
        dpModel.getDetail(url, new RcdContract.IRecModelRec.onCallBack() {
            @Override
            public void onSuccess(String responseMsg) {
                IView.onDetailSuccess(responseMsg);
            }

            @Override
            public void onFailure(IOException e) {
                IView.onDetailFail(e);
            }
        });
    }
}
