package com.wj.tmall.service;
import com.wj.tmall.pojo.Category;
import com.wj.tmall.pojo.Product;
import java.util.List;

public interface ProductService {
    void add(Product p);
    void delete(int id);
    void update(Product p);
    Product get(int id);
    List list(int cid);
    void  setFirstProductImage(Product p);


    void fill(List<Category> cs);

    void fill(Category c);

    void fillByRow(List<Category> cs);

    //产品销量
    void setSaleAndReviewNumber(Product p);

    //评价数量
    void setSaleAndReviewNumber(List<Product> ps);

    //通过关键字进行模糊查询
    List<Product> search(String keyword);
}
