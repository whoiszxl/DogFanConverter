package com.whoiszxl.controller;

import com.whoiszxl.entity.Result;
import com.whoiszxl.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @description: 生成核心控制器
 * @author: whoiszxl
 * @create: 2020-03-11
 **/
@RestController
public class CoreController {

    @Autowired
    private DogService dogService;

    @CrossOrigin
    @PostMapping("/converter")
    public Result converter(@RequestBody HashMap<String, String> params) {
        String oldArticle = params.get("text");
        if(StringUtils.isEmpty(oldArticle)) {
            return Result.buildError("传入文章为空");
        }
        return dogService.converter(oldArticle);
    }





}
