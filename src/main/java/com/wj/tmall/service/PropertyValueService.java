package com.wj.tmall.service;

import com.wj.tmall.pojo.Product;
import com.wj.tmall.pojo.PropertyValue;

import java.util.List;

public interface PropertyValueService {
        void init(Product p);
        void update(PropertyValue pv);

        PropertyValue get(int ptid, int pid);
        List<PropertyValue> list(int pid);
}