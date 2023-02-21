package com.example.dome.ui.detail_page;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dome.R;
import com.example.dome.base.BaseActivity;
import com.example.dome.bean.BookList;
import com.example.dome.bean.ChapterList;
import com.example.dome.service.DownloadService;
import com.example.dome.sql.DBBook;
import com.example.dome.ui.detail_page.adapter.GridAdapter;
import com.example.dome.ui.read.Read;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DetailPage
 * @Author name
 * @Date 2023/2/14
 * @Description
 */
public class DetailPage extends BaseActivity implements DPContract.IDPView, View.OnClickListener {


    private DPPresenter dp_presenter;
    private TextView top_name,text,text1,text2,text3;
    private ImageView img;
    private TextView introduce;
    private ImageView btn_down;
    private ImageView btn_up;
    private int line;
    private RecyclerView recycler_view;
    private String url;
    private ImageView get_back;
    private DBBook dbBook;
    private CardView add_book;
    private CardView delete_book;
    private CardView download;
    private boolean isRefuse;
    private String name1;

    @Override
    protected void initView() {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        url = bundle.getString("url");
        dbBook = new DBBook(this);
        add_book = findId(R.id.add);
        top_name = findId(R.id.top_name);//名字
        text = findId(R.id.text);//作者名字
        text1 = findId(R.id.text1);//类型标签
        text2 = findId(R.id.text2);//状态标签
        text3 = findId(R.id.text3);//最后的更新时间
        img = findId(R.id.img);//封面
        introduce = findId(R.id.introduce);//简介
        recycler_view = findId(R.id.recycler_view);
        btn_down = findId(R.id.btn_down);
        btn_up = findId(R.id.btn_up);
        get_back = findId(R.id.get_back);
        get_back.setOnClickListener(this);
        btn_down.setOnClickListener(this);
        btn_up.setOnClickListener(this);
        dp_presenter = new DPPresenter(this);
        dp_presenter.getDetail(url);
        delete_book = findId(R.id.delete_book);
        //下载
        download = findId(R.id.download);
        download.setOnClickListener(this);
        if (dbBook.isUrl(url)){
            add_book.setVisibility(View.GONE);
            delete_book.setVisibility(View.VISIBLE);
        }
        delete_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbBook.deleteBook(url);
                add_book.setVisibility(View.VISIBLE);
                delete_book.setVisibility(View.GONE);
            }
        });
        Toast.makeText(DetailPage.this,""+dbBook.isUrl(url),Toast.LENGTH_SHORT).show();

    }
    @Override
    protected int getLayout() {
        return R.layout.detail_page_activity;
    }

    @Override
    public void onDetailSuccess(String data) {
        Document doc = Jsoup.parse(data);
        name1 = doc.select("#comicName").text();
        top_name.setText(name1);
        setStatus(doc);
        chapter(doc);
    }

    @Override
    public void onDetailFail(IOException e) {

    }
    private void chapter(Document doc){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);
        recycler_view.setLayoutManager(gridLayoutManager);
        Elements elements = doc.select("#chapter-list-1 >li");
        List<ChapterList> list = new ArrayList<>();
        for (Element data:
                elements) {
            ChapterList chapterList = new ChapterList();
            String url = data.select("a").attr("href");
            chapterList.setUrl("https://m.dushimh.com"+url);
            String name = data.select("a>span").text();
            chapterList.setName(name);
            list.add(chapterList);
        }
        List<ChapterList> list_items = new ArrayList<>();
        if (list.size()>20){
            list_items = list.subList(0,19);
        }
        GridAdapter gridAdapter = new GridAdapter(list_items,DetailPage.this,1);
        recycler_view.setAdapter(gridAdapter);
        gridAdapter.setOnChapterList(new GridAdapter.OnChapterList() {
            @Override
            public void Chapter(List<ChapterList> data, int position) {
                Toast.makeText(DetailPage.this,"点击了"+position+"条目"+data.get(position).getName(),Toast.LENGTH_SHORT).show();
                ArrayList<ChapterList> arr = new ArrayList<>();
                for(int i=0;i<data.size();i++){
                    arr.add(data.get(i));
                }
                Intent intent = new Intent(DetailPage.this,Read.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list",arr);
                bundle.putInt("position",position);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void addChapter() {
                gridAdapter.all(list,0);
            }
        });

    }
    private void setStatus(Document doc) {
        introduce.setText(doc.select("#simple-des").text());
        line = introduce.getLineCount();
        introduce.setLines(2);
        String imageUrl = doc.select("#Cover > img").attr("src");
        String name = doc.select("div.sub_r.autoHeight>p:nth-child(1)").text();
        String text_one = doc.select("div.sub_r.autoHeight > p:nth-child(2)").text();
        String text_tow = doc.select("div.sub_r.autoHeight > p:nth-child(3)").text();
        String text_three = doc.select("div.sub_r.autoHeight > p:nth-child(4)").text();
        Glide.with(this)
             .load(imageUrl)
             .into(img);
        text.setText(name);
        text1.setText(text_one);
        text2.setText(text_tow);
        text3.setText(text_three);
        add_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 dbBook.addBook(new BookList(null, imageUrl, name, url, text_one, text_tow, text_three));
                delete_book.setVisibility(View.VISIBLE);
                add_book.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_down:
                btn_down.setVisibility(View.GONE);
                btn_up.setVisibility(View.VISIBLE);
                introduce.setLines(line);
                break;
            case R.id.btn_up:
                btn_up.setVisibility(View.GONE);
                btn_down.setVisibility(View.VISIBLE);
                introduce.setLines(2);
                break;
            case R.id.download:
                if (Build.VERSION.SDK_INT >= 23) {// 6.0
                    String[] perms = {
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_PHONE_STATE};
                    for (String p : perms) {
                        int f = ContextCompat.checkSelfPermission(DetailPage.this, p);
                        Log.d("---", String.format("%s - %d", p, f));
                        if (f != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(perms, 0XCF);
                            break;
                        }
                    }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && !isRefuse) {// android 11  且 不是已经被拒绝
                    // 先判断有没有权限
                    if (!Environment.isExternalStorageManager()) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                        intent.setData(Uri.parse("package:" + getPackageName()));
                        startActivityForResult(intent, 1024);
                    }
                }
                if (!isRefuse){
                    //获取外部存储路径
//                    String storage = Environment.getExternalStorageDirectory().getPath() + "/免费漫画/download/"+name1;
//                    File dirFile = new File(storage);
//                    Log.d("dirFile", "" + dirFile);
//                    if (!dirFile.exists()) {
//                        boolean mkdirs = dirFile.mkdirs();
//                        if (!mkdirs) {
//                            Log.i("TAG", "文件夹创建失败");
//                        } else {
//                            Log.i("TAG", "文件夹创建成功");
//                        }
//                    }
                   }
                    Intent intent = new Intent(DetailPage.this, DownloadService.class);
                    startService(intent);

                break;
            case R.id.get_back:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1024 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 检查是否有权限
            if (Environment.isExternalStorageManager()) {
                isRefuse = false;
                // 授权成功
            } else {
                isRefuse = true;
                // 授权失败
            }
        }
    }

}
