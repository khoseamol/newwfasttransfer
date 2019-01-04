package com.example.webplat.amoldesigning.pojo.operator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15/09/2016.
 */
public class MainOperator implements Serializable {
    private int imageIcon;
    String headerTitle;

    List<OperatorList> operatorList=new ArrayList<>();





    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public List<OperatorList> getOperatorList() {
        return operatorList;
    }

    public void setOperatorList(List<OperatorList> operatorList) {
        this.operatorList = operatorList;
    }




    public int getImageIcon() {
        return imageIcon;
    }
}
