package com.proapplab.imageupdownload.imageuploaddownload;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ImagesActivity extends AppCompatActivity implements ImageAdapter.OnItemClickListener{

    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    Upload upload;
    private ProgressBar mProgressCircle;
    DatabaseReference country;
    private FirebaseStorage mStorage;
    private ValueEventListener mDbListener;
    private List<Upload> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        Log.e("ImageActivity ","xyz : ImageActivity Oncreate");

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        mProgressCircle = findViewById(R.id.progress_circle);

        mUploads = new ArrayList<>();
        Log.e("ImageActivity ","xyz : mUpload 1 "+mUploads.toString());

        mAdapter = new ImageAdapter(ImagesActivity.this,mUploads);
        Log.e("ImageActivity ","xyz : mUpload 2 "+mUploads.size());

        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(ImagesActivity.this);


        Intent searchList = getIntent();
        String item = searchList.getStringExtra("product");
        String subcategory = searchList.getStringExtra("subcategory");

        Log.e("Image Acti Intent item","xyz : item "+item);
        Log.e("Image Acti","xyz : subcategory "+subcategory);


        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    Log.e("SFD","xyz : Connected");
                } else {
                    Log.e("SFD","xyz :Not  Connected");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("SFD","xyz : Listner canceled");
            }
        });




         mStorage=FirebaseStorage.getInstance();
       country = FirebaseDatabase.getInstance().getReference("Category").child(subcategory);
        DatabaseReference store=country.child("B");
        DatabaseReference department2=store.child("C");
        DatabaseReference category=department2.child("D");
        DatabaseReference subCategory=category.child("E");
          Query search = country.orderByChild("name").equalTo(item);

     // Query search = country.orderByChild("name").startAt("Ip").endAt("Ip"+  "\uf8ff");




            search.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("ImageActivity ","xyz : addvalueEventListner Triggered");

                mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Log.e("ImageActivity ","xyz : DataSnapShot Triggered");

                    Log.e("Children","xyz : "+dataSnapshot.getChildrenCount());
                upload = postSnapshot.getValue(Upload.class);
                    upload.setmKey(postSnapshot.getKey());
                    Log.e("ImageActivity dataSnap","xyz : mUpload.size 1 "+mUploads.size());
                  mUploads.add(upload);
                    Log.e("ImageActivity DataSnap ","xyz : mUpload.size 1 "+mUploads.size());
                }

                mAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
                if(dataSnapshot.getChildrenCount()==0)
                {
                    Toast.makeText(ImagesActivity.this,"Item Not Found: ",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ImagesActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });



    }

    @Override
    public void onItemClick(int position) {
        //Toast.makeText(this,"Normal Click at Position : "+position,Toast.LENGTH_SHORT).show();
        Upload selectedItem=mUploads.get(position);
        Toast.makeText(this,"Store Name : "+selectedItem.getStore(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWhateverClick(int position) {
        Toast.makeText(this,"Whatever Click : "+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {
        Upload selectedItem=mUploads.get(position);
        final String selectedKey=selectedItem.getmKey();
        StorageReference imageRef=mStorage.getReferenceFromUrl(selectedItem.getmImageUrl1());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                country.child(selectedKey).removeValue();
                Toast.makeText(ImagesActivity.this,"Item Deleted",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ImagesActivity.this,"Deletion Failed",Toast.LENGTH_SHORT).show();
            }
        });

    }

  /*  @Override
    protected void onDestroy() {
        super.onDestroy();
        country.removeEventListener(mDbListener);
    }*/
}
