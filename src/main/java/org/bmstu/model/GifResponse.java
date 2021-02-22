package org.bmstu.model;

import java.util.ArrayList;

public class GifResponse {
    private ArrayList<GifObject> data;

    public ArrayList<GifObject> getData() {
        return data;
    }

    public GifObject getGifObject() {
        return data.get(0);
    }
}
