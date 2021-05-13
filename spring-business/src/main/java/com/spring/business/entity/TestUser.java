package com.spring.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表(TestUser)实体类
 *
 * @author xiao-_-wu
 * @since 2021-04-08 17:05:42
 */
@Data
@Builder
@TableName("test_user")
public class TestUser implements Serializable {
    private static final long serialVersionUID = -76953908500471675L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 账户
     */
    private String account;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码
     */
    private String password;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 账户状态 0正常 1冻结
     */
    private Integer accountStatus;


}

