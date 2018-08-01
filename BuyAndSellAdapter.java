package com.proapplab.imageupdownload.imageuploaddownload;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Javad on 15/04/2018.
 */

public class BuyAndSellAdapter extends RecyclerView.Adapter<BuyAndSellAdapter.ImageViewHolder> {
    private Context mContext,context2;
    private List<Upload> mUploads,imageUpload;
    private OnItemClickListener mListener;
    private int totalkey=0;
    private Upload uploadCurrent;
    private int imgIndexClicked;
    private ArrayList<String> linkList=new ArrayList<String>();
    private String commentText,UploadID;
private   int commentCount;

    public BuyAndSellAdapter(Context context, List<Upload> uploads) {
        Log.i("(BuyAndSellAdapter, ","xyz :List<Upload> uploads ");
        mContext = context;
        mUploads = uploads;
    }

    public BuyAndSellAdapter(int childrenNo) {
       int totalChild=childrenNo;
    }

    public BuyAndSellAdapter(Context context, List<Upload> mUploadsBuyandSell2, int i) {
        context2=context;
        imageUpload=mUploadsBuyandSell2;
        Log.e("Buy Sell Adapter ","xyz : adapter ");
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_buy_and_sell, parent, false);

        return new ImageViewHolder(v);

    }


    @Override
    public void onBindViewHolder(final ImageViewHolder holder, final int position) {
                Log.e("Buy Sell Adapter ","xyz : onBindViewHolder");


     uploadCurrent = mUploads.get(position);
     UploadID=uploadCurrent.getUploadid();
        Log.e("Buy Sell Adapter ","xyz : uploadid "+UploadID);
        if(uploadCurrent.getmImageUrl6()!=null)
        {

           totalkey=6;
        }else if(uploadCurrent.getmImageUrl5()!=null)
        {

            totalkey=5;
        }else if(uploadCurrent.getmImageUrl4()!=null)
        {

            totalkey=4;
        }
        else if(uploadCurrent.getmImageUrl3()!=null)
        {

            totalkey=3;
        }else if(uploadCurrent.getmImageUrl2()!=null)
        {

            totalkey=2;
        }else if(uploadCurrent.getmImageUrl1()!=null)
        {

            totalkey=1;
        }



        if(totalkey==3)
        {
            holder.img4.setVisibility(View.INVISIBLE);
        }

        if(totalkey<5)
        {
            holder.RemainingImage.setVisibility(View.INVISIBLE);
        }else
        {
            holder.RemainingImage.setVisibility(View.VISIBLE);
            holder.RemainingImage.setText("+"+(totalkey-4));
        }

        holder.CommentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length()>0)
                {
                    holder.send.setVisibility(View.VISIBLE);
                }else
                {
                    holder.send.setVisibility(View.INVISIBLE);
                }

            }
        });


        Log.e("Buy Sell Adapter ","xyz : number of key "+totalkey);

        Log.e("Buy Sell Adapter ","xyz : image1.getImageUrl(1) "+uploadCurrent.getmImageUrl1());
        Log.e("Buy Sell Adapter ","xyz : image1.getImageUrl(2) "+uploadCurrent.getmImageUrl2());
        Log.e("Buy Sell Adapter ","xyz : image1.getImageUrl(3) "+uploadCurrent.getmImageUrl3());
        Log.e("Buy Sell Adapter ","xyz : image1.getImageUrl(4) "+uploadCurrent.getmImageUrl4());
        Log.e("Buy Sell Adapter ","xyz : image1.getImageUrl(5) "+uploadCurrent.getmImageUrl5());
        Log.e("Buy Sell Adapter ","xyz : image1.getImageUrl(6) "+uploadCurrent.getmImageUrl6());
     //   Log.e("Buy Sell Adapter ","xyz : SubCat "+uploadCurrent.getSubCategory());
       holder.textViewName.setText(uploadCurrent.getName());
        holder.whatAreyouSelling.setText(uploadCurrent.getCountry());
        holder.detailedDescription.setText(uploadCurrent.getStore());
      //  holder.textViewStore.setText(uploadCurrent.getStore());
      //  holder.detailedDescription.setText(uploadCurrent.getName());
        if(totalkey==3)
        {
           holder.FourImage.setVisibility(View.INVISIBLE);
            holder.ThreeImage.setVisibility(View.VISIBLE);

            Picasso.with(mContext)
                    .load(uploadCurrent.getmImageUrl1())
                    .placeholder(R.drawable.imgplaceholder)
                    .fit()

                    //.centerInside()
                    .centerCrop()

                    .into(holder.imageView);

            Picasso.with(mContext)
                    .load(uploadCurrent.getmImageUrl1())
                    .placeholder(R.drawable.imgplaceholder)
                   .fit()

                  // .centerInside()
                    .centerCrop()

                    .into(holder.img31);

            Picasso.with(mContext)
                    .load(uploadCurrent.getmImageUrl2())
                    .placeholder(R.drawable.imgplaceholder)
                    .fit()

                    // .centerInside()
                    .centerCrop()

                    .into(holder.img32);

            Picasso.with(mContext)
                    .load(uploadCurrent.getmImageUrl3())
                    .placeholder(R.drawable.imgplaceholder)
                    .fit()

                    //.centerInside()
                    .centerCrop()

                    .into(holder.img33);





        }else {
            holder.FourImage.setVisibility(View.VISIBLE);
            holder.ThreeImage.setVisibility(View.INVISIBLE);
            Picasso.with(mContext)
                    .load(uploadCurrent.getmImageUrl1())
                    .placeholder(R.drawable.imgplaceholder)
                    .fit()

                    //.centerInside()
                    .centerCrop()

                    .into(holder.imageView);

            Picasso.with(mContext)
                    .load(uploadCurrent.getmImageUrl1())
                    .placeholder(R.drawable.imgplaceholder)
                    .fit()

                    // .centerInside()
                    .centerCrop()

                    .into(holder.img1);

            Picasso.with(mContext)
                    .load(uploadCurrent.getmImageUrl2())
                    .placeholder(R.drawable.imgplaceholder)
                   .fit()

                    //.centerInside()
                    .centerCrop()

                    .into(holder.img2);

            Picasso.with(mContext)
                    .load(uploadCurrent.getmImageUrl3())
                    .placeholder(R.drawable.imgplaceholder)
                    .fit()

                    // .centerInside()
                    .centerCrop()

                    .into(holder.img3);

            Picasso.with(mContext)
                    .load(uploadCurrent.getmImageUrl4())
                    .placeholder(R.drawable.imgplaceholder)
                    .fit()

                    // .centerInside()
                    .centerCrop()

                    .into(holder.img4);
        }
       holder.detailedDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Buy and Sell","xyz : textViewName clicked");

                ViewGroup.LayoutParams params = holder.detailedDescription.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                holder.detailedDescription.setLayoutParams(params);
             // holder.  textViewName.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;


            }


       });

        DatabaseReference UploadExistCheck=FirebaseDatabase.getInstance().getReference("Buy And Sell Comments");
        UploadExistCheck.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                Upload thisUpload=mUploads.get(position);
                if(dataSnapshot.hasChild(thisUpload.getUploadid()))
                {

                    DatabaseReference CommentReference =FirebaseDatabase.getInstance().getReference("Buy And Sell Comments").child(thisUpload.getUploadid());

                    CommentReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            commentCount= (int) dataSnapshot.getChildrenCount();
                            holder.commentscount.setText(commentCount+" Comments");
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }else{holder.commentscount.setText("0 Comments");}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        holder.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Upload thisUpload=mUploads.get(position);

                DatabaseReference CommentReference =FirebaseDatabase.getInstance().getReference("Buy And Sell Comments").child(thisUpload.getUploadid());
                String commentuploadId = CommentReference.push().getKey();
                CommentReference.child(commentuploadId).setValue(holder.CommentEditText.getText().toString());
                Log.e("BuySellAdapter","xyz : send clicked at position "+position+ " Upload Id "+thisUpload.getUploadid());


            }
        });

