package com.spring.base.quartz.dto;

import lombok.Data;

/**
 * job入参
 *
 * @author xiao-_-wu
 * @date 2021/3/25 10:10
 */
@Data
public class JobDetailsDTO {

    /**
     * job类包名
     */
    private String jobClass;

    /**
     * job定时工作类型,非必传
     */
    private Integer jobType;
    /**
     * 工作分组,非必传
     */
    private String jobGroup;
    /**
     * 触发器分组,非必传
     */
    private String triggerGroup;
    /**
     * 其他信息,非必传
     */
    private String massage;
    /**
     * 间隔时间/ 秒
     */
    private Integer time;
}
