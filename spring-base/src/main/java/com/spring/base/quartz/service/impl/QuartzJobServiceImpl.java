package com.spring.base.quartz.service.impl;

import com.spring.base.quartz.dto.JobAndTriggerDTO;
import com.spring.base.quartz.dto.JobDetailsDTO;
import com.spring.base.quartz.dto.JobInfoDTO;
import com.spring.base.quartz.dto.KeyDTO;
import com.spring.base.quartz.service.QuartzJobService;
import com.spring.base.quartz.util.JobUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author xiao-_-wu
 * @date 2021/6/3 15:18
 */
@Slf4j
@Service
public class QuartzJobServiceImpl implements QuartzJobService {

    @Resource
    private Scheduler scheduler;

    @Override
    public String stopTask(String jobDetailName) {
        JobKey jobKey = JobKey.jobKey(jobDetailName);
        try {
            if (!isJobDetail(jobKey)){
                return "定时任务不存在或已删除";
            }
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "暂停成功";
    }

    @Override
    public String restoreTask(String jobDetailName) {
        JobKey jobKey = JobKey.jobKey(jobDetailName);
        try {
            if (!isJobDetail(jobKey)){
                return "定时任务不存在或已删除";
            }
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "恢复成功";
    }

    @Override
    public String addTask(JobDetailsDTO dto) {
        try {
            // 启动调度器
            scheduler.start();

            // 获取创建的job任务
            Job job = JobUtil.getJobClass(dto.getJobClass());
            // 创建JobDetail
            JobDetail jobDetail = JobBuilder
                    .newJob(job.getClass())
                    // 给Job任务取名,分组
                    .withIdentity(dto.getJobClass(), dto.getJobGroup())
                    .build();
            jobDetail.requestsRecovery();
            jobDetail.getJobDataMap().put("jobKey", dto.getMassage());

            // 创建Trigger
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    // 给触发器设置名称和分组
                    .withIdentity(dto.getJobClass(), dto.getTriggerGroup())
                    // 设置定时策略 这里使用createSimple()方式, 也可以使用cron定时策略 createCron()
                    .withSchedule(createSimple(dto.getTime()))
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
            System.out.println("-----定时任务启动-----");

            /* 创建调度器,进行任务调度, 这里调度器交给了Spring容器进行管理 所以没有用这里的方法
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(jobDetail, trigger);
            // 这是暂停定时任务的方法
//            scheduler.shutdown();
            */
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            return "异常: 非法访问异常";
        } catch (InstantiationException e) {
            System.out.println(e.getMessage());
            return "异常: 实例化异常";
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return "异常: 类找不到异常";
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "定时任务创建成功";
    }


    @Override
    public String removeTask(String jobClassName) {
        schedulerDelete(jobClassName);
        return "删除成功";
    }

    @Override
    public String modifyTask(JobDetailsDTO dto) {
        // 先删除任务
        schedulerDelete(dto.getJobClass());
        // 创建定时任务
        addTask(dto);
        return "修改成功";
    }


    @Override
    public JobAndTriggerDTO listTask() {
        List<Trigger> triggers = new ArrayList<>();
        List<JobDetail> jobDetails = new ArrayList<>();
        try {
            // 获取trigger集合
            listTrigger(triggers);
            // 获取JobDetail集合
            listJobDetail(jobDetails);
        } catch (Exception e) {
            log.error("获取定时任务信息出错", e);
            throw new RuntimeException("获取定时任务信息异常");
        }
        JobAndTriggerDTO dto = new JobAndTriggerDTO();
        List<JobInfoDTO> jobInfos = new ArrayList<>();
        for (JobDetail jobDetail : jobDetails) {
            // 存入jobKey信息
            KeyDTO keyDTO = new KeyDTO();
            keyDTO.setKey(jobDetail.getKey().getName())
                    .setGroup(jobDetail.getKey().getGroup());
            // 存入JobDetail信息
            jobInfos.add(new JobInfoDTO()
                    .setJobDataMap(jobDetail.getJobDataMap())
                    .setJobKey(keyDTO)
                    .setClassName(jobDetail.getJobClass().getName()));
        }
        // 存入返回值
        dto.setJobInfos(jobInfos);
        return dto;
    }

    /**
     * 简单模式的Trigger
     * @return ret
     * @param time 间隔时间/秒
     */
    private SimpleScheduleBuilder createSimple(Integer time){
        return SimpleScheduleBuilder
                // 定时策略类型 普通
                .simpleSchedule()
                // 给定间隔时间 单位/秒
                .withIntervalInSeconds(time)
                .repeatForever();
    }

    /**
     * Cron 模式下的Trigger
     * ps:这里也可以使用Cron模式,使用这种模式需要了解 cron 语法
     * @return ret
     */
    private CronScheduleBuilder createCron(){
        return CronScheduleBuilder
                .cronSchedule("0 0/5 * * *？");
    }

    /**
     * 判断当前Job是否存在
     * @param jobKey jobKey
     * @return ret
     * @throws SchedulerException
     */
    private boolean isJobDetail(JobKey jobKey) throws SchedulerException {
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        return jobDetail != null;
    }

    /**
     * 删除定时任务
     *
     * @param jobClassName job名称
     */
    private void schedulerDelete(String jobClassName) {
        try {
            // 暂停Trigger
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName));
            // 从调度器中删除置顶Trigger
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName));
            // 删除Job任务
            scheduler.deleteJob(JobKey.jobKey(jobClassName));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("删除定时任务失败");
        }
    }

    /**
     * 获取触发器列表
     * @param triggers 触发器
     * @return ret
     * @throws SchedulerException 调度器程序异常
     */
    private List<Trigger> listTrigger(List<Trigger> triggers) throws SchedulerException {
        // 获取Scheduler下的所有group
        List<String> triggerGroupNames = scheduler.getTriggerGroupNames();
        // 获取所有Trigger名称
        for (String str : triggerGroupNames) {
            GroupMatcher<TriggerKey> groupMatcher = GroupMatcher.triggerGroupEquals(str);
            Set<TriggerKey> set = scheduler.getTriggerKeys(groupMatcher);
            for (TriggerKey key : set){
                triggers.add(scheduler.getTrigger(key));
            }
        }
        return triggers;
    }

    /**
     * 获取定时任务列表
     * @param jobDetails job详情
     * @return ret
     * @throws SchedulerException 调度器程序异常
     */
    private List<JobDetail> listJobDetail(List<JobDetail> jobDetails) throws SchedulerException {
        List<String> jobGroupNames = scheduler.getJobGroupNames();
        for (String str : jobGroupNames) {
            GroupMatcher<JobKey> groupMatcher = GroupMatcher.jobGroupEquals(str);
            Set<JobKey> set = scheduler.getJobKeys(groupMatcher);
            for (JobKey key : set){
                jobDetails.add(scheduler.getJobDetail(key));
            }

        }
        return jobDetails;
    }

}
