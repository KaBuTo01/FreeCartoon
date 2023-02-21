package com.example.dome.ui.read.adapter;

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

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ImageAdapter
 * @Author name
 * @Date 2023/2/15
 * @Description
 */
public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> data = new ArrayList<>();
    private Context context;
    public void allAdd(List<String> data){
        this.data.addAll(data);

        notifyDataSetChanged();
    }
    public ImageAdapter(Context context){
        this.context = context;
    }
    public ImageAdapter(List<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_item,null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        Glide.with(context)
                .load(data.get(position))
                .into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{

         private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.zp);
        }
    }
}
