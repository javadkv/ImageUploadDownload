package com.proapplab.imageupdownload.imageuploaddownload;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

public class Comments extends AppCompatActivity implements CommentsAdapter.OnItemClickListener {
    private Context context1;
    private int h, postnumber, p, counter = 0;
    private RecyclerView mRecyclerView;
    private CommentsAdapter mAdapter, ImageAdapter;
    private ProgressBar mProgressCircle;
    DatabaseReference CommentsRefe;
    private FirebaseStorage mStorage;
    private ValueEventListener mDbListener;
    private List<Upload3Comments> mUploadsComments, ImageListUpload;
    private ArrayList<String> linkList;
    private String PostId;
    private EditText recyclerviewEditText;
    private Button recyclerviewSendButton;
    private int AdapterPosition, NumberofComments,commentCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_commentsl);

       getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        Log.e("Comments   ", "xyz : Oncreat");
        Intent intent = getIntent();


        PostId = intent.getStringExtra("PostId");
        AdapterPosition = intent.getIntExtra("AdapterPosition", -1);
        commentCount = intent.getIntExtra("commentCount", -1);
        Log.e("Comments   ", "xyz : commentCount "+commentCount);

        recyclerviewEditText = (EditText) findViewById(R.id.recycler_comment_edittext);
        recyclerviewSendButton = (Button) findViewById(R.id.recycler_comments_send);
        final ArrayList<String> SubcategoryList = new ArrayList<>();

        mRecyclerView = findViewById(R.id.recycler_view_comments);
        mRecyclerView.setHasFixedSize(true);

        // StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
        //  mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManagerWithSmoothScroller(Comments.this));
       // mRecyclerView.setLayoutManager(mLayoutManager);



        mProgressCircle = findViewById(R.id.progress_circle);

        mUploadsComments = new ArrayList<>();
        // ImageListUpload = new ArrayList<>();
        Log.e("(BuyAndSell.this, ", "xyz :above mUploadsComments) ");
        mAdapter = new CommentsAdapter(Comments.this, mUploadsComments);
        //  ImageAdapter = new CommentsAdapter(BuyAndSell.this,ImageListUpload,h);

        Log.e("(BuyAndSell.this, ", "xyz :below mUploadsComments) ");
        mRecyclerView.setAdapter(mAdapter);



        mAdapter.setOnItemClickListener(Comments.this);

        recyclerviewSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatabaseReference CommentReference = FirebaseDatabase.getInstance().getReference("Buy And Sell Comments").child(PostId);
                String commentuploadId = CommentReference.push().getKey();
                CommentReference.child(commentuploadId).setValue(recyclerviewEditText.getText().toString());
                recyclerviewEditText.setText("");
               mRecyclerView.scrollToPosition(NumberofComments-1);

                Log.e("(Comments, ", "xyz : commentscount "+NumberofComments);
                NumberofComments++;

               // mAdapter.notifyDataSetChanged();


            }
        });


        mStorage = FirebaseStorage.getInstance();
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    Log.e("SFD", "xyz : Connected");
                } else {
                    Log.e("SFD", "xyz :Not  Connected");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("SFD", "xyz : Listner canceled");
            }
        });


        CommentsRefe = FirebaseDatabase.getInstance().getReference("Buy And Sell Comments");
        CommentsRefe.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChild(PostId)) {

                    CommentsRefe.child(PostId).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            mUploadsComments.clear();
                            for (DataSnapshot getcomments : dataSnapshot.getChildren()) {

                                NumberofComments = (int) dataSnapshot.getChildrenCount();

                                Log.e("Comments", "xyz : get comments " +NumberofComments+ getcomments.getValue());

                                String comment = (String) getcomments.getValue();

                                Upload3Comments commentsUpload = new Upload3Comments(comment);

                                mUploadsComments.add(commentsUpload);
                            }

                            mAdapter.notifyDataSetChanged();
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


    }


    @Override
    public void onItemClick(View view, int position, int totalkey, String toString, String CommentText) {


    }

    @Override
    public void onItemClick(View view, int position, int totalKey, ArrayList<String> linkList) {

    }

    @Override
    public void onWhateverClick(int position) {
        Toast.makeText(this, "Whatever Click : " + position, Toast.LENGTH_SHORT).show();
        Log.e("Comments", "xyz : comments " + NumberofComments);
        mRecyclerView.scrollToPosition(NumberofComments-1);
    }

    @Override
    public void onDeleteClick(int position) {
        Upload3Comments selectedItem = mUploadsComments.get(position);

    }

    @Override
    public void onBackPressed() {
        commentCount=0;
        super.onBackPressed();
    }
}
