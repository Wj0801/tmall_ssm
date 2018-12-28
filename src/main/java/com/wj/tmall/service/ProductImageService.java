package com.wj.tmall.service;

import com.wj.tmall.pojo.ProductImage;

import java.util.List;

public interface ProductImageService  {

    String type_single="type_single";//单个图片
    String type_detail="type_detail";//详情图片

     //CRUD
    void add(ProductImage pi);
    void delete(int id);
    void update(ProductImage pi);
    ProductImage get(int id);
    List list(int pid,String type); //根据产品id和图片类型查询的list方法

}
