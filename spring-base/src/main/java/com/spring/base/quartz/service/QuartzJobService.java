package com.spring.base.quartz.service;

import com.spring.base.quartz.dto.JobAndTriggerDTO;
import com.spring.base.quartz.dto.JobDetailsDTO;

/**
 * 定时任务接口
 * @author wu-_-jia
 */
public interface QuartzJobService {

    /**
     * 暂停定时任务
     * @param jobDetailName 名称
     * @return ret
     */
    String stopTask(String jobDetailName);

    /**
     * 恢复定时任务
     * @param jobDetailName 名称
     * @return ret
     */
    String restoreTask(String jobDetailName);

    /**
     * 新增定时任务
     * @return ret
     * @param dto
     */
    String addTask(JobDetailsDTO dto);

    /**
     * 删除定时任务
     * @return ret
     * @param jobClassName
     */
    String removeTask(String jobClassName);

    /**
     * 修改定时任务
     * @return ret
     * @param dto
     */
    String modifyTask(JobDetailsDTO dto);

    /**
     * 查看当前任务信息
     * @return ret
     */
    JobAndTriggerDTO listTask();

}
