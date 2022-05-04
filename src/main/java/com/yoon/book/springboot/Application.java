package com.yoon.book.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing
@SpringBootApplication // 해당 위치부터 설정 읽어간다
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}



