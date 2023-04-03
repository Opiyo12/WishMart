package com.example.wishmart;

public class productModel {
private  int productImage, price, discount,imageSample1, ImageSample2, ImageSample3;
private String productName, category, description, description_details;

    public productModel() {
    }

    public productModel(int productImage, int price, int discount, int imageSample1,
                        int ImageSample2, int ImageSample3, String productName,
                        String category, String description, String description_details) {
        this.productImage = productImage;
        this.price = price;
        this.discount = discount;
        this.imageSample1 = imageSample1;
        this.ImageSample2 = ImageSample2;
        this.ImageSample3 = ImageSample3;
        this.productName = productName;
        this.category = category;
        this.description = description;
        this.description_details = description_details;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setImageSample1(int imageSample1) {
        this.imageSample1 = imageSample1;
    }

    public void setGetImageSample2(int getImageSample2) {
        this.ImageSample2 = ImageSample2;
    }

    public void setGetImageSample3(int getImageSample3) {
        this.ImageSample3 = ImageSample3;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDescription_details(String description_details) {
        this.description_details = description_details;
    }

    public int getProductImage() {
        return productImage;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public int getImageSample1() {
        return imageSample1;
    }

    public int getGetImageSample2() {
        return ImageSample2;
    }

    public int getGetImageSample3() {
        return ImageSample3;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getDescription_details() {
        return description_details;
    }
}
