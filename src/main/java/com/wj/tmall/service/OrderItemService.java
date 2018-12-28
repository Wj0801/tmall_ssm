package com.wj.tmall.service;

import com.wj.tmall.pojo.Order;
import com.wj.tmall.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {

    void add(OrderItem c);
    void delete(int id);
    void update(OrderItem c);
    OrderItem get(int id);
    List list();
    void fill(List<Order> os);
    void fill(Order o);

    //根据产品获取销售量
    int getSaleCount(int pid);

    List<OrderItem> listByUser(int uid);
}
