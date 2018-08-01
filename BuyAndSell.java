package com.proapplab.imageupdownload.imageuploaddownload;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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

public class BuyAndSell extends AppCompatActivity implements BuyAndSellAdapter.OnItemClickListener {
    private Context context1;
    private int h,postnumber,p,counter=0;
    private RecyclerView mRecyclerView;
    private BuyAndSellAdapter mAdapter,ImageAdapter;
    private Upload upload,uploadBuyAndSell,uploadBuyAndSell2;
    private ProgressBar mProgressCircle;
    DatabaseReference ImageReference,CommentReference;
    private FirebaseStorage mStorage;
    private ValueEventListener mDbListener;
    private List<Upload> mUploadsBuyandSell,ImageListUpload;
    private List<Upload2> commentUpload;
    private ArrayList<String> linkList;
    private int adapterPosition,totalkey;
    private String PostUploadId;
private  int commentCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_buy_sell);



        Log.e("Buy and Sell  ","xyz : Oncreat");




        final ArrayList<String> SubcategoryList=new ArrayList<>();

        mRecyclerView = findViewById(R.id.recycler_view_buy_sell);
        mRecyclerView.setHasFixedSize(true);

       // StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
        //  mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mProgressCircle = findViewById(R.id.progress_circle);

        mUploadsBuyandSell = new ArrayList<>();
       // ImageListUpload = new ArrayList<>();
        Log.e("(BuyAndSell.this, ","xyz :above mUploadsBuyandSell) ");
        mAdapter = new BuyAndSellAdapter(BuyAndSell.this,mUploadsBuyandSell);
      //  ImageAdapter = new BuyAndSellAdapter(BuyAndSell.this,ImageListUpload,h);

        Log.e("(BuyAndSell.this, ","xyz :below mUploadsBuyandSell) ");
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(BuyAndSell.this);

mStorage=FirebaseStorage.getInstance();
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





             ImageReference =FirebaseDatabase.getInstance().getReference("Buy And Sell").child("Area");
               ImageReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                      mUploadsBuyandSell.clear();
                    for(DataSnapshot getimage:dataSnapshot.getChildren())
                    {
                        Log.e("Buy Sell","xyz : Data Snap Shot Triggered "+"Images");
                        Log.e("Buy Sell","xyz : children count"+dataSnapshot.getChildrenCount());

                        Upload imageupload=getimage.getValue(Upload.class);
                        imageupload.setmKey(getimage.getKey());
                        mUploadsBuyandSell.add(imageupload);
                        Log.e("Buy Sell","xyz : Data Snap Shot getValue "+getimage.getValue());

                       // Log.e("Buy Sell","xyz : imageUrl "+h+" "+imageupload.getImageUrl());

                    }
                    mAdapter.notifyDataSetChanged();


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

    }


    @Override
    public void onItemClick(View view, int position, int totalkey, String comment, String UploadId) {
        //Toast.makeText(this,"Normal Click at Position : "+position,Toast.LENGTH_SHORT).show();
        Upload selectedItem=mUploadsBuyandSell.get(position);
        linkList=new ArrayList<String>();

        this.adapterPosition=position;
        this.totalkey=totalkey;

        linkList.clear();
        if (selectedItem.getmImageUrl1()!=null)
        {

            linkList.add(selectedItem.getmImageUrl1());
        }if(selectedItem.getmImageUrl2()!=null)
        {
            linkList.add(selectedItem.getmImageUrl2());
        }
        if(selectedItem.getmImageUrl3()!=null)
        {
            linkList.add(selectedItem.getmImageUrl3());
        }if(selectedItem.getmImageUrl4()!=null)
        {
            linkList.add(selectedItem.getmImageUrl4());
        }if(selectedItem.getmImageUrl5()!=null)
        {
            linkList.add(selectedItem.getmImageUrl5());
        }if(selectedItem.getmImageUrl6()!=null)
        {
            linkList.add(selectedItem.getmImageUrl6());
        }


        switch (view.getId())
        {
            case R.id.idimg1:
                openIntent();


                Toast.makeText(this,"Img1 Clicked at Adapter Position"+position,Toast.LENGTH_SHORT).show();
                break;
            case R.id.idimg2:
                openIntent();
                Toast.makeText(this,"Img2 Clicked ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.idimg3:
                openIntent();
                Toast.makeText(this,"Img3 Clicked ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.idimg4:
                openIntent();
                Toast.makeText(this,"Img4 Clicked ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.idimg31:
                openIntent();
            Toast.makeText(this,"Img31 Clicked ",Toast.LENGTH_SHORT).show();
            break;
            case R.id.idimg32:
                openIntent();
                Toast.makeText(this,"Img32 Clicked ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.idimg33:
                openIntent();
                Toast.makeText(this,"Img33 Clicked ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.MainLayout:
                Toast.makeText(this,"Main Layout Clicked ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.ThreeImageRelL:
                Toast.makeText(this,"3 Image REl Layout Clicked ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.FourImageRelL:
                Toast.makeText(this,"4 Image Rel Layout Clicked ",Toast.LENGTH_SHORT).show();
                break;
       /*     case R.id.Comments:


                Toast.makeText(this,"Comments Clicked ",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(BuyAndSell.this,Comments.class);
                startActivity(intent);
               break;
*/





        }


        Log.e("Buy Sell","xyz : link list size "+linkList.size());



        Log.e("Buy Sell","xyz : Onitem clicked, Id "+view.getId());
    }

    private void openIntent() {
        Intent intent=new Intent(BuyAndSell.this,AllImages.class);
        intent.putExtra("AdapterPosition",adapterPosition);
        intent.putExtra("NumberOfImage",totalkey);
        intent.putExtra("linklist",linkList);
        startActivity(intent);
    }

    private void UploadCommentToFirebase(final String PostUploadId, final String comment, final View view, int position) {

        final Upload selectedItem=mUploadsBuyandSell.get(position);
       CommentReference =FirebaseDatabase.getInstance().getReference("Buy And Sell Comments").child(selectedItem.getUploadid());


        DatabaseReference UploadExistCheck=FirebaseDatabase.getInstance().getReference("Buy And Sell Comments");
        UploadExistCheck.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(selectedItem.getUploadid()))
                {

                    DatabaseReference CommentReference =FirebaseDatabase.getInstance().getReference("Buy And Sell Comments").child(selectedItem.getUploadid());

                    CommentReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            commentCount= (int) dataSnapshot.getChildrenCount();

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        TextView textView=(TextView)findViewById(R.id.Comments);
        textView.setText(commentCount+"Comments");

        Log.e("Buy Sell","xyz : Uploading comment to "+PostUploadId+"position "+position);

    }

    @Override
    public void onWhateverClick(int position) {
        Toast.makeText(this,"Whatever Click : "+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {
        Upload selectedItem=mUploadsBuyandSell.get(position);
        final String selectedKey=selectedItem.getmKey();

        StorageReference imageRef=mStorage.getReferenceFromUrl(selectedItem.getmImageUrl1());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
              ImageReference.child(selectedKey).removeValue();
                Toast.makeText(BuyAndSell.this,"Item Deleted",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(BuyAndSell.this,"Deletion Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
