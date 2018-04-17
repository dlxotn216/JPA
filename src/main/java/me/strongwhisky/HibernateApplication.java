package me.strongwhisky;

import me.strongwhisky.day01.Day01Runner;
import me.strongwhisky.day02.Day02Runner;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HibernateApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateApplication.class, args);
    }

//    @Bean
//    public ApplicationRunner day01ApplicationRunner(Day01Runner day01Runner) {
//        return (args) -> day01Runner.runDayApplication();
//    }

    @Bean
    public ApplicationRunner day02ApplicationRunner(Day02Runner day02Runner) {
        return (args) -> day02Runner.runDayApplication();
    }
}
