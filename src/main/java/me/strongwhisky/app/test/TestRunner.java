package me.strongwhisky.app.test;

import me.strongwhisky.app.test.service.FindService;
import me.strongwhisky.app.test.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by taesu on 2018-05-19.
 */
@Component
public class TestRunner implements ApplicationRunner {
    @Autowired
    private InitService initService;

    @Autowired
    private FindService findService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initService.init();
        findService.findUserWithAll();
    }
}
