package com.example.webplat.amoldesigning.pojo.operator;

import java.io.Serializable;

/**
 * Created by Administrator on 15/09/2016.
 */
public class OperatorList implements Serializable {
    String opId;
    String opName;
    String opImageURL;
    String opType;

    public String getOpImageURL() {
        return opImageURL;
    }

    public void setOpImageURL(String opImageURL) {
        this.opImageURL = opImageURL;
    }



    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public String getOpId() {
        return opId;
    }

    public void setOpId(String opId) {
        this.opId = opId;
    }



    @Override
    public String toString() {
        return opName;
    }
}
