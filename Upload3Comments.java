package com.proapplab.imageupdownload.imageuploaddownload;

/**
 * Created by Javad on 23/07/2018.
 */

public class Upload3Comments {

    private String comment;

    public String getmKey() {
        return mKey;
    }

    public void setmKey(String mKey) {
        this.mKey = mKey;
    }

    private String mKey;

    public Upload3Comments()
    {}

    public Upload3Comments(String comment)
    {
        this.comment=comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
