package com.projects.tan.geopictures;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder> {

    private Context context;
    private List<Folder> folders;

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview_folder)
        ImageView imgFolder;
        @BindView(R.id.textview_folder_title)
        TextView tvFolderTitle;
        @BindView(R.id.textview_folder_count)
        TextView tvFolderCount;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public FolderAdapter(Context context, List<Folder> folders) {
        this.context = context;
        this.folders = folders;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_folder, parent, false);
        int height = parent.getMeasuredHeight() / 4;
        view.setMinimumHeight(height);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Folder folder = folders.get(position);

        System.out.println(folder.getPath());
          Glide.with(context)
                .load(folder.getPath())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .into(holder.imgFolder);
        holder.tvFolderTitle.setText(folder.getName());
        holder.tvFolderCount.setText(Integer.valueOf(folder.getCount()) + " photos");
    }

    @Override
    public int getItemCount() {
        return folders.size();
    }
}
