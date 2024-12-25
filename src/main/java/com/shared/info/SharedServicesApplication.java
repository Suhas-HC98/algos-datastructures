package com.shared.info;

import com.shared.info.config.SharedServicesApplicationContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@Slf4j
@AllArgsConstructor
@SpringBootApplication
@EnableFeignClients
public class SharedServicesApplication implements CommandLineRunner {

    private final ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(SharedServicesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SharedServicesApplicationContext sharedAlgoApplicationConfig = applicationContext
                .getBean(SharedServicesApplicationContext.class);
        log.info("{} is now ready", sharedAlgoApplicationConfig.getApplicationId());
    }
}
