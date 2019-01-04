
package com.example.webplat.amoldesigning.pojo.model.filter;

import java.util.List;

public class Category {

    private String mainCatagory;

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public String getMainCatagory() {
        return mainCatagory;
    }

    public void setMainCatagory(String mainCatagory) {
        this.mainCatagory = mainCatagory;
    }

    private List<SubCategory> subCategories = null;

    @Override
    public String toString() {
        return mainCatagory;
    }
}
