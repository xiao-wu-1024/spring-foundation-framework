package com.spring.admin.quartz.controller;

import com.spring.base.quartz.dto.JobAndTriggerDTO;
import com.spring.base.quartz.dto.JobDetailsDTO;
import com.spring.base.quartz.service.QuartzJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xiao-_-wu
 * @date 2021/6/3 15:23
 */
@Slf4j
@Api(tags = "定时任务相关")
@RestController
@RequestMapping("/quartz/jop")
public class QuartzJopController {

    @Resource
    private QuartzJobService quartzJobService;

    @ApiOperation(value = "停止定时任务")
    @GetMapping("/stop/{jobDetailName}")
    public String stopTask(@PathVariable String jobDetailName){
        log.info("Controller-----停止定时任务");
        return quartzJobService.stopTask(jobDetailName);
    }

    @ApiOperation(value = "恢复定时任务")
    @GetMapping("/restore/{jobDetailName}")
    public String restoreTask(@PathVariable String jobDetailName){
        log.info("Controller-----恢复定时任务");
        return quartzJobService.restoreTask(jobDetailName);
    }

    @ApiOperation(value = "新增定时任务")
    @PostMapping("/add")
    public String addTask(JobDetailsDTO dto){
        log.info("Controller-----新增定时任务");
        return quartzJobService.addTask(dto);
    }

    @ApiOperation(value = "删除定时任务")
    @DeleteMapping("/remove/{jobDetailName}")
    public String removeTask(@PathVariable String jobDetailName){
        log.info("Controller-----删除定时任务");
        return quartzJobService.removeTask(jobDetailName);
    }

    @ApiOperation(value = "修改定时任务")
    @PutMapping("/modify")
    public String modifyTask(JobDetailsDTO dto){
        log.info("Controller-----修改定时任务");
        return quartzJobService.modifyTask(dto);
    }

    @ApiOperation(value = "获取任务列表")
    @GetMapping("/list")
    public JobAndTriggerDTO listTask(){
        log.info("Controller-----获取任务列表");
        return quartzJobService.listTask();
    }

}
