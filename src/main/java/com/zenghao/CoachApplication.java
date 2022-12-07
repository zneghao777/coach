package com.zenghao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zenghao.mapper")
public class CoachApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoachApplication.class, args);
    }

}
