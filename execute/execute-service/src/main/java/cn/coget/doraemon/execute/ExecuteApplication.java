//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.coget.doraemon.execute;

import cn.coget.doraemon.execute.generator.GitHubShellGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@SpringBootApplication
@EnableScheduling
@EnableAsync(
        proxyTargetClass = true
)
public class ExecuteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExecuteApplication.class, args);
    }

    @Bean
    public GitHubShellGenerator gitHubShellGenerator() {
        return new GitHubShellGenerator();
    }
}
