package com.spring.portal;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xiao-_-wu
 * @date 2021/5/12 17:35
 */
@Slf4j
@ComponentScan(basePackages={"com.spring.*"})
@MapperScan("com.spring.business.mapper")
@SpringBootApplication
public class PortalApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalApiApplication.class, args);
    }

}
