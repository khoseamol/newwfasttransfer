
package com.example.webplat.amoldesigning.pojo.bbpscategory;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Datum {

    @SerializedName("Categoryid")
    private int mCategoryid;
    @SerializedName("Categoryname")
    private String mCategoryname;

    public int getCategoryid() {
        return mCategoryid;
    }

    public void setCategoryid(int categoryid) {
        mCategoryid = categoryid;
    }

    public String getCategoryname() {
        return mCategoryname;
    }

    public void setCategoryname(String categoryname) {
        mCategoryname = categoryname;
    }

    @Override
    public String toString() {
        return mCategoryname;
    }
}
