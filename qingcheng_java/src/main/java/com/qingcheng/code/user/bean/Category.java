package com.qingcheng.code.user.bean;

/**
 * Created by liguohua on 31/12/2016.
 */
public class Category {
    private String categoryId;
    private String categoryName;
    private String categoryNameCn;
    private String categoryIconName;
    private String categoryColor;
    private String categorySelected;


    public Category() {
    }

    public Category(String categoryId, String categoryName, String categoryNameCn, String categoryIconName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryNameCn = categoryNameCn;
        this.categoryIconName = categoryIconName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryNameCn() {
        return categoryNameCn;
    }

    public void setCategoryNameCn(String categoryNameCn) {
        this.categoryNameCn = categoryNameCn;
    }

    public String getCategoryIconName() {
        return categoryIconName;
    }

    public void setCategoryIconName(String categoryIconName) {
        this.categoryIconName = categoryIconName;
    }

    public String getCategoryColor() {
        return categoryColor;
    }

    public void setCategoryColor(String categoryColor) {
        this.categoryColor = categoryColor;
    }

    public String getCategorySelected() {
        return categorySelected;
    }

    public void setCategorySelected(String categorySelected) {
        this.categorySelected = categorySelected;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryNameCn='" + categoryNameCn + '\'' +
                ", categoryIconName='" + categoryIconName + '\'' +
                ", categoryColor='" + categoryColor + '\'' +
                ", categorySelected='" + categorySelected + '\'' +
                '}';
    }
}
