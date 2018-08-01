package com.proapplab.imageupdownload.imageuploaddownload;

import android.content.Context;

/**
 * Created by Javad on 18/07/2018.
 */

public class Upload2 {

    private String imageUrl;
    private String comments;


    public Upload2(String imageUrl)
    {
        this.imageUrl=imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
