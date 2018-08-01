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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Javad on 15/04/2018.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ImageViewHolder> {
    private Context mContext,context2;
    private List<Upload3Comments> mUploads,imageUpload;
    private OnItemClickListener mListener;
    private int totalkey=0;
    private Upload3Comments uploadCurrent;
    private int imgIndexClicked;
    private ArrayList<String> linkList=new ArrayList<String>();
    private String commentText;

    public CommentsAdapter(Context context, List<Upload3Comments> uploads) {
        Log.i("(BuyAndSellAdapter, ","xyz :List<Upload> uploads ");
        mContext = context;
        mUploads = uploads;
    }



    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.comment_sample_layout, parent, false);

        return new ImageViewHolder(v);

    }


    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {
                Log.e("Buy Sell Adapter ","xyz : onBindViewHolder");


     uploadCurrent = mUploads.get(position);
        holder.commentText.setText(uploadCurrent.getComment());




    }



    @Override
    public int getItemCount() {
        Log.e("BuySellAdapter ","xyz : getItemCount()"+mUploads.size());
      return mUploads.size();

      //  return imageUpload.size();

    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements OnItemClickListener, View.OnClickListener
            ,View.OnCreateContextMenuListener,PopupMenu.OnMenuItemClickListener, MenuItem.OnMenuItemClickListener {
        public TextView commentText;


        public ImageViewHolder(View itemView) {
            super(itemView);
            Log.e("BuySellAdapter ","xyz : ImageViewHolder");

            commentText=itemView.findViewById(R.id.sample_comment_text);
            itemView.setOnCreateContextMenuListener(this);


                    }


        @Override
        public void onClick(View v) {



            if(mListener!= null){
                int position=getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION) {



                   mListener.onItemClick(v, position,totalkey,linkList);




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
        public void onItemClick(View view, int position, int totalkey, String toString, String CommentText) {

        }

        @Override
        public void onItemClick(View view, int position, int totalkey, ArrayList<String> linkList) {
            Log.e("BuySellAdapter ","xyz :   public void onItemClick(View view, int position) ");

        }

        @Override
        public void onWhateverClick(int position) {

        }

        @Override
        public void onDeleteClick(int position) {

        }
    }

    public interface OnItemClickListener {


        void onItemClick(View view, int position, int totalkey, String toString, String CommentText);

        void  onItemClick(View view, int position, int totalKey, ArrayList<String> linkList);

        void onWhateverClick(int position);

        void onDeleteClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {

         mListener=listener;
    }



}