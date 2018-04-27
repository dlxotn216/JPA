package me.strongwhisky.app.cascate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class CascadeTestRunner implements ApplicationRunner {

    @Autowired
    TestService testService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        testService.addUserAndUserRoles();
        testService.addRoleAndUserUsers();
        testService.addUsers();
    }
}
