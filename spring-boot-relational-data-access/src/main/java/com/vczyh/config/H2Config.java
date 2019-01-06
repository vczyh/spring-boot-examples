package com.vczyh.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2Config {

    /**
     * 暴露接口，不然访问不到
     * 访问  ttp://localhost:8080/h2-console
     * jdbc:url改为jdbc:h2:mem:testdb 连接即可
     *
     * 配置h2数据库映射：https://segmentfault.com/a/1190000015331557
     */
    @Bean
    public ServletRegistrationBean h2ServletRegistration() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/h2-console/*");
        return registrationBean;
    }
}
