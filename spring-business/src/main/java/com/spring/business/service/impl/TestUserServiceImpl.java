package com.spring.business.service.impl;


import cn.hutool.db.Entity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.business.entity.TestUser;
import com.spring.business.mapper.TestUserMapper;
import com.spring.business.service.ITestUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户表(TestUser)表服务实现类
 *
 * @author xiao_wu
 * @since 2021-04-08 17:05:42
 */
@Service
public class TestUserServiceImpl extends ServiceImpl<TestUserMapper, TestUser> implements ITestUserService {
    @Resource
    private TestUserMapper testUserMapper;

    public static void main(String[] args) {
    }
}

