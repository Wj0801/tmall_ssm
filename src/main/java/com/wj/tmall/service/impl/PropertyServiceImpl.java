package com.wj.tmall.service.impl;

import com.wj.tmall.mapper.PropertyMapper;
import com.wj.tmall.pojo.Property;
import com.wj.tmall.pojo.PropertyExample;
import com.wj.tmall.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//实现接口
@Service
public class PropertyServiceImpl implements PropertyService {
        @Autowired
        PropertyMapper propertyMapper;

        @Override
        public void add(Property p){
            propertyMapper.insert(p);
        }

        @Override
        public void delete(int id){
            propertyMapper.deleteByPrimaryKey(id);
        }

        @Override
        public void update(Property p){
            propertyMapper.updateByPrimaryKeySelective(p);
        }

        @Override
        public Property get(int id){
            return propertyMapper.selectByPrimaryKey(id);
        }
        @Override
        public List list(int cid) {
            PropertyExample example =new PropertyExample();
            example.createCriteria().andCidEqualTo(cid);
            example.setOrderByClause("id asc");
            return propertyMapper.selectByExample(example);
        }
}
