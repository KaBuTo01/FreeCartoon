package com.example.dome.ui.upd;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dome.R;
import com.example.dome.base.BaseFragment;
import com.example.dome.bean.SearchList;
import com.example.dome.ui.detail_page.DetailPage;
import com.example.dome.ui.search.adapter.SearchAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Updata
 * @Author name
 * @Date 2023/2/12
 * @Description
 */
public class UpData extends BaseFragment implements UDContract.IUDView {

    int page;
    private UDPresenter udpresenter;
    private RecyclerView recycler_view;
    private SearchAdapter searchAdapter;

    @Override
    protected void initView() {
        page =1;
        udpresenter = new UDPresenter(this);
        udpresenter.getData(page);
        recycler_view = findId(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler_view.setHasFixedSize(true);
        recycler_view.setVisibility(View.VISIBLE);
        searchAdapter = new SearchAdapter(getContext());
        recycler_view.setAdapter(searchAdapter);
        recycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                boolean b = recyclerView.canScrollVertically(1);
                if (!b){
                    page++;
                    udpresenter.getData(page);
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
        return R.layout.fragment_up_data;
    }

    @Override
    public void onUDSuccess(String data) {
        Document doc = Jsoup.parse(data);
        searchAdapter.setOnClickItem(new SearchAdapter.OnClickItem() {
            @Override
            public void onClickLItem(String url, int position) {
                Intent intent = new Intent(getContext(), DetailPage.class);
                Bundle bundle = new Bundle();
                bundle.putString("url",url);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        searchAdapter.allAdd(getData(doc));
    }

    @Override
    public void onUDFail(IOException e) {

    }
    private List<SearchList> getData(Document doc){
        List<SearchList> searchLists = new ArrayList<>();
        Elements elements = doc.select(".UpdateList>div");
        for (Element data:
                elements) {
            SearchList searchList = new SearchList();
            String ImageUrl = data.select(".itemImg>a>img").attr("src");
            searchList.setImageUrl(ImageUrl);
            String name = data.select(".itemTxt>a").text();
            searchList.setName(name);
            String url = data.select(".itemImg>a").attr("href");
            searchList.setUrl(url);
            String text1 = data.select(".itemTxt > p:nth-child(2)").text();
            searchList.setText1(text1);
            String text2 = data.select(".itemTxt > p:nth-child(3)").text();
            searchList.setText2(text2);
            String text3 = data.select(".itemTxt > p:nth-child(4)").text();
            searchList.setText3(text3);
            searchLists.add(searchList);
        }

        return searchLists;
    }
}
