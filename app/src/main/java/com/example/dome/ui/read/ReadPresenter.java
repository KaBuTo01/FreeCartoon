package com.example.dome.ui.read;

import com.example.dome.ui.recommend.RcdContract;

import java.io.IOException;

/**
 * @ClassName ReadPresenter
 * @Author name
 * @Date 2023/2/15
 * @Description
 */
public class ReadPresenter implements ReadContract.IReadPresenter {
    private ReadContract.IReadView IView;
    private ReadModel readModel;
    public ReadPresenter(ReadContract.IReadView IView){
        this.IView = IView;
        readModel = new ReadModel();
    }
    @Override
    public void getRead(String url) {
        readModel.getRead(url, new RcdContract.IRecModelRec.onCallBack() {
            @Override
            public void onSuccess(String responseMsg) {
                IView.onReadSuccess(responseMsg);
            }
            @Override
            public void onFailure(IOException e) {
                IView.onReadFail(e);
            }
        });
    }
}
