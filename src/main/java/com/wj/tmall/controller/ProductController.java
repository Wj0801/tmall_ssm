package com.wj.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wj.tmall.pojo.Category;
import com.wj.tmall.pojo.Product;
import com.wj.tmall.service.CategoryService;
import com.wj.tmall.service.ProductService;
import com.wj.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

//产品管理
@Controller
@RequestMapping("")
public class ProductController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    /*
    1. 在listProduct.jsp提交数据的时候，除了提交产品名称，小标题，原价格，优惠价格，库存外还会提交cid
    2. 在ProductController中获取Product对象，并插入到数据库
    3. 客户端跳转到admin_product_list,并带上参数cid
     */
    @RequestMapping("admin_product_add")
    public String add(Model model, Product p){
        p.setCreateDate(new Date());
        productService.add(p);
        return "redirect:admin_product_list?cid="+p.getCid();
    }

    /*
    1. 在ProductController的delete方法中获取id
    2. 根据id获取Product对象
    3. 借助productService删除这个对象对应的数据
    4. 客户端跳转到admin_product_list，并带上参数cid
     */
    @RequestMapping("admin_product_delete")
    public String delete(int id){
        Product p =productService.get(id);
        productService.delete(id);
        return "redirect:admin_product_list?cid="+p.getCid();
    }

    /*
    1. 在ProductController的edit方法中，根据id获取product对象
    2. 根据product对象的cid产品获取Category对象，并把其设置在product对象的category产品上
    3. 把product对象放在request的 "p" 产品中
    3. 服务端跳转到admin/editProduct.jsp
    4. 在editProduct.jsp中显示产品名称
    5. 在editProduct.jsp中隐式提供id和cid( cid 通过 p.category.id 获取)
     */
    @RequestMapping("admin_product_edit")
    public String edit(Model model,int id){
        Product p=productService.get(id);
        Category c=categoryService.get(p.getCid());
        p.setCategory(c);
        model.addAttribute("p",p);
        return "admin/editProduct";
    }

    /*
    1. 在ProductController的update方法中获取Product对象
    2. 借助productService更新这个对象到数据库
    3. 客户端跳转到admin_product_list，并带上参数cid
     */
    @RequestMapping("admin_product_update")
    public String update(Product p){
        productService.update(p);
        return "redirect:admin_product_list?cid="+p.getCid();
    }

    /*
    查询地址admin_product_list映射的是ProductController的list()方法
    1. 获取分类 cid,和分页对象page
    2. 通过PageHelper设置分页参数
    3. 基于cid，获取当前分类下的产品集合
    4. 通过PageInfo获取产品总数
    5. 把总数设置给分页page对象
    6. 拼接字符串"&cid="+c.getId()，设置给page对象的Param值。 因为产品分页都是基于当前分类下的分页，所以分页的时候需要传递这个cid
    7. 把产品集合设置到 request的 "ps" 产品上
    8. 把分类对象设置到 request的 "c" 产品上。 ( 这个c有什么用呢？ 在 其他-面包屑导航 中会用于显示分类名称)
    9. 把分页对象设置到 request的 "page" 对象上
    10. 服务端跳转到admin/listProduct.jsp页面
    11. 在listProduct.jsp页面上使用c:forEach 遍历ps集合，并显示
     */
    @RequestMapping("admin_product_list")
    public String list(int cid, Model model, Page page){
        Category c=categoryService.get(cid);

        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Product> ps=productService.list(cid);

        int total=(int)new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+c.getId());

        model.addAttribute("ps",ps);
        model.addAttribute("c",c);
        model.addAttribute("page",page);

        return "admin/listProduct";
    }

}
