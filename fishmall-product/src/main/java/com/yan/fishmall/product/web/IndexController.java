package com.yan.fishmall.product.web;

import com.yan.fishmall.product.entity.CategoryEntity;
import com.yan.fishmall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    CategoryService categoryService;

    @GetMapping({"/", "/index.html"})
    public String IndexPage(Model model){

        //TODO 1. 查出所有的一级分类
        List<CategoryEntity> categoryEntities = categoryService.getLevel1Category();

        //视图解析器进行拼串
        //classpath:/template/xxx.html
        model.addAttribute("categorys", categoryEntities);
        return "index";
    }
}
