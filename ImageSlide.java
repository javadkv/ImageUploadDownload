package com.proapplab.imageupdownload.imageuploaddownload;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImageSlide extends AppCompatActivity {
    private List<Upload> mUploads;
    Upload upload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_slide);
        final ViewPager viewPager=findViewById(R.id.view_pager);
        mUploads = new ArrayList<>();



        DatabaseReference searchRef= FirebaseDatabase.getInstance().getReference("Product List").child("Product Images").child("Images0");

       searchRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot searchSnapshot : dataSnapshot.getChildren())
                {

                    upload = searchSnapshot.getValue(Upload.class);
                     mUploads.add(upload);


                }
                ViewPagerAdapter adapter=new ViewPagerAdapter(ImageSlide.this,mUploads);
                viewPager.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
