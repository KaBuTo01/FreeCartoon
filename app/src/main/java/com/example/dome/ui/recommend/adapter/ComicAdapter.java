package com.example.dome.ui.recommend.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dome.R;
import com.example.dome.bean.ComicBean;

import java.util.List;

/**
 * @ClassName ComicAdapter
 * @Author name
 * @Date 2023/2/14
 * @Description
 */
public class ComicAdapter extends BaseAdapter {
    private List<ComicBean> list;
    private Context mContext;
    private OnItemClickListener onItemClickListener;
    public ComicAdapter(List<ComicBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }
    public interface OnItemClickListener {
        void onItemClick(String url);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.comic_item,null);
        ImageView image = convertView.findViewById(R.id.image);
        TextView comic_name = convertView.findViewById(R.id.comic_name);
        TextView name = convertView.findViewById(R.id.name);
        Glide.with(mContext)
                .load(list.get(position).getImageUrl())
                .into(image);
        comic_name.setText(list.get(position).getComicName());
        name.setText(list.get(position).getName());
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onItemClickListener.onItemClick(list.get(position).getUrl());
            }
        });
        return convertView;
    }

}
