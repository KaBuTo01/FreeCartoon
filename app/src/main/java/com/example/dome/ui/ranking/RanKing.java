package com.example.dome.ui.ranking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dome.R;
import com.example.dome.base.BaseFragment;
import com.example.dome.bean.BookList;
import com.example.dome.sql.DBBook;
import com.example.dome.ui.detail_page.DetailPage;
import com.example.dome.ui.search.SearchActivity;
import com.example.dome.view.NoScrollListView;

import java.util.List;

/**
 * @ClassName RanKing
 * @Author name
 * @Date 2023/2/12
 * @Description
 */
public class RanKing extends BaseFragment {

    private TextView txt;
    private DBBook dbBook;
    private NoScrollListView listView;
    private BookAdapter bookAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_ran_king;
    }

    @Override
    protected void initView() {
        dbBook = new DBBook(getContext());
        listView = findId(R.id.list_view);
        //List<BookList> list = dbBook.getBook();
        bookAdapter = new BookAdapter(getContext());
        listView.setAdapter(bookAdapter);
        bookAdapter.UpData(dbBook.getBook());
        bookAdapter.setOnClick(new BookAdapter.OnItemClick() {
            @Override
            public void onItemClick(String url, int position) {
                Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
                dbBook.deleteBook(url);
                bookAdapter.UpData(dbBook.getBook());
            }

            @Override
            public void onImageClick(String url, int position) {
                Intent intent = new Intent(getContext(), DetailPage.class);
                Bundle bundle = new Bundle();
                bundle.putString("url",url);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        bookAdapter.UpData(dbBook.getBook());

    }

    @Override
    public void onResume() {
        super.onResume();
        bookAdapter.UpData(dbBook.getBook());
    }
}
