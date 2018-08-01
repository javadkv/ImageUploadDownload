package com.proapplab.imageupdownload.imageuploaddownload;

import android.util.Log;

import com.google.firebase.database.Exclude;

/**
 * Created by Javad on 13/04/2018.
 */

public class Upload {
    private String mName;


    public Upload(String brand, String sub_category, String category, String department, String store, String country, String productName, String url1, String url2, String url3, String url4) {
        this.country = country;
        this.store = store;
        this.department = department;
        this.category = category;
        this.subcategory = sub_category;
        this.mImageUrl1 = url1;
        this.mName = productName;
        this.brand = brand;
        this.mImageUrl2 = url2;
        this.mImageUrl3 = url3;
        this.mImageUrl4 = url4;
    }


    public Upload(String brand, String sub_category, String category, String department, String store, String country, String productName, String url1, String url2, String url3, String url4, String url5) {
        this.country = country;
        this.store = store;
        this.department = department;
        this.category = category;
        this.subcategory = sub_category;
        this.mImageUrl1 = url1;
        this.mName = productName;
        this.brand = brand;
        this.mImageUrl2 = url2;

        this.mImageUrl3 = url3;
        this.mImageUrl4 = url4;
        this.mImageUrl5 = url5;
    }

    public String getUploadid() {
        return uploadid;
    }

    public void setUploadid(String uploadid) {
        this.uploadid = uploadid;
    }

    public Upload(String brand, String sub_category, String category, String department, String store, String country, String productName, String uploadid, String url1, String url2, String url3, String url4, String url5, String url6) {
        this.country = country;
        this.store = store;
        this.department = department;
        this.category = category;
        this.subcategory = sub_category;
        this.mImageUrl1 = url1;
        this.mName = productName;
        this.brand = brand;
        this.mImageUrl2 = url2;
        this.mImageUrl3 = url3;
        this.mImageUrl4 = url4;
        this.mImageUrl5 = url5;
        this.mImageUrl6 = url6;

        this.uploadid = uploadid;
        Log.e("Upload ", "xyz : upload id6 " + uploadid);
    }

    public String getmImageUrl3() {
        return mImageUrl3;
    }

    public void setmImageUrl3(String mImageUrl3) {
        this.mImageUrl3 = mImageUrl3;
    }

    public Upload(String brand, String sub_category, String category, String department, String store, String country, String productName, String url1, String url2, String url3) {

        this.country = country;
        this.store = store;
        this.department = department;
        this.category = category;
        this.subcategory = sub_category;
        this.mImageUrl1 = url1;
        this.mName = productName;
        this.brand = brand;
        this.mImageUrl2 = url2;
        this.mImageUrl3 = url3;


    }

    public String getmImageUrl2() {
        return mImageUrl2;
    }

    public void setmImageUrl2(String mImageUrl2) {
        this.mImageUrl2 = mImageUrl2;
    }

    public String getmImageUrl1() {
        return mImageUrl1;
    }

    public void setmImageUrl1(String mImageUrl1) {
        this.mImageUrl1 = mImageUrl1;
    }

    private String mImageUrl1, mImageUrl2, mImageUrl3, mImageUrl4, mImageUrl5, mImageUrl6, mImageUrl7;
    private String uploadid;
    private String mKey;
    private Integer mI;

    public String getmImageUrl4() {
        return mImageUrl4;
    }

    public void setmImageUrl4(String mImageUrl4) {
        this.mImageUrl4 = mImageUrl4;
    }

    public String getmImageUrl5() {
        return mImageUrl5;
    }

    public void setmImageUrl5(String mImageUrl5) {
        this.mImageUrl5 = mImageUrl5;
    }

    public String getmImageUrl6() {
        return mImageUrl6;
    }

    public void setmImageUrl6(String mImageUrl6) {
        this.mImageUrl6 = mImageUrl6;
    }

    public String getmImageUrl7() {
        return mImageUrl7;
    }

    public void setmImageUrl7(String mImageUrl7) {
        this.mImageUrl7 = mImageUrl7;
    }

    private String country;
    private String store;
    private String department;
    private String category;
    private String subcategory;
    private String brand;

    public Upload(String brand, String sub_category, String category, String department, String store, String country, String productName, String url1, String url2) {
        this.country = country;
        this.store = store;
        this.department = department;
        this.category = category;
        this.subcategory = sub_category;
        this.mImageUrl1 = url1;
        this.mName = productName;
        this.brand = brand;
        this.mImageUrl2 = url2;
    }


    public Integer getmI() {
        return mI;
    }

