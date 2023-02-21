package com.example.dome.ui.read;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.dome.R;
import com.example.dome.base.BaseActivity;
import com.example.dome.bean.ChapterList;
import com.example.dome.ui.read.adapter.ImageAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Read
 * @Author name
 * @Date 2023/2/15
 * @Description
 */
public class Read extends BaseActivity implements ReadContract.IReadView, View.OnClickListener {

    private List<ChapterList> list;
    private int position;
    private TextView top_name;
    private ReadPresenter readPresenter;
    private RecyclerView recycler_view;
    private ImageView get_back;
    private ImageAdapter imageAdapter;

    @Override
    protected void initView() {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        list = (List<ChapterList>)bundle.getSerializable("list");
        position = bundle.getInt("position");
        top_name = findId(R.id.top_name);
        top_name.setText(list.get(position).getName());
        recycler_view = findId(R.id.recycler);
        get_back = findId(R.id.get_back);
        get_back.setOnClickListener(this);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        readPresenter = new ReadPresenter(this);
        readPresenter.getRead(list.get(position).getUrl());
        imageAdapter = new ImageAdapter(Read.this);
        recycler_view.setAdapter(imageAdapter);
        recycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                boolean b = recyclerView.canScrollVertically(1);
                if (!b){
                    position++;
                    readPresenter.getRead(list.get(position).getUrl());
                    top_name.setText(list.get(position).getName());
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.read_activity;
    }

    @Override
    public void onReadSuccess(String data) {
        Log.e("0000000",data);
        Document doc = Jsoup.parse(data);
        imageAdapter.allAdd(getImageList(doc));
    }

    @Override
    public void onReadFail(IOException e) {

    }
    private List<String> getImageList(Document doc){
        Elements elements = doc.select(".scroll-item");
        List<String> image_list = new ArrayList<>();
        for (Element data1:
                elements) {
            String image = data1.select("img").attr("data-src");
            if (!image.equals("")) {
                image_list.add(image);
            }
        }
        image_list.add(0,doc.select("#image").attr("src"));
        Log.e("11111",image_list.get(0));
        return image_list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_back:
                finish();
                break;
        }
    }
}
