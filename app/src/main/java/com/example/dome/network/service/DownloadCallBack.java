package com.example.dome.network.service;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName DownloadCallBack
 * @Author name
 * @Date 2023/2/20
 * @Description
 */
public interface DownloadCallBack {
    void onSuccess(InputStream inputStream);
    void onFail(IOException e);
}
