package com.lss.immutablewrongdemo;

import com.lss.immutablewrongdemo.config.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Properties.class)
public class ImmutableWrongDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImmutableWrongDemoApplication.class, args);
    }

}