    public void setmI(Integer mI) {
        this.mI = mI;
    }




 /*   public Upload(String brand, String sub_category, String category, String department, String store, String country, String productName) {
        Log.e("Upload Class", "xyz : public Upload bnd,sub_,cat,dep,sto,cou,proName");
        this.country = country;
        this.store = store;
        this.department = department;
        this.category = category;
        this.subCategory = sub_category;

        this.mName = productName;
        this.brand = brand;
    }
*/
 /*   public Upload(String imageUrl) {
        Log.e("Upload Class", "xyz : public Upload(String imageUrl  mImageUrl=imageUrl;)");
        mImageUrl = imageUrl;
    }*/

    /*   public Upload(String brand, String sub_category, String category, String department, String store, String country, String productName, String ImageUrl1, String ImageUrl2) {

        Log.e("Upload Class", "xyz : public Upload bnd,sub_,cat,dep,sto,cou,proName,mImaUri");
        this.country = country;
        this.store = store;
        this.department = department;
        this.category = category;
        this.subCategory = sub_category;
        this.mImageUrl1 = ImageUrl1;
        this.mImageUrl2 = ImageUrl2;

        this.mName = productName;
        this.brand = brand;
    }*/

    @Exclude

    public String getmKey() {
        Log.e("Upload Class", "xyz : public String getmKey()");
        return mKey;
    }

    @Exclude
    public void setmKey(String mKey) {
        Log.e("Upload Class", "xyz : public void setmKey(String mKey");
        this.mKey = mKey;
    }


    public String getBrand() {
        Log.e("Upload Class", "xyz :  public String getBrand()");
        return brand;
    }

    public void setBrand(String brand) {
        Log.e("Upload Class", "xyz :    public void setBrand(String brand)");
        this.brand = brand;
    }


    public String getCountry() {
        Log.e("Upload Class", "xyz :     public String getCountry()");
        return country;
    }

    public void setCountry(String country) {
        Log.e("Upload Class", "xyz :   public void setCountry(String country)");
        Log.e("Upload Class", "xyz :  country " + country);
        this.country = country;
    }

    public String getStore() {
        Log.e("Upload Class", "xyz :  public String getStore()");
        return store;
    }

    public void setStore(String store) {
        Log.e("Upload Class", "xyz :   public void setStore(String store)");
        this.store = store;
    }

    public String getDepartment() {
        Log.e("Upload Class", "xyz :   public String getDepartment()");
        return department;
    }

    public void setDepartment(String department) {
        Log.e("Upload Class", "xyz :  public void setDepartment(String department)");
        this.department = department;
    }

    public String getCategory() {
        Log.e("Upload Class", "xyz : public String getCategory()");
        return category;
    }

    public void setCategory(String category) {
        Log.e("Upload Class", "xyz : public void setCategory(String category)");
        this.category = category;
    }

    public String getSubCategory() {
        Log.e("Upload Class", "xyz :  public String getSubCategory()");
        return subcategory;
    }

    public void setSubCategory(String subCategory) {
        Log.e("Upload Class", "xyz :  public void setSubCategory(String subCategory)");
        this.subcategory = subCategory;
    }


    /*   public Upload(String productName, String s) {
           Log.e("Upload Class", "xyz :   public Upload(String productName, String s)");
           //empty constructor needed
       }
   */
    public Upload() {
        //empty constructor needed
        Log.e("Upload Class", "xyz : public Upload() empty constructor");
    }


    /*   public Upload(String name, String imageUrl, Integer i) {
           Log.e("Upload Class", "xyz :public Upload(String name, String imageUrl,Integer i)");
           if (name.trim().equals("")) {
               name = "No Name";
           }

           mName = name;
           mImageUrl = imageUrl + i.toString();
           mI = i;
       }

   */
    public String getName() {
        Log.e("Upload Class", "xyz :   public String getName()");
        return mName;
    }

    public void setName(String name) {
        Log.e("Upload Class", "xyz :   public void setName(String name)");
        mName = name;
    }


    public Upload(String brand, String sub_category, String category, String department, String store, String country, String name, String mImageUrl) {
        Log.e("Upload Class", "xyz : public Upload bnd,sub_,cat,dep,sto,cou,proName,mImaUri");
        if (name.trim().equals("")) {
            name = "No Name";
        }


        this.country = country;
        this.store = store;
        this.department = department;
        this.category = category;
        this.subcategory = sub_category;
        this.mImageUrl1 = mImageUrl;
        this.mName = name;
        this.brand = brand;

    }

}