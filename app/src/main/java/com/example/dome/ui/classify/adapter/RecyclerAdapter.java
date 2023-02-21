package com.example.dome.ui.classify.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dome.R;
import com.example.dome.bean.ComicBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RecyclerAdapter
 * @Author name
 * @Date 2023/2/18
 * @Description
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ComicBean> list = new ArrayList<>();
    private OnclickItem onclickItem;
    public RecyclerAdapter(Context mContext, List<ComicBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    public RecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void notifyAddData(List<ComicBean> list){
        this.list = list;
        notifyDataSetChanged();
    }
    public interface OnclickItem{
        void OnclickItem(String url,int position);
    }
    public void setOnclickItem(OnclickItem onclickItem){
        this.onclickItem = onclickItem;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(mContext).inflate(R.layout.comic_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        BindViewHolder(viewHolder,position);

    }
    public void BindViewHolder(ViewHolder holder,int position){
        Glide.with(mContext)
                .load(list.get(position).getImageUrl())
                .into(holder.image);
        holder.comic_name.setText(list.get(position).getComicName());
        holder.name.setText(list.get(position).getName());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickItem.OnclickItem(list.get(position).getUrl(),position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView comic_name ;
        private TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            comic_name = itemView.findViewById(R.id.comic_name);
            name = itemView.findViewById(R.id.name);
        }
    }
}
