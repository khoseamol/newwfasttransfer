
package com.example.webplat.amoldesigning.pojo.bbpsbilling;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Datum {

    @SerializedName("FieldType")
    private String mFieldType;
    @SerializedName("Ismandatory")
    private Boolean mIsmandatory;
    @SerializedName("MaxLength")
    private Long mMaxLength;
    @SerializedName("MinLenght")
    private Long mMinLenght;
    @SerializedName("Name")
    private String mName;
    @SerializedName("Pattern")
    private String mPattern;

    public String getFieldType() {
        return mFieldType;
    }

    public void setFieldType(String fieldType) {
        mFieldType = fieldType;
    }

    public Boolean getIsmandatory() {
        return mIsmandatory;
    }

    public void setIsmandatory(Boolean ismandatory) {
        mIsmandatory = ismandatory;
    }

    public Long getMaxLength() {
        return mMaxLength;
    }

    public void setMaxLength(Long maxLength) {
        mMaxLength = maxLength;
    }

    public Long getMinLenght() {
        return mMinLenght;
    }

    public void setMinLenght(Long minLenght) {
        mMinLenght = minLenght;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPattern() {
        return mPattern;
    }

    public void setPattern(String pattern) {
        mPattern = pattern;
    }

}
