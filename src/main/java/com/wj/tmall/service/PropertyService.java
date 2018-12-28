package com.wj.tmall.service;

import com.wj.tmall.pojo.Property;

import java.util.List;

//属性管理， CRUD.需要查询某个分类下的属性，所以list方法会带上对应分类的id。
public interface PropertyService {
            void add(Property c);
            void delete(int id);
            void update(Property c);
            Property get(int id);
             List list(int cid);

}
