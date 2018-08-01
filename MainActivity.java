package com.proapplab.imageupdownload.imageuploaddownload;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 2;
    private static final int CAMERA_OPEN_REQUEST = 1;
    private Button mButtonChooseImage,mButtonCamera;
    private Button mButtonUpload, imageSlide;
    private TextView mTextViewShowUploads, search_text;
    private EditText itemName, Country, Store, Department, Category, SubCategory, Brand;
    private ImageView mImageView, TwoImageOneLeft, TwoImageOneRight;
    private ProgressBar mProgressBar;

    private Uri mImageUri;
    private int i;
    private StorageReference mStorageRef, mStorageRef2, mStorageRef3;
    private DatabaseReference mDatabaseRef, mDatabaseRef2, mDatabaseRef3, mDatabaseRef4, mDatabaseRef5;
    private StorageTask mUploadTask;
    private ArrayList<Uri> UriList;
    private int k;
    private int PostNumber;
    private String uploadId33;
    private String ProductName, country, store, department, category, sub_category, brand;
    ArrayList<String> UploadedUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UploadedUri = new ArrayList<>();
        UriList = new ArrayList<>();
        imageSlide = (Button) findViewById(R.id.button_image_Slide);
        mButtonChooseImage = findViewById(R.id.button_choose_image);
        mButtonUpload = findViewById(R.id.button_upload);
        mTextViewShowUploads = findViewById(R.id.text_view_show_uploads);
        search_text = (TextView) findViewById(R.id.text_view_search);
        itemName = findViewById(R.id.edit_text_file_name);
        Country = (EditText) findViewById(R.id.edit_text_whatAreYouSelling);
        Store = (EditText) findViewById(R.id.edit_text_describtion);
        Department = (EditText) findViewById(R.id.edit_text_Department);
        Category = (EditText) findViewById(R.id.edit_text_Category);
        SubCategory = (EditText) findViewById(R.id.edit_text_Sub_category);
        mImageView = (ImageView) findViewById(R.id.image_view);
        TwoImageOneLeft = (ImageView) findViewById(R.id.image_view_half_left);
        TwoImageOneRight = (ImageView) findViewById(R.id.image_view_half_right);
        Brand = (EditText) findViewById(R.id.edit_text_Brand);
        mButtonCamera=(Button)findViewById(R.id.button_Camera);
        mProgressBar = findViewById(R.id.progress_bar);





