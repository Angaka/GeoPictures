package com.projects.tan.geopictures;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rohit.recycleritemclicksupport.RecyclerItemClickSupport;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FolderFragment extends Fragment {

    static final String TAG = "FolderFragment";

    @BindView(R.id.recylerview_folders)
    RecyclerView rvFolders;

    private List<Folder> folders;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_folder, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        folders = MediaStoreManager.getPictureFolders(getContext());

        for (Folder folder : folders) {
            System.out.println(folder.toString());
        }

        FolderAdapter adapter = new FolderAdapter(getContext(), folders);
        rvFolders.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvFolders.setHasFixedSize(true);
        rvFolders.setAdapter(adapter);
        RecyclerItemClickSupport.addTo(rvFolders).setOnItemClickListener(new RecyclerItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent pictureIntent = new Intent(getContext(), PictureActivity.class);
                pictureIntent.putExtra(MediaStoreManager.FOLDER, Parcels.wrap(folders.get(position)));
                startActivity(pictureIntent);
            }
        });

    }
}
