
package com.example.webplat.amoldesigning.pojo.plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class OperatorByNumberData {

    @SerializedName("Id")
    @Expose
    private Integer Id;
    @SerializedName("ServiceId")
    @Expose
    private Object ServiceId;
    @SerializedName("OperatorName")
    @Expose
    private String OperatorName;
    @SerializedName("OurCode")
    @Expose
    private Object OurCode;
    @SerializedName("Image")
    @Expose
    private String Image;
    @SerializedName("IsSpecial")
    @Expose
    private Boolean IsSpecial;
    @SerializedName("SpecialOpId")
    @Expose
    private Integer SpecialOpId;
    @SerializedName("Circles")
    @Expose
    private String Circles;

    /**
     *
     * @return
     * The Id
     */
    public Integer getId() {
        return Id;
    }

    /**
     *
     * @param Id
     * The Id
     */
    public void setId(Integer Id) {
        this.Id = Id;
    }

    /**
     *
     * @return
     * The ServiceId
     */
    public Object getServiceId() {
        return ServiceId;
    }

    /**
     *
     * @param ServiceId
     * The ServiceId
     */
    public void setServiceId(Object ServiceId) {
        this.ServiceId = ServiceId;
    }

    /**
     *
     * @return
     * The OperatorName
     */
    public String getOperatorName() {
        return OperatorName;
    }

    /**
     *
     * @param OperatorName
     * The OperatorName
     */
    public void setOperatorName(String OperatorName) {
        this.OperatorName = OperatorName;
    }

    /**
     *
     * @return
     * The OurCode
     */
    public Object getOurCode() {
        return OurCode;
    }

    /**
     *
     * @param OurCode
     * The OurCode
     */
    public void setOurCode(Object OurCode) {
        this.OurCode = OurCode;
    }

    /**
     *
     * @return
     * The Image
     */
    public String getImage() {
        return Image;
    }

    /**
     *
     * @param Image
     * The Image
     */
    public void setImage(String Image) {
        this.Image = Image;
    }

    /**
     *
     * @return
     * The IsSpecial
     */
    public Boolean getIsSpecial() {
        return IsSpecial;
    }

    /**
     *
     * @param IsSpecial
     * The IsSpecial
     */
    public void setIsSpecial(Boolean IsSpecial) {
        this.IsSpecial = IsSpecial;
    }

    /**
     *
     * @return
     * The SpecialOpId
     */
    public Integer getSpecialOpId() {
        return SpecialOpId;
    }

    /**
     *
     * @param SpecialOpId
     * The SpecialOpId
     */
    public void setSpecialOpId(Integer SpecialOpId) {
        this.SpecialOpId = SpecialOpId;
    }

    /**
     *
     * @return
     * The Circles
     */
    public String getCircles() {
        return Circles;
    }

    /**
     *
     * @param Circles
     * The Circles
     */
    public void setCircles(String Circles) {
        this.Circles = Circles;
    }

}


