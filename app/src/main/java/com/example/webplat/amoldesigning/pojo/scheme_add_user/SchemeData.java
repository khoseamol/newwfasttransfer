
package com.example.webplat.amoldesigning.pojo.scheme_add_user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class SchemeData {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("SchemeName")
    @Expose
    private String schemeName;
    @SerializedName("SchemeType")
    @Expose
    private String schemeType;
    @SerializedName("Amount")
    @Expose
    private String amount;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The Id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The schemeName
     */
    public String getSchemeName() {
        return schemeName;
    }

    /**
     * 
     * @param schemeName
     *     The SchemeName
     */
    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    /**
     * 
     * @return
     *     The schemeType
     */
    public String getSchemeType() {
        return schemeType;
    }

    /**
     * 
     * @param schemeType
     *     The SchemeType
     */
    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType;
    }

    /**
     * 
     * @return
     *     The amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * 
     * @param amount
     *     The Amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return schemeName;
    }
}
