package com.example.dome.ui.ranking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dome.R;
import com.example.dome.bean.BookList;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BookAbapter
 * @Author name
 * @Date 2023/2/16
 * @Description
 */
public class BookAdapter extends BaseAdapter {
    private List<BookList> data = new ArrayList<>();
    private Context mContext;
    private OnItemClick onClick;
    public BookAdapter( Context mContext) {
        this.mContext = mContext;
    }
    public void UpData(List<BookList> data){
        this.data.clear();
        this.data = data;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public interface  OnItemClick {
        void onItemClick(String url,int position);
        void onImageClick(String url,int position);
    }
    public void setOnClick(OnItemClick onClick){
        this.onClick = onClick;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView!=null){
            view = convertView;
        }else{
            view = LayoutInflater.from(mContext).inflate(R.layout.book_item,null);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null){
            holder = new ViewHolder();
            holder.img = view.findViewById(R.id.img);
            holder.name = view.findViewById(R.id.name);
            holder.text = view.findViewById(R.id.text);
            holder.tex1 = view.findViewById(R.id.text1);
            holder.tex2 = view.findViewById(R.id.text2);
            holder.delete_book = view.findViewById(R.id.delete_book);
        }
        Glide.with(mContext)
                .load(data.get(position).getImageUrl())
                .into(holder.img);
        holder.name.setText(data.get(position).getName());
        holder.text.setText(data.get(position).getText1());
        holder.tex1.setText(data.get(position).getText2());
        holder.tex2.setText(data.get(position).getText3());
        holder.delete_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(data.get(position).getUrl(),position);
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onImageClick(data.get(position).getUrl(),position);
            }
        });
        return view;
    }
    class ViewHolder
     {
         ImageView img;
         TextView name;
         TextView text;
         TextView tex1;
         TextView tex2;
         ImageView delete_book;

     }
}
