package com.projects.tan.geopictures;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oudong on 03/02/2017.
 */

public class PictureDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imageview_picture)
    ImageView imgPicture;
    @BindView(R.id.mapview_picture)
    MapView mapView;

    private Picture picture;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);
        ButterKnife.bind(this);

        picture = Parcels.unwrap(getIntent().getParcelableExtra(MediaStoreManager.PICTURE));

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(picture.getName());

        Glide.with(this)
                .load(picture.getPath())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .into(imgPicture);

        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = new LatLng(picture.getLocation().getLatitude(), picture.getLocation().getLongitude());

        googleMap.setMyLocationEnabled(true);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));

        googleMap.addMarker(new MarkerOptions()
            .title(picture.getName())
            .position(latLng));
    }
}
