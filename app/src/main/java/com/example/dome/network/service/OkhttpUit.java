package com.example.dome.network.service;

import android.app.Application;
import android.os.Handler;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @ClassName OkhttpUit
 * @Author name
 * @Date 2023/2/11
 * @Description
 */
public class OkhttpUit extends OkHttpClient {

    private static OkhttpUit  instance;
    private OkHttpClient okHttpClient = new OkHttpClient();
    private Handler handler = new Handler();
    public static OkhttpUit getInstance() {
        if (instance == null){
            synchronized (OkhttpUit.class){
                if (instance == null){
                    return new OkhttpUit();
                }
            }
        }
        return instance;
    }
    public void downloadImage(String url,DownloadCallBack downloadCallBack){
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        downloadCallBack.onFail(e);
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final InputStream data = response.body().byteStream();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        downloadCallBack.onSuccess(data);
                    }
                });

            }
        });
    }
    public void doGet(String url,callback callback){
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFail(e);
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String data = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(data);
                    }
                });

            }
        });
    }

    public void doPost(String url,String json,callback callback){
        //FormBody formBody = new FormBody.Builder().add("a","1").add("b","2").build();
//        File file = new File("E:\\ai\\aa.txt");
//        RequestBody requestBody = RequestBody.create(file,MediaType.parse("text/plain"));
//        MultipartBody multipartBody = new MultipartBody.Builder()
//                .addFormDataPart("b",file.getName(),requestBody ).build();
        RequestBody requestBody = RequestBody.create(json,MediaType.parse("application/json"));
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFail(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                callback.onSuccess(response.body().string());
            }
        });
    }

//    private static OkhttpUit instance;
//    private OkHttpClient okHttpClient = new OkHttpClient();
//    public static OkhttpUit getInstance(){
//        if (instance == null){
//            synchronized (OkhttpUit.class){
//                if (instance == null){
//                    return new OkhttpUit();
//                }
//            }
//        }
//        return instance;
//    }
    public void get(String url,callback callback){
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onFail(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                callback.onSuccess(response.body().string());
            }
        });
    }
//    public  void post(String url,String json,callback callback){
//        RequestBody requestBody = RequestBody.create(json,MediaType.parse("application/json"));
//        Request request =new Request.Builder().url(url).post(requestBody).build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                callback.onFail(e);
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                callback.onSuccess(response.body().string());
//            }
//        });
//    }
}
