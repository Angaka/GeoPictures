package com.projects.tan.geopictures;

import android.location.Location;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by oudong on 23/01/2017.
 */

@Parcel
public class Picture {

    private String id;
    private String path;
    private String name;
    private String date;
    private Location location;

    @ParcelConstructor
    public Picture() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id='" + id + '\'' +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", location=" + location +
                '}';
    }
}
