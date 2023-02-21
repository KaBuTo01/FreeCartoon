package com.example.dome.ui.search.adapter;

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
import com.example.dome.bean.SearchList;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SearchAdapter
 * @Author name
 * @Date 2023/2/15
 * @Description
 */
public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<SearchList> data = new ArrayList<>();
    private Context mContext;
    final static private int LAST = 1;
    final static private int STANDARD = -1;
    private OnClickItem onClickItem;
    public SearchAdapter(Context mContext,List<SearchList> data){
        this.mContext = mContext;
        this.data =data;
    }
    public SearchAdapter(Context mContext){
        this.mContext = mContext;
    }
    public void allAdd(List<SearchList> mData) {
        this.data.addAll(mData);
        notifyDataSetChanged();
    }
    public interface OnClickItem{
        void onClickLItem(String url ,int position);
    }
    public void setOnClickItem(OnClickItem onClickItem){
        this.onClickItem = onClickItem;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder =null;
        switch(viewType){
            case STANDARD:
                View view = LayoutInflater.from(mContext).inflate(R.layout.search_item,null);
                viewHolder = new Standard(view);
                break;
            case LAST:
                View view1 = LayoutInflater.from(mContext).inflate(R.layout.last_loading_item,null);
                viewHolder = new LAST(view1);
                break;
        }


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            switch (getItemViewType(position)){
                case STANDARD:
                    Standard standard = (Standard) holder;
                    BinSetStandard(standard,position);
                    break;
                case LAST:

                    break;
            }

    }
    private void BinSetStandard(Standard holder, int position){
        holder.name.setText(data.get(position).getName());
        holder.text.setText(data.get(position).getText1());
        holder.text1.setText(data.get(position).getText2());
        holder.text2.setText(data.get(position).getText3());
        Glide.with(mContext)
                .load(data.get(position).getImageUrl())
                .into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClickLItem(data.get(position).getUrl(),position);
            }
        });
    }
    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount()-1){
            return LAST;
        }
        return STANDARD;
    }

    @Override
    public int getItemCount() {
        return data.size()+1;
    }
    class LAST extends ViewHolder{
        public LAST(@NonNull View itemView) {
            super(itemView);
        }
    }
    class Standard extends ViewHolder {
        private  ImageView img;
        private  TextView name;
        private  TextView text;
        private  TextView text1;
        private  TextView text2;
        public Standard(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            text = itemView.findViewById(R.id.text);
            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {



        public ViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
