package cn.coget.dora.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 邮件服务器
 *
 * author: sin
 * time: 2020/3/21 4:24 下午
 */
@SpringBootApplication
@EnableDiscoveryClient

public class MailApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class, args);
    }
}
