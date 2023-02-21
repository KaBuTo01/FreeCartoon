package com.example.dome.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @ClassName BaseFragment
 * @Author name
 * @Date 2023/2/12
 * @Description
 */
abstract public class BaseFragment extends Fragment {
    protected View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(getLayout(),null);
        initView();
        return view;
    }

    abstract protected void initView();
    abstract protected int getLayout();
    public <T extends View> T findId(@IdRes int id){
        return view.findViewById(id);
    }
}