/*
        mDatabaseRef5 = FirebaseDatabase.getInstance().getReference("Product List").child("Post Number");

        mDatabaseRef5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.e("Main Activity ","xyz : onDataChange ");
                PostNumber = Integer.valueOf(dataSnapshot.getValue().toString());



                Log.e("Main Activity ","xyz : Post Number_ "+PostNumber);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/
        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        mButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });

        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(MainActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();

                }
            }
        });

        mTextViewShowUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagesActivity();
            }
        });
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);



        startActivityForResult(intent,CAMERA_OPEN_REQUEST);
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");/* video/* application/pdf" );*/

        // intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);

        // startActivityForResult(intent, PICK_IMAGE_REQUEST);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                //   && data != null && data.getData() != null) {
                && data != null && data.getClipData() != null) {
            //   mImageUri = data.getData();
            //  Log.e("URI","xyz : "+mImageUri);

            //  Picasso.with(this).load(mImageUri).into(mImageView);


            // picassoInstance.load(VideoRequestHandler.SCHEME_VIDEO+":"+filepath).into(holder.videoThumbnailView);


            int totalItemsSelected = data.getClipData().getItemCount();

            for (int i = 0; i < totalItemsSelected; i++) {

                Uri fileUri = data.getClipData().getItemAt(i).getUri();

                String fileName = getFileName(fileUri);

                UriList.add(fileUri);

                Log.e("MainActivity ", "xyz : Urilist.Add0 " + UriList.get(i));


                //  Log.e("File Name ", "xyz : File Name " + i + " " + fileName+"File Uri "+fileUri+UriList.size());

             /*   fileNameList.add(fileName);
                fileDoneList.add("uploading");
                uploadListAdapter.notifyDataSetChanged();

                StorageReference fileToUpload = mStorage.child("Images").child(fileName);

                final int finalI = i;
                fileToUpload.putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        fileDoneList.remove(finalI);
                        fileDoneList.add(finalI, "done");

                        uploadListAdapter.notifyDataSetChanged();

                    }
                });*/
                // Picasso.with(this).load(data.getClipData().getItemAt(2).getUri()).into(mImageView);
            }

            Log.e("Uri Array List size ", "xyz : Uri List size " + UriList.size());

            for (k = 0; k < UriList.size(); k++) {
                Log.e("Uri Array List ", "xyz : Uri List " + k + UriList.get(k));
            }
            k = 0;


            if (UriList.size() == 2) {
                mImageView.setVisibility(View.INVISIBLE);

                Picasso.with(this).load(UriList.get(0)).fit().centerCrop().into(TwoImageOneLeft);
                Picasso.with(this).load(UriList.get(1)).fit().centerCrop().into(TwoImageOneRight);
            }


        } else if (data.getData() != null) {
            mImageUri = data.getData();

            UriList.add(data.getData());


            Picasso.with(this).load(UriList.get(0)).into(mImageView);
            Log.e("Main Activity ", "xyz : Single File Selected " + mImageUri);
            Log.e("Main Activity ", "xyz : URI SIZE " + UriList.size());

        }





        if (requestCode == CAMERA_OPEN_REQUEST && resultCode == RESULT_OK)
                 {


                     //get the camera image
                     Bundle extras = data.getExtras();
                     Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                   //  ByteArrayOutputStream baos = new ByteArrayOutputStream();
                  //   bitmap.compress(Bitmap.CompressFormat.JPEG, 5, baos);
                   //  byte[] dataBAOS = baos.toByteArray();
//Log.e("Main Activity","xyz : Camera intent "+dataBAOS+" size  "+bitmap.getByteCount());

//set the image into imageview
                    mImageView.setImageBitmap(bitmap);
                     Bitmap converetdImage = getResizedBitmap(bitmap,5000);
                getImageUri(this,converetdImage);


        }
    }

    private Bitmap getResizedBitmap(Bitmap bitmap, int maxSize) {

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(bitmap, width, height, true);

    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
       // ByteArrayOutputStream bytes = new ByteArrayOutputStream();
      //  inImage.compress(Bitmap.CompressFormat.JPEG,100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        Log.e("Main Activity","xyz : URI "+ Uri.parse(path));
        UriList.add(Uri.parse(path));
        Log.e("Main Activity","xyz : URI size "+ UriList.size());
        return Uri.parse(path);
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {

        ProductName = itemName.getText().toString().trim();
        country = Country.getText().toString().trim();
        store = Store.getText().toString().trim();
        department = Department.getText().toString().trim();
        category = Category.getText().toString().trim();
        sub_category = SubCategory.getText().toString().trim();
        brand = Brand.getText().toString().trim().toLowerCase();


        mStorageRef = FirebaseStorage.getInstance().getReference().child("Buy And Sell").child("Area");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Buy And Sell").child("Area");

        switch (UriList.size()) {
            case 1:
                singleImage();
                break;
            case 2:

                TwoImage();

                break;
            case 3:
                ThreeImage();
                break;
            case 4:
                fourImage();
                break;
            case 5:
                FiveImage();
                break;
            case 6:
                SixImage();
                break;


        }


    }


    private void singleImage() {
        StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                //    + "." + getFileExtension(mImageUri));
                + "." + getFileExtension(UriList.get(0)));

        mUploadTask = fileReference.putFile(UriList.get(0)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setProgress(0);
                    }
                }, 500);

                Toast.makeText(MainActivity.this, i + " Upload successfull", Toast.LENGTH_LONG).show();
                UriList.clear();
                Upload upload = new Upload(brand, sub_category, category, department, store, country, ProductName,
                        taskSnapshot.getDownloadUrl().toString());
                Log.e("Main Activity", "xyz : brand " + upload.getBrand());
                String uploadId = mDatabaseRef.push().getKey();
                mDatabaseRef.child(uploadId).setValue(upload);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                mProgressBar.setProgress((int) progress);
            }
        });
    }

    private void TwoImage() {
        Log.e("Main Activity ", "xyz : UploadedUriSize first " + UploadedUri.size());
        UploadedUri.clear();
        for (int uriNo = 0; uriNo < 2; uriNo++) {

            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    //    + "." + getFileExtension(mImageUri));
                    + "." + getFileExtension(UriList.get(uriNo)));

            mUploadTask = fileReference.putFile(UriList.get(uriNo))
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 500);
                            UriList.clear();
                            Toast.makeText(MainActivity.this, UploadedUri.size() + " Upload successfull", Toast.LENGTH_LONG).show();
                            Log.e("Main Activity ", "xyz : Upload Successful");
                            String link = taskSnapshot.getDownloadUrl().toString();

                            UploadedUri.add(link);
                            if (UploadedUri.size() == 2) {
                                Log.e("Main Activity ", "xyz : UploadedUri.size()==2) ");
                                Upload upload = new Upload(brand, sub_category, category, department, store, country, ProductName,
                                        UploadedUri.get(0), UploadedUri.get(1));
                                String uploadId = mDatabaseRef.push().getKey();
                                mDatabaseRef.child(uploadId).setValue(upload);
                            }
                            Log.e("Main Activity ", "xyz : UploadedUriSize middle " + UploadedUri.size());
                            Log.e("Main Activity ", "xyz : Uploaded Uri " + link);
                            Log.e("Main Activity ", "xyz : UploadedURi(0) " + UploadedUri.get(0));
                        }
                    })


                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });


        }


    }

    private void ThreeImage() {

        Log.e("Main Activity ", "xyz : UploadedUriSize first " + UploadedUri.size());
        UploadedUri.clear();
        for (int uriNo = 0; uriNo < 3; uriNo++) {

            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    //    + "." + getFileExtension(mImageUri));
                    + "." + getFileExtension(UriList.get(uriNo)));

            mUploadTask = fileReference.putFile(UriList.get(uriNo))
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 500);
                            UriList.clear();
                            Toast.makeText(MainActivity.this, UploadedUri.size() + " Upload successfull", Toast.LENGTH_LONG).show();
                            Log.e("Main Activity ", "xyz : Upload Successful");
                            String link = taskSnapshot.getDownloadUrl().toString();

                            UploadedUri.add(link);
                            if (UploadedUri.size() == 3) {
                                Log.e("Main Activity ", "xyz : UploadedUri.size()==2) ");
                                Upload upload = new Upload(brand, sub_category, category, department, store, country, ProductName,
                                        UploadedUri.get(0), UploadedUri.get(1), UploadedUri.get(2));
                                String uploadId = mDatabaseRef.push().getKey();
                                mDatabaseRef.child(uploadId).setValue(upload);

                            }
                            Log.e("Main Activity ", "xyz : UploadedUriSize middle " + UploadedUri.size());
                            Log.e("Main Activity ", "xyz : Uploaded Uri " + link);
                            Log.e("Main Activity ", "xyz : UploadedURi(0) " + UploadedUri.get(0));
                        }
                    })


                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });


        }

    }

    private void fourImage() {

        Log.e("Main Activity ", "xyz : UploadedUriSize first " + UploadedUri.size());
        UploadedUri.clear();
        for (int uriNo = 0; uriNo < 4; uriNo++) {

            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    //    + "." + getFileExtension(mImageUri));
                    + "." + getFileExtension(UriList.get(uriNo)));

            mUploadTask = fileReference.putFile(UriList.get(uriNo))
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 500);
                            UriList.clear();
                            Toast.makeText(MainActivity.this, UploadedUri.size() + " Upload successfull", Toast.LENGTH_LONG).show();
                            Log.e("Main Activity ", "xyz : Upload Successful");
                            String link = taskSnapshot.getDownloadUrl().toString();

                            UploadedUri.add(link);
                            if (UploadedUri.size() == 4) {
                                Log.e("Main Activity ", "xyz : UploadedUri.size()==2) ");
                                Upload upload = new Upload(brand, sub_category, category, department, store, country, ProductName,
                                        UploadedUri.get(0), UploadedUri.get(1), UploadedUri.get(2), UploadedUri.get(3));
                                String uploadId = mDatabaseRef.push().getKey();
                                mDatabaseRef.child(uploadId).setValue(upload);
                            }
                            Log.e("Main Activity ", "xyz : UploadedUriSize middle " + UploadedUri.size());
                            Log.e("Main Activity ", "xyz : Uploaded Uri " + link);
                            Log.e("Main Activity ", "xyz : UploadedURi(0) " + UploadedUri.get(0));
                        }
                    })


                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });


        }

    }

    private void FiveImage() {

        Log.e("Main Activity ", "xyz : UploadedUriSize first " + UploadedUri.size());
        UploadedUri.clear();
        for (int uriNo = 0; uriNo < 5; uriNo++) {

            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    //    + "." + getFileExtension(mImageUri));
                    + "." + getFileExtension(UriList.get(uriNo)));

            mUploadTask = fileReference.putFile(UriList.get(uriNo))
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 500);
                            UriList.clear();
                            Toast.makeText(MainActivity.this, UploadedUri.size() + " Upload successfull", Toast.LENGTH_LONG).show();
                            Log.e("Main Activity ", "xyz : Upload Successful");
                            String link = taskSnapshot.getDownloadUrl().toString();

                            UploadedUri.add(link);
                            if (UploadedUri.size() == 5) {
                                Log.e("Main Activity ", "xyz : UploadedUri.size()==2) ");
                                Upload upload = new Upload(brand, sub_category, category, department, store, country, ProductName,
                                        UploadedUri.get(0), UploadedUri.get(1), UploadedUri.get(2), UploadedUri.get(3), UploadedUri.get(4));
                                String uploadId = mDatabaseRef.push().getKey();
                                mDatabaseRef.child(uploadId).setValue(upload);
                            }
                            Log.e("Main Activity ", "xyz : UploadedUriSize middle " + UploadedUri.size());
                            Log.e("Main Activity ", "xyz : Uploaded Uri " + link);
                            Log.e("Main Activity ", "xyz : UploadedURi(0) " + UploadedUri.get(0));
                        }
                    })


                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });


        }

    }

    private void SixImage() {

        Log.e("Main Activity ", "xyz : UploadedUriSize first " + UploadedUri.size());
        UploadedUri.clear();
        for (int uriNo = 0; uriNo < 6; uriNo++) {

            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    //    + "." + getFileExtension(mImageUri));
                    + "." + getFileExtension(UriList.get(uriNo)));

            mUploadTask = fileReference.putFile(UriList.get(uriNo))
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 500);
                            UriList.clear();
                            Toast.makeText(MainActivity.this, UploadedUri.size() + " Upload successfull", Toast.LENGTH_LONG).show();
                            Log.e("Main Activity ", "xyz : Upload Successful");
                            String link = taskSnapshot.getDownloadUrl().toString();

                            UploadedUri.add(link);
                            if (UploadedUri.size() == 6) {
                                Log.e("Main Activity ", "xyz : UploadedUri.size()==2) ");
                                String uploadId = mDatabaseRef.push().getKey();
                                Upload upload = new Upload(brand, sub_category, category, department, store, country, ProductName, uploadId,
                                        UploadedUri.get(0), UploadedUri.get(1), UploadedUri.get(2), UploadedUri.get(3), UploadedUri.get(4), UploadedUri.get(5));

                                mDatabaseRef.child(uploadId).setValue(upload);
                                Log.e("Main Activity ", "xyz : Upload ID " + uploadId);
                            }
                            Log.e("Main Activity ", "xyz : UploadedUriSize middle " + UploadedUri.size());
                            Log.e("Main Activity ", "xyz : Uploaded Uri " + link);
                            Log.e("Main Activity ", "xyz : UploadedURi(0) " + UploadedUri.get(0));
                        }
                    })


                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });


        }

    }

    private void openImagesActivity() {
        //   Intent intent = new Intent(this, ImagesActivity.class);
        //  startActivity(intent);
    }

    public void search_click(View view) {
        UriList.clear();
        Log.e("Main Activity", "xyz : Search Button Clicked");
        Intent intent = new Intent(this, Filter.class);
        startActivity(intent);
    }

    public void image_view_click(View view) {
        Intent intent = new Intent(this, ImageSlide.class);
        startActivity(intent);
    }

    public void BuyandSell(View view) {

        Intent intent = new Intent(this, BuyAndSell.class);
        startActivity(intent);
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}