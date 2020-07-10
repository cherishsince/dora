package cn.coget.dora.actuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 执行器
 *
 * author: sin
 * time: 2020/5/2 11:26 上午
 */
@Configuration
@SpringBootApplication
@EnableScheduling
@EnableAsync(
        proxyTargetClass = true
)
public class ActuatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActuatorApplication.class, args);
    }
}
