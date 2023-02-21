package com.example.dome.ui.recommend;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dome.R;
import com.example.dome.base.BaseFragment;
import com.example.dome.bean.BannerBean;
import com.example.dome.bean.ComicBean;
import com.example.dome.ui.detail_page.DetailPage;
import com.example.dome.ui.recommend.adapter.ComicAdapter;
import com.example.dome.ui.recommend.adapter.ImageTitleNumAdapter;
import com.example.dome.ui.recommend.view.MyGridView;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName rcod
 * @Author name
 * @Date 2023/2/12
 * @Description
 */
public class Rcd extends BaseFragment implements RcdContract.IRcdView, ImageTitleNumAdapter.OnBanner, ComicAdapter.OnItemClickListener {


    private RcdPresenter rcdPresenter;
    private Banner banner;
    private ImageView img,img1,img2,img3;
    private GridView gridView,gridView1,gridView2,gridView3;
    private ImageTitleNumAdapter imageTitleNumAdapter;

    @Override
    protected void initView() {
        banner = findId(R.id.banner);
        img = findId(R.id.img);
        img1 = findId(R.id.img1);
        img2 = findId(R.id.img2);
        img3 = findId(R.id.img3);

        gridView = findId(R.id.gridview);
        gridView1 = findId(R.id.gridview1);
        gridView2 = findId(R.id.gridview2);
        gridView3 = findId(R.id.gridview3);
        rcdPresenter = new RcdPresenter(this);
        rcdPresenter.getHome();
    }
    @Override
    public void onHomeSuccess(String data) {
        //轮播图
        List<BannerBean> list = new ArrayList<>();
        //轮播图
        Document doc = Jsoup.parse(data);
        Elements elements = doc
                .select("#w0 > div");
        for (Element item:
                elements) {
            BannerBean bannerList = new BannerBean();
            String image = item.select("div > a> img").attr("src");
            bannerList.setImageUrl(image);
            String title = item.select("div > div").text();
            bannerList.setTitle(title);
            String url = item.select("div > a").attr("href");
            bannerList.setUrl("https://m.dushimh.com/"+url);
            list.add(bannerList);

        }
        imageTitleNumAdapter = new ImageTitleNumAdapter(list,getContext());
        banner.setAdapter(imageTitleNumAdapter);
        imageTitleNumAdapter.setOnBanner(this);

        //近期必看
        Glide.with(getContext())
                .load("https://m.dushimh.com/assets/d6ae7e24/images/icon_h2_1.png")
                .into(img);
        gridView.setAdapter(getAdapter(data,2));
        //猜你喜欢
        Glide.with(getContext())
                .load("https://m.dushimh.com/assets/d6ae7e24/images/icon_h2_3.png")
                .into(img1);
        gridView1.setAdapter(getAdapter(data,3));
        //国漫也精彩
        Glide.with(getContext())
                .load("https://m.dushimh.com/assets/d6ae7e24/images/icon_h2_5.png")
                .into(img2);
        gridView2.setAdapter(getAdapter(data,4));
        //国漫也精彩
        Glide.with(getContext())
                .load("https://m.dushimh.com/assets/d6ae7e24/images/icon_h2_7.png")
                .into(img3);
        gridView3.setAdapter(getAdapter(data,5));

    }
    private ComicAdapter getAdapter(String data,int w){

        List<ComicBean> comic_list = new ArrayList<>();
        Document doc = Jsoup.parse(data);
        Elements elements1 = doc
                .select("#w"+w+" > li");
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
        ComicAdapter comicAdapter = new ComicAdapter(comic_list,getContext());
        comicAdapter.setOnItemClickListener(this);
        return comicAdapter;

    }
    @Override
    public void onHomeFail(IOException e) {

    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_rcd;
    }


    @Override
    public void mBanner(int position, String url) {
        Intent intent = new Intent(getContext(), DetailPage.class);
        Bundle bundle = new Bundle();
        bundle.putString("url",url);

        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void onItemClick(String url) {
        Intent intent = new Intent(getContext(), DetailPage.class);
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
