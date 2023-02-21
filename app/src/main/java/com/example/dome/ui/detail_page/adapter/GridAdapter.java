package com.example.dome.ui.detail_page.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dome.R;
import com.example.dome.bean.ChapterList;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GridAdapter
 * @Author name
 * @Date 2023/2/14
 * @Description
 */
public class GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ChapterList> data = new ArrayList<>();
    private Context mContext;
    private int num;
    private OnChapterList listener;
    public void all(List<ChapterList> mData,int num){
        this.num = num;
        data.addAll(mData);
        notifyDataSetChanged();

    }
    public interface OnChapterList{
        void Chapter(List<ChapterList> data,int position);
        void addChapter();
    }
    public void setOnChapterList(OnChapterList listener){
        this.listener = listener;
    }
    public GridAdapter(List<ChapterList> data, Context mContext,int num) {
        this.data = data;
        this.mContext = mContext;
        this.num = num;
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
            if (num == 1&& position== getItemCount()-1){
                viewHolder.text.setText("点击加载更多...");
            }else {
                viewHolder.text.setText(data.get(position).getName());
            }
        click(viewHolder,position);
    }
    public void click(ViewHolder viewHolder,int position){
        viewHolder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num != 1 || position !=getItemCount()-1){
                    listener.Chapter(data,position);
                }else {
                    listener.addChapter();
                }

            }
        });
    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (num == 1){
            return data.size()+1;
        }
        return data.size();
    }
    class ViewHolder extends  RecyclerView.ViewHolder {

        private  TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
        }
    }
}
