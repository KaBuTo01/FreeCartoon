package com.example.dome.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.dome.network.service.DownloadCallBack;
import com.example.dome.network.service.OkhttpUit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName Download
 * @Author name
 * @Date 2023/2/21
 * @Description
 */
public class DownloadService extends IntentService {
    private String TAG = getPackageName();

    public DownloadService() {
        //必须实现父类的构造方法
        super("DownloadService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind");
        return super.onBind(intent);
    }


    @Override
    public void onCreate() {
        System.out.println("onCreate");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d(TAG,"onStart");
        super.onStart(intent, startId);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void setIntentRedelivery(boolean enabled) {
        super.setIntentRedelivery(enabled);
        Log.d(TAG,"setIntentRedelivery");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Intent是从Activity发过来的，携带识别参数，根据参数不同执行不同的任务
        String action = intent.getExtras().getString("param");
        if (action.equals("oper1")) {
            Log.d(TAG,"Operation1");
        }else if (action.equals("oper2")) {
            Log.d(TAG,"Operation2");
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy");
        super.onDestroy();
    }

    public void download(){
        OkhttpUit.getInstance().downloadImage("https://otu1.dodomh.com/images/o/39/a3/1b0a8068f3ae90bd27c6cbba402d.jpg", new DownloadCallBack() {
            @Override
            public void onSuccess(InputStream inputStream) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                saveBitmap(DownloadService.this,bitmap);
            }

            @Override
            public void onFail(IOException e) {

            }
        });
    }
    private static final String SD_PATH = Environment.getExternalStorageDirectory() + "/免费漫画/download/葬/1/";
    private static final String IN_PATH = Environment.getExternalStorageDirectory() + "/免费漫画/download/";

    /**
     * 随机生产文件名
     *
     * @return
     */
    private static String generateFileName() {
        return UUID.randomUUID().toString();
    }
    /**
     * 保存bitmap到本地
     *
     * @param context
     * @param mBitmap
     * @return
     */
    public static String saveBitmap(Context context, Bitmap mBitmap) {
        String savePath;
        File filePic;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            savePath = SD_PATH;
        } else {
            savePath = context.getApplicationContext().getFilesDir()
                    .getAbsolutePath()
                    + IN_PATH;
        }
        try {
            filePic = new File(savePath + generateFileName() + ".jpg");
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        return filePic.getAbsolutePath();
    }


}
