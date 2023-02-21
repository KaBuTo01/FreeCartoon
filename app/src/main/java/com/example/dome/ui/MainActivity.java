package com.example.dome.ui;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.dome.R;
import com.example.dome.base.BaseActivity;
import com.example.dome.ui.classify.Classify;
import com.example.dome.ui.download.Download;
import com.example.dome.ui.ranking.RanKing;
import com.example.dome.ui.recommend.Rcd;
import com.example.dome.ui.search.SearchActivity;
import com.example.dome.ui.upd.UpData;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private TextView rcd,upd,rk,cfy,ov;
    private Fragment[] fragments;
    private int lastFragmentIndex=0;
    private ImageView search;
    private EditText input;



    @Override
    protected void initView() {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        navigate();
        search = findId(R.id.search);
        input = findId(R.id.input);
        search.setOnClickListener(this);
    }
    void navigate(){
        rcd = findId(R.id.rcd);
        upd = findId(R.id.upd);
        rk = findId(R.id.rk);
        cfy = findId(R.id.cfy);
        ov = findId(R.id.ov);
        fragments = new Fragment[]{
                new Rcd(),
                new UpData(),
                new RanKing(),
                new Classify(),
                new Download()
        };
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout,fragments[0])
                .commit();
        rcd.setOnClickListener(this);
        upd.setOnClickListener(this);
        rk.setOnClickListener(this);
        cfy.setOnClickListener(this);
        ov.setOnClickListener(this);
        rcd.setTextColor(Color.parseColor("#0090ff"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rcd:
                switchFragment(0);
                clerColors();
                rcd.setTextColor(Color.parseColor("#0090ff"));
                break;
            case R.id.upd:
                switchFragment(1);
                clerColors();
                upd.setTextColor(Color.parseColor("#0090ff"));
                break;
            case R.id.rk:
                switchFragment(2);
                clerColors();
                rk.setTextColor(Color.parseColor("#0090ff"));
                break;
            case R.id.cfy:
                switchFragment(3);
                clerColors();
                cfy.setTextColor(Color.parseColor("#0090ff"));
                break;
            case R.id.ov:
                switchFragment(4);
                clerColors();
                ov.setTextColor(Color.parseColor("#0090ff"));
                break;
            case R.id.search:
                if (!input.getText().toString().equals("")){
                    Intent intent = new Intent(this, SearchActivity.class);
                    intent.putExtra("input",input.getText().toString());
                    startActivity(intent);
                }

                break;
        }
    }
    private void clerColors(){
        rcd.setTextColor(Color.parseColor("#636165"));
        upd.setTextColor(Color.parseColor("#636165"));
        rk.setTextColor(Color.parseColor("#636165"));
        cfy.setTextColor(Color.parseColor("#636165"));
        ov.setTextColor(Color.parseColor("#636165"));
    }
    private void switchFragment(int to){
        if (lastFragmentIndex==to){
            return;
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (!fragments[to].isAdded()){
            fragmentTransaction.add(R.id.layout,fragments[to]);
        }else {
            fragmentTransaction.show(fragments[to]);
        }
        fragmentTransaction.hide(fragments[lastFragmentIndex]).commitAllowingStateLoss();
        lastFragmentIndex=to;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
}