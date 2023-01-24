package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.auto17.mapper")
@SpringBootApplication
public class AppApi {

    public static void main(String[] args) {
        SpringApplication.run(AppApi.class, args);
        System.out.println("***********************************************\n");
        System.out.println("*** React&SpringBoot API Start Successfully ***\n");
        System.out.println("***********************************************\n");
    }

}
