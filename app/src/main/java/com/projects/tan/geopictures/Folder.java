package com.projects.tan.geopictures;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.List;

/**
 * Created by oudong on 23/01/2017.
 */

@Parcel
public class Folder {

    private String id;
    private String parentPath;
    private String path;
    private String name;
    private long coverId;
    private int count;
    private List<Picture> pictures;

    @ParcelConstructor
    public Folder() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCoverId() {
        return coverId;
    }

    public void setCoverId(long coverId) {
        this.coverId = coverId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "id='" + id + '\'' +
                ", parentPath='" + parentPath + '\'' +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", coverId=" + coverId +
                ", count=" + count +
                ", pictures=" + pictures.size() +
                '}';
    }
}
