package com.ajie.vd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author:hyj
 * @date:2022/10/11
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.ajie")
public class ServiceVdApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceVdApplication.class, args);
    }
}
