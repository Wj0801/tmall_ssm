package com.wj.tmall.service.impl;

import com.wj.tmall.mapper.CategoryMapper;
import com.wj.tmall.pojo.Category;
import com.wj.tmall.pojo.CategoryExample;
import com.wj.tmall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*
新建CategoryService接口的实现类CategoryServiceImpl
注解@Service声明当前类是一个Service类
通过自动装配@Autowired引入CategoryMapper ，在list方法中调用CategoryMapper 的list方法.
 */
@Service
public class CategoryServiceImpl  implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    /*
    //支持分页的查询方法list(Page page)
    public List<Category> list(Page page){
        return categoryMapper.list(page);
    };
    //获取总数的方法total
    @Override
    public int total(){
        return  categoryMapper.total();
    }
    */

    //pagehelper分页插件list方法
    @Override
    public List<Category> list() {
        CategoryExample example = new CategoryExample();
        example.setOrderByClause("id asc");
        return categoryMapper.selectByExample(example);
    }

    //add方法的实现
    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
    }
    //删除方法的实现
    @Override
    public void delete(int id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    //get方法的实现
    @Override
    public Category get(int id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    //update修改方法的实现
    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }
}