package com.spring.base.quartz.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 列表参数类
 * @author xiao-_-wu
 * @date 2021/3/25 16:55
 */
@Data
public class JobAndTriggerDTO implements Serializable {
    private static final long serialVersionUID = -686788394437570442L;

    /**
     * jobDetail集合
     */
    List<JobInfoDTO> jobInfos;

}
