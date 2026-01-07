package com.tanglin.tangaicodemother;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.tanglin.tangaicodemother.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class TangAiCodeMotherApplication {

    public static void main(String[] args) {
        SpringApplication.run(TangAiCodeMotherApplication.class, args);
    }

}
