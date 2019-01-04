
package com.example.webplat.amoldesigning.pojo.model.filter;

import java.util.List;

public class SubCategory {

    private String subCategoryName;

    public List<SubSubCategory> getSubSubCategories() {
        return subSubCategories;
    }

    public void setSubSubCategories(List<SubSubCategory> subSubCategories) {
        this.subSubCategories = subSubCategories;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    private List<SubSubCategory> subSubCategories = null;

    @Override
    public String toString() {
        return subCategoryName;
    }
}
