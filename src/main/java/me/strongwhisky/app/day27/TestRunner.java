package me.strongwhisky.app.day27;

import me.strongwhisky.app.day27.domain.model.User;
import me.strongwhisky.app.day27.domain.repository.UserRepository;
import me.strongwhisky.app.day27.service.InitService;
import me.strongwhisky.app.day27.service.TestService;
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
    private TestService testService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        initService.init();

        testService.testForNotSaveCall_WhenNotChangeGroup();
        testService.testForNotSaveCall_WhenChangeGroup();

        testService.changeUsersTest();
        testService.clearAndChangeUsersTest();
        testService.changeBulk();
    }
}
