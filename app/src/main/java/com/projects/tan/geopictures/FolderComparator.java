package com.projects.tan.geopictures;

import java.util.Comparator;

/**
 * Created by oudong on 28/01/2017.
 */

public class FolderComparator implements Comparator<Folder> {

    @Override
    public int compare(Folder o1, Folder o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
