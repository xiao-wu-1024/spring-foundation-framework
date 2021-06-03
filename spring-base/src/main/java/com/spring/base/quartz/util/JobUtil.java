package com.spring.base.quartz.util;

import org.quartz.Job;

/**
 * @author xiao-_-wu
 * @date 2021/6/3 15:17
 */
public class JobUtil {

    /**
     * 根据类包名获取类
     * @param jobClass 包名
     * @return ret'
     * @throws IllegalAccessException 非法访问异常
     * @throws InstantiationException 实例化异常
     * @throws ClassNotFoundException 类找不到异常
     */
    public static Job getJobClass(String jobClass) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class<?> job = Class.forName(jobClass);
        return (Job)job.newInstance();
    }

}
