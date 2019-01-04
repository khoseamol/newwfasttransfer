package com.example.webplat.amoldesigning.Utils;





import com.example.webplat.amoldesigning.pojo.plans.PlansDatum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by webplat on 28/08/2015.
 */
public class ListViewDataFilter {


    public static List<PlansDatum> filterByRecharegePlans(List<PlansDatum> data, String plans) {
        List<PlansDatum> filteredList=new ArrayList<PlansDatum>();
        for (PlansDatum plansDatum : data) {

            if(plansDatum.getRechargeType().equals(plans)){
                filteredList.add(plansDatum);
            }

        }
        return filteredList;
    }


}
