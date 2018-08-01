package com.proapplab.imageupdownload.imageuploaddownload;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


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
import java.util.StringTokenizer;

public class Filter extends AppCompatActivity  {
    ArrayAdapter adapter;
    ListView listView;
    EditText Search_Text ;
      private List<Upload> mUploads;
    ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Log.e("Filtr ","xyz : Filter Class Oncreate");
        Search_Text=(EditText)findViewById(R.id.search_text);
        mUploads = new ArrayList<>();
        listView=(ListView)findViewById(R.id.search_list);

        names = new ArrayList<>();


        Search_Text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
                names.add("hi");
                adapter = new ArrayAdapter(Filter.this,R.layout.search_list,names);
                names.clear();
                adapter.notifyDataSetChanged();


                String Search_String=s.toString();
                Log.e("Filter clSearch String ", "xyz : " + Search_String);
                DatabaseReference searchRef=FirebaseDatabase.getInstance().getReference("Category").child("xhhx");
                Query query=searchRef.orderByChild("name").startAt(Search_String).endAt(Search_String+ "\uf8ff");

               query.addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.e("Filter Cl OnDataCha Tr ", "xyz : " );
                        for (DataSnapshot searchSnapshot : dataSnapshot.getChildren())
                        {

                            Upload upload = searchSnapshot.getValue(Upload.class);
                            String toAdd=  upload.getName() + "  in  " + upload.getSubCategory();


                            names.add(toAdd);
                            Log.e("Filter class list size ", "xyz : " + names.size());




                            Log.e("Filter Class Names ", "xyz : " + upload.getName());
                        }

                             Set<String> hs=new HashSet<String>();
                        hs.addAll(names);
                        names.clear();
                        names.addAll(hs);

                        adapter = new ArrayAdapter(Filter.this,R.layout.search_list,names);


                        listView.setAdapter(adapter);



                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length()==0)
                {
                    names.clear();
                    adapter.clear();
                    adapter.notifyDataSetChanged();
                }
            }
        });

listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e("Filtr ","xyz : List Clicked");
        TextView text = (TextView) view;
        String fullText=text.toString();
        Log.e("Full text","xyz : " +text.getText() + position);
       String[] split= text.getText().toString().split("in");
       String product=split[0];
       String subcategory=split[1];

        Log.e("After Split","xyz : First String "+product.trim());
        Log.e("After Split","xyz : second String "+subcategory.trim());

        Intent intent = new Intent(Filter.this, ImagesActivity.class);
        intent.putExtra("product",product.trim());
        intent.putExtra("subcategory",subcategory.trim());
        startActivity(intent);

    }
});

    }


}
