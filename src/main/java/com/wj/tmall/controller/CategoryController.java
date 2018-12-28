package com.wj.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wj.tmall.pojo.Category;
import com.wj.tmall.service.CategoryService;
import com.wj.tmall.util.ImageUtil;
import com.wj.tmall.util.Page;
import com.wj.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
/*
注解@Controller声明当前类是一个控制器
注解@RequestMapping("")表示访问的时候无需额外的地址
注解@Autowired把CategoryServiceImpl自动装配进了CategoryService 接口
注解@RequestMapping("admin_category_list") 映射admin_category_list路径的访问
在list方法中，通过categoryService.list()获取所有的Category对象，然后放在"cs"中，并服务端跳转到 “admin/listCategory” 视图。
“admin/listCategory” 会根据后续的springMVC.xml 配置文件，跳转到 WEB-INF/jsp/admin/listCategory.jsp 文件
 */
@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /*  分页方法
      1. 为方法list增加参数Page用于获取浏览器传递过来的分页信息
      2. categoryService.list(page); 获取当前页的分类集合
      3. 通过categoryService.total(); 获取分类总数
      4. 通过page.setTotal(total); 为分页对象设置总数
      5. 把分类集合放在"cs"中
      6. 把分页对象放在 "page“ 中
      7. 跳转到listCategory.jsp页面
     */
    @RequestMapping("admin_category_list")
    public String list(Model model, Page page) {
        //通过分页插件指定分页参数
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Category> cs = categoryService.list();
        int total = (int)new PageInfo<>(cs).getTotal();
        page.setTotal(total);
        model.addAttribute("cs", cs);
        model.addAttribute("page", page);
        return "admin/listCategory";
    }

    /* 增加方法
    1. add方法映射路径admin_category_add的访问
    1.1 参数 Category c接受页面提交的分类名称
    1.2 参数 session 用于在后续获取当前应用的路径
    1.3 UploadedImageFile 用于接受上传的图片
    2. 通过categoryService保存c对象
    3. 通过session获取ControllerContext,再通过getRealPath定位存放分类图片的路径。
    4. 根据分类id创建文件名
    5. 如果/img/category目录不存在，则创建该目录，否则后续保存浏览器传过来图片，会提示无法保存
    6. 通过UploadedImageFile 把浏览器传递过来的图片保存在上述指定的位置
    7. 通过ImageUtil.change2jpg(file); 确保图片格式一定是jpg，而不仅仅是后缀名是jpg.
    8. 客户端跳转到admin_category_list
     */
    @RequestMapping("admin_category_add")
    public String add(Category c, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        System.out.println(c.getId());
        categoryService.add(c);
        System.out.println(c.getId());
        File  imageFolder= new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,c.getId()+".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        System.out.println(uploadedImageFile);
        System.out.println(uploadedImageFile.getImage());
        System.out.println(file);
        uploadedImageFile.getImage().transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);

        return "redirect:/admin_category_list";
    }



        /*
        增加删除方法
        1. 映射路径admin_category_delete
        2. 提供参数接受id注入
        3. 提供session参数，用于后续定位文件位置
        3. 通过categoryService删除数据
        4. 通过session获取ControllerContext然后获取分类图片位置，接着删除分类图片
        5. 客户端跳转到 admin_category_list
         */
    @RequestMapping("admin_category_delete")
    public String delete(int id,HttpSession session) throws IOException {
        categoryService.delete(id);

        File  imageFolder= new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,id+".jpg");
        file.delete();

        return "redirect:/admin_category_list";
    }

    /*
    增加edit方法
    1. 映射admin_category_edit路径的访问
    2. 参数id用来接受注入
    3. 通过categoryService.get获取Category对象
    4. 把对象放在“c"上
    5. 返回editCategory.jsp
     */
    @RequestMapping("admin_category_edit")
    public  String edit(int id,Model model)throws  IOException{
        Category c=categoryService.get(id);
        model.addAttribute("c",c);

        return  "admin/editCategory";
    }

    /*
    增加update方法
    1. update 方法映射路径admin_category_update的访问
    1.1 参数 Category c接受页面提交的分类名称
    1.2 参数 session 用于在后续获取当前应用的路径
    1.3 UploadedImageFile 用于接受上传的图片
    2. 通过categoryService更新c对象
    3. 首先判断是否有上传图片，如果有上传，那么通过session获取ControllerContext,再通过getRealPath定位存放分类图片的路径。
    如果严格按照本教程的做法，使用idea中的tomcat部署的话，那么图片就会存放在:E:\project\tmall_ssm\target\tmall_ssm\img\category 这里
    4. 根据分类id创建文件名
    5. 通过UploadedImageFile 把浏览器传递过来的图片保存在上述指定的位置
    6. 通过ImageUtil.change2jpg(file); 确保图片格式一定是jpg，而不仅仅是后缀名是jpg.
    7. 客户端跳转到admin_category_list
     */
    @RequestMapping("admin_category_update")
    public String update(Category c,HttpSession session,UploadedImageFile uploadedImageFile)throws IOException{
        categoryService.update(c);
        MultipartFile image=uploadedImageFile.getImage();
        if(null!=image && !image.isEmpty()){
            File  imageFolder= new File(session.getServletContext().getRealPath("img/category"));
            File file = new File(imageFolder,c.getId()+".jpg");
            image.transferTo(file);
            BufferedImage img=ImageUtil.change2jpg(file);
            ImageIO.write(img,"jpg",file);
        }

        return  "redirect:/admin_category_list";
    }



}




