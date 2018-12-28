package com.wj.tmall.service.impl;

import com.wj.tmall.mapper.UserMapper;
import com.wj.tmall.pojo.UserExample;
import com.wj.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wj.tmall.pojo.User;

import java.util.List;



@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void add(User u) {
        userMapper.insert(u);
    }

    @Override
    public void delete(int id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(User u) {
        userMapper.updateByPrimaryKeySelective(u);
    }

    @Override
    public User get(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public List<User> list(){
        UserExample example =new UserExample();
        example.setOrderByClause("id asc");
        return userMapper.selectByExample(example);

    }

    //判断名称是否已经被使用过
    @Override
    public boolean isExist(String name) {
        UserExample example =new UserExample();
        example.createCriteria().andNameEqualTo(name);
        List<User> result= userMapper.selectByExample(example);
        if(!result.isEmpty())
            return true;
        return false;

    }

    @Override
    public User get(String name,String password){
        UserExample example=new UserExample();
        example.createCriteria().andNameEqualTo(name);
        List<User> result =userMapper.selectByExample(example);
        if(result.isEmpty())
            return null;
       return result.get(0);

    }
}
