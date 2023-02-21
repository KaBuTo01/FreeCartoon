package com.example.dome.ui.classify.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dome.R;
import com.example.dome.bean.ClassIfMap;
import com.example.dome.ui.detail_page.adapter.GridAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName NavGridAdapter
 * @Author name
 * @Date 2023/2/18
 * @Description
 */
public class NavGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ClassIfMap> data = new ArrayList<>();
    private Context mContext;
    private OnClickItem onClickItem;
    private int type;
    public NavGridAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public NavGridAdapter(){
    }
    public void UpDataNav(List<ClassIfMap> mData,int type){
        this.type = type;
        this.data = mData;
        notifyDataSetChanged();
    }
    public interface OnClickItem{
       void OnClickItem(ClassIfMap classIfMap,int type);
    }
    public void setOnClickItem(OnClickItem onClickItem){
        this.onClickItem = onClickItem;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.chapter_item,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.text.setText(data.get(position).getName());
            viewHolder.text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItem.OnClickItem(data.get(position),type);
                }
            });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
        }
    }
}
