package com.wj.tmall.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
1. 首先浏览器上访问路径 /admin_category_list
2. tomcat根据web.xml上的配置信息，拦截到了/admin_category_list，并将其交由DispatcherServlet处理。
3. DispatcherServlet 根据springMVC的配置，将这次请求交由CategoryController类进行处理，所以需要进行这个类的实例化
4. 在实例化CategoryController的时候，注入CategoryServiceImpl
5. 在实例化CategoryServiceImpl的时候，又注入CategoryMapper
6. 根据ApplicationContext.xml中的配置信息，将CategoryMapper和CategoryMapper.xml关联起来了。
7. 这样就拿到了实例化好了的CategoryController,并调用list方法
8. 在list方法中，访问CategoryService,并获取数据，并把数据放在"cs"上，接着服务端跳转到listCategory.jsp去
9. 最后在listCategory.jsp 中显示数据
 */
public class TestTmall {

    public static void main(String args[]){
        //借助JDBC, 运行代码，创建10条分类测试数据

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tmall_ssm?useUnicode=true&characterEncoding=utf8",
                        "root", "admin");
                Statement s = c.createStatement();
        )
        {
            for (int i = 1; i <=10 ; i++) {
                String sqlFormat = "insert into category values (null, '测试分类%d')";
                String sql = String.format(sqlFormat, i);
                s.execute(sql);
            }

            System.out.println("已经成功创建10条分类测试数据");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}