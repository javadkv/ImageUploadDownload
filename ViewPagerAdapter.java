package com.proapplab.imageupdownload.imageuploaddownload;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;



import com.squareup.picasso.Picasso;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * Created by Javad on 22/05/2018.
 */

public class ViewPagerAdapter extends PagerAdapter {
    ImageView imageView;
    private Context mContext;
    private List<Upload> mUploads;
    PhotoViewAttacher attacher;

    public ViewPagerAdapter(Context context, List<Upload> uploads) {
        mContext = context;
        mUploads = uploads;
    }
    @Override
    public int getCount() {
        return mUploads.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        Upload uploadCurrent = mUploads.get(position);
       imageView = new ImageView(mContext);
//PhotoView photoView=new PhotoView(mContext);
        imageView=new ImageView(mContext);
        Picasso.with(mContext)
                .load(uploadCurrent.getmImageUrl1())
               .placeholder(R.drawable.imgplaceholder)
                .fit()

                .centerInside()
                //.centerCrop()
               .into(imageView);



        container.addView(imageView);


      attacher =  new PhotoViewAttacher(imageView);



        return imageView;


    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
