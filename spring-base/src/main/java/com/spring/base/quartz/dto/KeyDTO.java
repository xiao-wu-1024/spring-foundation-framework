package com.spring.base.quartz.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用来获取key 和 group
 * @author xiao-_-wu
 * @date 2021/3/26 10:38
 */
@Data
@Accessors(chain = true)
public class KeyDTO {

    /**
     * key值
     */
    private String key;
    /**
     * 分组
     */
    private String group;
}
