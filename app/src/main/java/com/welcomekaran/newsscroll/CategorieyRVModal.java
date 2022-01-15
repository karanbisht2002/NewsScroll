package com.welcomekaran.newsscroll;

public class CategorieyRVModal {

    private String category;
    private String categoryimage;

    public CategorieyRVModal(String category, String categoryimage) {
        this.category = category;
        this.categoryimage = categoryimage;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryimage() {
        return categoryimage;
    }
    public void setCategoryimage(String categoryimage) {
        this.categoryimage = categoryimage;
    }

}
