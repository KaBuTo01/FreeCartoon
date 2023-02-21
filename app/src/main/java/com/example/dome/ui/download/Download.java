package com.example.dome.ui.download;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.dome.R;
import com.example.dome.base.BaseFragment;
import com.example.dome.network.service.DownloadCallBack;
import com.example.dome.network.service.OkhttpUit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ClassName Over
 * @Author name
 * @Date 2023/2/12
 * @Description
 */
public class Download extends BaseFragment {
    private ImageView image_view; // 声明一个图像视图对象
    private Button btn_file_download;
    @Override
    protected void initView() {
        image_view = findId(R.id.image_view);
        btn_file_download = findId(R.id.btn_file_download);
        btn_file_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkhttpUit.getInstance().downloadImage("https://otu1.dodomh.com/images/o/39/a3/1b0a8068f3ae90bd27c6cbba402d.jpg", new DownloadCallBack() {
                    @Override
                    public void onSuccess(InputStream inputStream) {
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        Glide.with(getContext()).load(bitmap).into(image_view);
                        saveBitmap(getContext(),bitmap);
                    }

                    @Override
                    public void onFail(IOException e) {

                    }
                });

            }
        });

    }

    /**
     * 保存指纹图片
     *
     * @param bitmap
     */
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




    @Override
    protected int getLayout() {
        return R.layout.fragment_over;
    }
}
