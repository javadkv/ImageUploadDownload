package com.proapplab.imageupdownload.imageuploaddownload;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Javad on 15/04/2018.
 */

public class AllImagesAdapter extends RecyclerView.Adapter<AllImagesAdapter.ImageViewHolder> {
    private Context mContext,context2;
    private List<Upload2> mUploads,imageUpload;
    private OnItemClickListener mListener;
    private int totalkey=0;
    private Upload uploadCurrent;
    private int Adapterposition,totalimages;
  private   Bitmap bmp;



    public AllImagesAdapter(Context context, List<Upload2> linkList) {
        this.mContext=context;
        this.mUploads=linkList;

    }


    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false);

        return new ImageViewHolder(v);

    }


    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {
                Log.e("Buy Sell Adapter ","xyz : onBindViewHolder");
        Upload2 currentUpload=mUploads.get(position);


        Picasso.with(mContext)
                    .load(currentUpload.getImageUrl())
                    .placeholder(R.drawable.imgplaceholder)
                    .fit()

                    //.centerInside()
                    .centerCrop()

                    .into(holder.imageView);




    }


    @Override
    public int getItemCount() {
      //  Log.e("BuySellAdapter ","xyz : getItemCount()"+mUploads.size());
      return mUploads.size();

      //  return imageUpload.size();

    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements OnItemClickListener, View.OnClickListener
            ,View.OnCreateContextMenuListener,PopupMenu.OnMenuItemClickListener, MenuItem.OnMenuItemClickListener {
        public TextView textViewName,textViewStore,RemainingImage,whatAreyouSelling,detailedDescription;
        public ImageView imageView,img1,img2,img3,img4,img31,img32,img33;
        public RelativeLayout ThreeImage,FourImage;


        public ImageViewHolder(View itemView) {
            super(itemView);
            Log.e("BuySellAdapter ","xyz : ImageViewHolder");
            ThreeImage=itemView.findViewById(R.id.ThreeImageRelL);
            FourImage=itemView.findViewById(R.id.FourImageRelL);

            whatAreyouSelling=itemView.findViewById(R.id.PostShortDescription);
            detailedDescription=itemView.findViewById(R.id.Deatailed_description);
            textViewName = itemView.findViewById(R.id.text_view_name);
            RemainingImage = itemView.findViewById(R.id.RemainingImage);

            imageView = itemView.findViewById(R.id.image_view_upload);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);


        }


        @Override
        public void onClick(View v) {
            if(mListener!= null){
                int position=getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    mListener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Seelect Action");
            MenuItem doWhatEver=menu.add(Menu.NONE,1,1,"Do Whatever");
            MenuItem delete=menu.add(Menu.NONE,2,2,"Delete");
            doWhatEver.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if(mListener!= null){
                int position=getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    switch (item.getItemId())
                    {
                        case 1:
                        mListener.onWhateverClick(position);
                        return true;
                        case 2:
                            mListener.onDeleteClick(position);
                            return true;
                    }
                }
            }
            return false;
        }

        @Override
        public void onItemClick(int position) {

        }

        @Override
        public void onWhateverClick(int position) {

        }

        @Override
        public void onDeleteClick(int position) {

        }
    }

    public interface OnItemClickListener {

        void  onItemClick(int position);

        void onWhateverClick(int position);

        void onDeleteClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {
         mListener=listener;
    }


}