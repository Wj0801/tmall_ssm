package com.wj.tmall.service;
import com.wj.tmall.pojo.Category;
import java.util.List;


public interface CategoryService{
    /*
    //支持分页的查询方法list(Page page)
    List<Category> list(Page page);
    //获取总数的方法total
    int total();
    //增加的方法add
    */
    //pagehelper分页插件
    List<Category> list();
    void add(Category category);
    //删除的方法
    void delete(int id);
    //增加get方法
    Category get(int id);
    //增加update修改方法
    void update(Category category);

}