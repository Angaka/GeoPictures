package com.projects.tan.geopictures;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {

    private Context context;
    private List<Picture> pictures;

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview_picture)
        ImageView imgPicture;
        @BindView(R.id.textview_picture_date_added)
        TextView tvPictureDateAdded;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public PictureAdapter(Context context, List<Picture> pictures) {
        this.context = context;
        this.pictures = pictures;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture, parent, false);
        int height = parent.getMeasuredHeight() / 4;
        view.setMinimumHeight(height);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picture picture = pictures.get(position);

        System.out.println(picture.getPath());
        Glide.with(context)
            .load(picture.getPath())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(true)
            .into(holder.imgPicture);
        holder.tvPictureDateAdded.setText(picture.getDate());
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }
}
