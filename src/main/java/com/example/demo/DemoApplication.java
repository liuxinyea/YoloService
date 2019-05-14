package com.example.demo;

import com.example.demo.webSocket.MyWebSocket;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@MapperScan("com.example.demo.dao")
@EnableTransactionManagement    //开启事务管理
public class DemoApplication extends SpringBootServletInitializer {
    public static void main(String[] args) throws UnknownHostException {
        MyWebSocket webSocket=new MyWebSocket(8888);
        webSocket.start();
        SpringApplication.run(DemoApplication.class, args);
    }
    @Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}

