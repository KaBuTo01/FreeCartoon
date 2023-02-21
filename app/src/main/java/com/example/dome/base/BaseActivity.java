package com.example.dome.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dome.R;

/**
 * @ClassName BaseActivity
 * @Author name
 * @Date 2023/2/12
 * @Description
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.Theme_Dome);
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
    }
    protected abstract void initView();
    protected abstract  int getLayout();
    protected   <T extends View> T findId(@IdRes int id){
        return findViewById(id);
    }
}
