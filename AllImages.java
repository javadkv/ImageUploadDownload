package com.proapplab.imageupdownload.imageuploaddownload;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class AllImages extends AppCompatActivity implements AllImagesAdapter.OnItemClickListener {
    private Context context1;
    private int h,postnumber,p,counter=0;
    private RecyclerView mRecyclerView;
    private AllImagesAdapter mAdapter;
    private Upload upload;
    private ProgressBar mProgressCircle;
    DatabaseReference country,country2,Productimages, mDatabaseRef5;
    private FirebaseStorage mStorage;
    private ValueEventListener mDbListener;
     private  List<Upload> mUploadsAllImages;
    private  List<Upload2> mUploads2;
    private int  AdapterPosition,TotalImageLink;
    private ArrayList<String> linkList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_images);


        mUploads2 = new ArrayList<>();
        mAdapter=new AllImagesAdapter(AllImages.this,mUploads2);
        Log.e("All Images  ","xyz : Oncreat");
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            AdapterPosition=extras.getInt("AdapterPosition",-1);
            TotalImageLink=extras.getInt("NumberOfImage",-1);
             this.linkList= getIntent().getStringArrayListExtra("linklist");
            Log.e("All Images  ","xyz : linklist Size"+linkList.size());

        }
        else
        {
            Log.e("All Images  ","xyz : No putExtra");
        }

      //  mUploads2.clear();
        for(int i=0;i<linkList.size();i++)
        {



      Upload2 upload2= new Upload2(linkList.get(i));

               mUploads2.add(upload2);

        }
        mAdapter.notifyDataSetChanged();

        mRecyclerView = findViewById(R.id.recycler_view_AllImages);
        mRecyclerView.setHasFixedSize(true);

       // StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
        //  mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mProgressCircle = findViewById(R.id.progress_circle);


       // ImageListUpload = new ArrayList<>();
        Log.e("(BuyAndSell.this, ","xyz :above mUploadsBuyandSell) ");

      //  ImageAdapter = new BuyAndSellAdapter(BuyAndSell.this,ImageListUpload,h);


        Log.e("(BuyAndSell.this, ","xyz :below mUploadsBuyandSell) ");
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(AllImages.this);







    }

    @Override
    public void onItemClick(int position) {
        //Toast.makeText(this,"Normal Click at Position : "+position,Toast.LENGTH_SHORT).show();
        Upload2 selectedItem=mUploads2.get(position);
      Log.e("All Images","Adapter Position "+position+selectedItem.getImageUrl());
    }

    @Override
    public void onWhateverClick(int position) {
        Toast.makeText(this,"Whatever Click : "+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {
        Upload selectedItem=mUploadsAllImages.get(position);
        final String selectedKey=selectedItem.getmKey();
        StorageReference imageRef=mStorage.getReferenceFromUrl(selectedItem.getmImageUrl1());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                country.child(selectedKey).removeValue();
                Toast.makeText(AllImages.this,"Item Deleted",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AllImages.this,"Deletion Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
