package me.strongwhisky.app.day22;

import me.strongwhisky.app.day22.service.FindService;
import me.strongwhisky.app.day22.service.InitService;
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
        findService.EntityGraphByAttributePathTest();
        findService.userSpecTest();
        findService.fetchAllgraphByQueryDslTest();
    }
}
