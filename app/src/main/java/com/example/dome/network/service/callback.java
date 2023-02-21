package com.example.dome.network.service;

import java.io.IOException;

/**
 * @ClassName callback
 * @Author name
 * @Date 2023/2/11
 * @Description
 */
public interface callback {
        void onSuccess(String response);
        void onFail(IOException e);
}
