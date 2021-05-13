package com.spring.portal.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.business.entity.TestUser;
import com.spring.business.service.ITestUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xiao-_-wu
 * @date 2021/5/13 14:30
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private ITestUserService testUserService;

    @GetMapping("/test")
    public String test(){
        System.out.println("过来了");
        Page<TestUser> page = new Page<>(1,10);

        page = testUserService.page(page);
        System.out.println("分页" + page.getRecords());

        TestUser user = testUserService.lambdaQuery()
                .eq(TestUser::getId, 1)
                .one();
        System.out.println(user.toString());
        return "ok";
    }

}
