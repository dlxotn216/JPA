package me.strongwhisky.app.querydsl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by taesu on 2018-05-01.
 */
@Component
public class Runner implements ApplicationRunner {

    @Autowired
    private InitService initService;

    @Autowired
    private BasicDsl basicDsl;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initService.init();
        basicDsl.run();
    }
}