holder.commentscount.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Log.e("BuySellAdapter","xyz : Comment Clicked");
        Upload thisUpload=mUploads.get(position);
        Intent intent = new Intent(mContext,Comments.class);
        intent.putExtra("PostId",thisUpload.getUploadid());
        intent.putExtra("AdapterPosition",position);
        intent.putExtra("commentCount",commentCount);
        Log.e("BuySellAdapter","xyz : commentCount "+commentCount);
        mContext.startActivity(intent);
    }
});



    }



    @Override
    public int getItemCount() {
        Log.e("BuySellAdapter ","xyz : getItemCount()"+mUploads.size());
      return mUploads.size();

      //  return imageUpload.size();

    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements OnItemClickListener, View.OnClickListener
            ,View.OnCreateContextMenuListener,PopupMenu.OnMenuItemClickListener, MenuItem.OnMenuItemClickListener {
        public TextView textViewName,textViewStore,RemainingImage,whatAreyouSelling,detailedDescription,commentscount;
        public ImageView imageView,img1,img2,img3,img4,img31,img32,img33;
        public RelativeLayout MainLayout,ThreeImage,FourImage;
        public EditText CommentEditText;
        public Button send;


        public ImageViewHolder(View itemView) {
            super(itemView);
            Log.e("BuySellAdapter ","xyz : ImageViewHolder");
            MainLayout=itemView.findViewById(R.id.MainLayout);
            ThreeImage=itemView.findViewById(R.id.ThreeImageRelL);
            FourImage=itemView.findViewById(R.id.FourImageRelL);

            whatAreyouSelling=itemView.findViewById(R.id.PostShortDescription);
            detailedDescription=itemView.findViewById(R.id.Deatailed_description);
            textViewName = itemView.findViewById(R.id.text_view_name);
            RemainingImage = itemView.findViewById(R.id.RemainingImage);
            CommentEditText=itemView.findViewById(R.id.commentedittext);
            commentscount=itemView.findViewById(R.id.Comments);
            send=itemView.findViewById(R.id.send);


            imageView = itemView.findViewById(R.id.buy_sell_image_view_upload);
            img1 = itemView.findViewById(R.id.idimg1);
            img2 = itemView.findViewById(R.id.idimg2);
            img3 = itemView.findViewById(R.id.idimg3);
            img4 = itemView.findViewById(R.id.idimg4);

            img31=itemView.findViewById(R.id.idimg31);
            img32=itemView.findViewById(R.id.idimg32);
            img33=itemView.findViewById(R.id.idimg33);

            textViewStore=itemView.findViewById(R.id.text_view_store);
           // itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
            img1.setOnClickListener(this);
            img2.setOnClickListener(this);
            img3.setOnClickListener(this);
            img4.setOnClickListener(this);
            img31.setOnClickListener(this);
            img32.setOnClickListener(this);
            img33.setOnClickListener(this);
            FourImage.setOnClickListener(this);
            ThreeImage.setOnClickListener(this);
            MainLayout.setOnClickListener(this);
         //   commentscount.setOnClickListener(this);





        }


        @Override
        public void onClick(View v) {


            Log.e("BuySellAdapter ","xyz :  v.getId "+v.getId()+img1.getId());
            if(mListener!= null){
                int position=getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION) {



                   mListener.onItemClick(v, position,totalkey,CommentEditText.getText().toString(),UploadID);




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

   /*     @Override
        public void onItemClick(int position) {

            Log.e("BuySellAdapter ","xyz : Onitem clicked");


        }*/



        @Override
        public void onItemClick(View view, int position, int totalkey, String comment, String UploadID) {
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


        void  onItemClick(View view, int position, int totalKey, String comment, String UploadId);

        void onWhateverClick(int position);

        void onDeleteClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {

         mListener=listener;
    }

public BuyAndSellAdapter()
{}

}