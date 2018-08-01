package com.proapplab.imageupdownload.imageuploaddownload;

import android.content.Context;
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
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import com.squareup.picasso.Picasso;

import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Javad on 15/04/2018.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Upload> mUploads;
    private OnItemClickListener mListener;
    public ImageAdapter(Context context, List<Upload> uploads) {

       Log.e("Image Adapter ","xyz : ImageAdapter Called");
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("Image Adapter ","xyz : ImageViewHolder onCreateViewHolder Called");
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Log.e("Image Adapter ","xyz : onBindViewHolder Called");
        Upload uploadCurrent = mUploads.get(position);
        Log.e("Image Adapter ","xyz : onBindViewHolder Position "+position);
        holder.textViewName.setText(uploadCurrent.getName());
        holder.textViewStore.setText(uploadCurrent.getStore());
        Picasso.with(mContext)
                .load(uploadCurrent.getmImageUrl1())
                .placeholder(R.drawable.imgplaceholder)
               .fit()

               .centerInside()
               // .centerCrop()
                .into(holder.imageView);



    }
    

    @Override
    public int getItemCount() {
        Log.e("Image Adapter ","xyz : get Item Count");
        Log.e("ImageAdapter ","xyz : Image Upload.Size "+mUploads.size());
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements OnItemClickListener, View.OnClickListener
            ,View.OnCreateContextMenuListener,PopupMenu.OnMenuItemClickListener, MenuItem.OnMenuItemClickListener {

        public TextView textViewName,textViewStore;
        public ImageView imageView;


        public ImageViewHolder(View itemView) {


            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewName = itemView.findViewById(R.id.text_view_name);
imageView=itemView.findViewById(R.id.image_view_upload);

            textViewStore=itemView.findViewById(R.id.text_view_store);

            Log.e("Image Adapter ","xyz : public ImageViewHolder");
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