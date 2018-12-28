package com.wj.tmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wj.tmall.pojo.Category;
import com.wj.tmall.pojo.Property;
import com.wj.tmall.service.CategoryService;
import com.wj.tmall.service.PropertyService;
import com.wj.tmall.util.Page;

import java.util.List;
//控制器类，用于映射不同路径的访问
@Controller
@RequestMapping("")
public class PropertyController {
        @Autowired
        CategoryService categoryService;
        @Autowired
         PropertyService propertyService;
         /*
           1. 在listProperty.jsp页面提交数据的时候，除了提交属性名称，还会提交cid
           2. 在PropertyController通过参数Property 接受注入
           3. 通过propertyService保存到数据库
           4. 客户端跳转到admin_property_list,并带上参数cid
            */
        @RequestMapping("admin_property_add")
        public String add(Property p){
            propertyService.add(p);
            return  "redirect:admin_property_list?cid="+p.getCid();
        }
        /*
        1. 在PropertyController的delete方法中获取id
        2. 根据id获取Property对象
        3. 借助propertyService删除这个对象对应的数据
        4. 客户端跳转到admin_property_list，并带上参数cid
         */
        @RequestMapping("admin_property_delete")
        public String delete(int id){
            Property p=propertyService.get(id);
            propertyService.delete(id);
            return "redirect:admin_property_list="+p.getCid();
        }
        /*
        1. 在PropertyController的edit方法中，根据id获取Property对象
        2. 根据properoty对象的cid属性获取Category对象，并把其设置在Property对象的category属性上
        3. 把Property对象放在request的 "p" 属性中
        4. 服务端跳转到admin/editProperty.jsp
        5. 在editProperty.jsp中显示属性名称
        6. 在editProperty.jsp中隐式提供id和cid( cid 通过 p.category.id 获取)
         */
        @RequestMapping("admin_property_edit")
        public String edit(Model model,int id) {
          Property p= propertyService.get(id);
          Category c=categoryService.get(p.getCid());
          p.setCategory(c);
          model.addAttribute("p",p);
        return  "admin/editProperty";
        }

        /*
        1. 在PropertyController的update方法中获取Property对象
        2. 借助propertyService更新这个对象到数据库
        3. 客户端跳转到admin_property_list，并带上参数cid
           */
        @RequestMapping("admin_property_update")
        public  String update(Property p){
            propertyService.update(p);
            return "redirect:admin_property_list?cid="+p.getCid();
        }

        /*
        查询地址admin_property_list映射的是PropertyController的list()方法
        1. 获取分类 cid,和分页对象page
        2. 通过PageHelper设置分页参数
        3. 基于cid，获取当前分类下的属性集合
        4. 通过PageInfo获取属性总数
        5. 把总数设置给分页page对象
        6. 拼接字符串"&cid="+c.getId()，设置给page对象的Param值。 因为属性分页都是基于当前分类下的分页，所以分页的时候需要传递这个cid
        7. 把属性集合设置到 request的 "ps" 属性上
        8. 把分类对象设置到 request的 "c" 属性上。 ( 这个c有什么用呢？ 在后面步骤的 其他-面包屑导航 中会用于显示分类名称)
        9. 把分页对象设置到 request的 "page" 对象上
        10. 服务端跳转到admin/listProperty.jsp页面
        11. 在listProperty.jsp页面上使用c:forEach 遍历ps集合，并显示
         */
        @RequestMapping("admin_property_list")
        public String list(int cid,Model model,Page page){
           Category c=categoryService.get(cid);
           PageHelper.offsetPage(page.getStart(),page.getCount());
           List<Property> ps= propertyService.list(cid);

           int total=(int)new PageInfo<>(ps).getTotal();
           page.setTotal(total);
           page.setParam("&cid="+c.getId());

           model.addAttribute("ps",ps);
           model.addAttribute("c",c);
           model.addAttribute("page",page);
            return  "admin/listProperty";
        }

}
