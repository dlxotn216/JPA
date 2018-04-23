package me.strongwhisky.app.day06;

import lombok.extern.slf4j.Slf4j;
import me.strongwhisky.app.day06.manytoone.bidirectional.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by taesu on 2018-04-21.
 */
@Component
@Slf4j
public class Runner implements ApplicationRunner {

    @Autowired
    private Test test;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        test.test();
    }


}
