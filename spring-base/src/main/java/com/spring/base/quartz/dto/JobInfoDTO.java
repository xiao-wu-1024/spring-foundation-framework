package com.spring.base.quartz.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.quartz.JobDataMap;

/**
 * job信息类
 * @author xiao-_-wu
 * @date 2021/3/26 10:39
 */
@Data
@Accessors(chain = true)
public class JobInfoDTO {

    /**
     * job
     */
    private KeyDTO jobKey;
    /**
     * 包路径
     */
    private String className;
    /**
     * map(用于传值)
     */
    private JobDataMap jobDataMap;
}

