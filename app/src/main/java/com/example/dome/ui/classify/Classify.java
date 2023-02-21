package com.example.dome.ui.classify;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dome.R;
import com.example.dome.base.BaseFragment;
import com.example.dome.bean.ClassIfMap;
import com.example.dome.bean.ComicBean;
import com.example.dome.ui.classify.adapter.NavGridAdapter;
import com.example.dome.ui.classify.adapter.RecyclerAdapter;
import com.example.dome.ui.detail_page.DetailPage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Classify
 * @Author name
 * @Date 2023/2/12
 * @Description
 */
public class Classify extends BaseFragment implements View.OnClickListener, NavGridAdapter.OnClickItem, CIfContract.ICIfView, RecyclerAdapter.OnclickItem {
    //题材
    private List<ClassIfMap> theme = ClassIfUit.getTheme();
    //读者
    private List<ClassIfMap> reader = ClassIfUit.getReader();
    //进度
    private List<ClassIfMap> progress = ClassIfUit.getProgress();
    //地区
    private List<ClassIfMap>  region = ClassIfUit.getRegion();
    private TextView ti_cai;
    private TextView du_zhe;
    private TextView jin_du;
    private TextView di_qu;
    private RecyclerView nav_recycler_view;
    private NavGridAdapter navGridAdapter;
    private ImageView ti_cai_img;
    private ImageView du_zhe_img;
    private ImageView jin_du_img;
    private ImageView di_qu_img;
    private int ti_cai_selected,du_zhe_selected,jin_du_selected,di_qu_selected = 0;
    private RecyclerView recycler_view;
    private CIfPresenter cIfPresenter;
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void initView() {
        nav();
        recycler_view = findId(R.id.recycler_view);
        GridLayoutManager gridManager = new GridLayoutManager(getContext(),3);
        recycler_view.setLayoutManager(gridManager);


    }
    private void nav(){
        ti_cai = findId(R.id.ti_cai);
        du_zhe = findId(R.id.du_zhe);
        jin_du = findId(R.id.jin_du);
        di_qu = findId(R.id.di_qu);

        ti_cai_img = findId(R.id.ti_cai_img);
        du_zhe_img = findId(R.id.du_zhe_img);
        jin_du_img = findId(R.id.jin_du_img);
        di_qu_img = findId(R.id.di_qu_img);
        nav_recycler_view = findId(R.id.nav_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),4);
        nav_recycler_view.setLayoutManager(gridLayoutManager);
        navGridAdapter = new NavGridAdapter(getContext());
        nav_recycler_view.setAdapter(navGridAdapter);
        navGridAdapter.setOnClickItem(this);
        ti_cai.setOnClickListener(this);
        du_zhe.setOnClickListener(this);
        jin_du.setOnClickListener(this);
        di_qu.setOnClickListener(this);
        cIfPresenter = new CIfPresenter(this);
        cIfPresenter.getData("/list/");


    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_classify;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ti_cai:
                nav_recycler_view.setVisibility(View.VISIBLE);
                navGridAdapter.UpDataNav(theme,0);
                clearColorAndIcon();
                ti_cai.setTextColor(Color.parseColor("#0962EA"));
                Glide.with(getContext()).load(R.drawable.page_icon14).into(ti_cai_img);
                break;
            case R.id.du_zhe:
                nav_recycler_view.setVisibility(View.VISIBLE);
                navGridAdapter.UpDataNav(reader,1);
                clearColorAndIcon();
                du_zhe.setTextColor(Color.parseColor("#0962EA"));
                Glide.with(getContext()).load(R.drawable.page_icon14).into(du_zhe_img);
                break;
            case R.id.jin_du:
                nav_recycler_view.setVisibility(View.VISIBLE);
                navGridAdapter.UpDataNav(progress,2);
                clearColorAndIcon();
                jin_du.setTextColor(Color.parseColor("#0962EA"));
                Glide.with(getContext()).load(R.drawable.page_icon14).into(jin_du_img);
                break;
            case R.id.di_qu:
                nav_recycler_view.setVisibility(View.VISIBLE);
                navGridAdapter.UpDataNav(region,3);
                clearColorAndIcon();
                di_qu.setTextColor(Color.parseColor("#0962EA"));
                Glide.with(getContext()).load(R.drawable.page_icon14).into(di_qu_img);
                break;
        }
    }
   public void clearColorAndIcon(){
        ti_cai.setTextColor(Color.parseColor("#000000"));
       du_zhe.setTextColor(Color.parseColor("#000000"));
       jin_du.setTextColor(Color.parseColor("#000000"));
       di_qu.setTextColor(Color.parseColor("#000000"));
       Glide.with(getContext()).load(R.drawable.page_icon13).into(ti_cai_img);
       Glide.with(getContext()).load(R.drawable.page_icon13).into(du_zhe_img);
       Glide.with(getContext()).load(R.drawable.page_icon13).into(jin_du_img);
       Glide.with(getContext()).load(R.drawable.page_icon13).into(di_qu_img);
   }

    @Override
    public void OnClickItem(ClassIfMap classIfMap,int type) {
        Toast.makeText(getContext(),classIfMap.getHref(),Toast.LENGTH_SHORT).show();
        cIfPresenter.getData(classIfMap.getHref());
        switch (type){
            case 0:
                clearColorAndIcon();
                ti_cai.setText(classIfMap.getName());
                nav_recycler_view.setVisibility(View.GONE);
                break;
            case 1:
                clearColorAndIcon();
                du_zhe.setText(classIfMap.getName());
                nav_recycler_view.setVisibility(View.GONE);
                break;
            case 2:
                clearColorAndIcon();
                jin_du.setText(classIfMap.getName());
                nav_recycler_view.setVisibility(View.GONE);
                break;
            case 3:
                clearColorAndIcon();
                di_qu.setText(classIfMap.getName());
                nav_recycler_view.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onDataSuccess(String data) {
        Document doc = Jsoup.parse(data);
        recyclerAdapter = new RecyclerAdapter(getContext());
        recyclerAdapter.setOnclickItem(this);
        recyclerAdapter.notifyAddData(getData(doc));
        recycler_view.setAdapter(recyclerAdapter);

    }

    @Override
    public void onDataFail(IOException e) {

    }
    private List<ComicBean> getData(Document doc){
        List<ComicBean> comic_list = new ArrayList<>();
        Elements elements1 = doc
                .select("#comic-items > li");
        for (Element data1:
                elements1) {
            ComicBean comicBean = new ComicBean();
            String image = data1.select(" a.ImgA > img").attr("src");
            comicBean.setImageUrl(image);
            String title = data1.select("a.txtA").text();
            comicBean.setComicName(title);
            String url = data1.select("a.ImgA").attr("href");
            comicBean.setUrl(url);
            String name = data1.select("span").text();
            comicBean.setName(name);
            comic_list.add(comicBean);
        }
        return comic_list;
    }

    @Override
    public void OnclickItem(String url, int position) {
        Intent intent = new Intent(getContext(), DetailPage.class);
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
