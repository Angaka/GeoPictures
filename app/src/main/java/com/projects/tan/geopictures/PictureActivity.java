package com.projects.tan.geopictures;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rohit.recycleritemclicksupport.RecyclerItemClickSupport;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oudong on 29/01/2017.
 */

public class PictureActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recylerview_pictures)
    RecyclerView rvPictures;

    private Folder folder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        folder = Parcels.unwrap(getIntent().getParcelableExtra(MediaStoreManager.FOLDER));
    }

    @Override
    protected void onResume() {
        super.onResume();

        final List<Picture> pictures = MediaStoreManager.getPicturesByFolderId(this, folder.getId());
        PictureAdapter adapter = new PictureAdapter(this, pictures);

        rvPictures.setLayoutManager(new GridLayoutManager(this, 3));
        rvPictures.setHasFixedSize(true);
        rvPictures.setAdapter(adapter);
        RecyclerItemClickSupport.addTo(rvPictures).setOnItemClickListener(new RecyclerItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent pictureDetailIntent = new Intent(PictureActivity.this, PictureDetailActivity.class);
                pictureDetailIntent.putExtra(MediaStoreManager.PICTURE, Parcels.wrap(pictures.get(position)));
                startActivity(pictureDetailIntent);
            }
        });
    }
}
