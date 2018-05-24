package me.strongwhisky.app.day24;

import me.strongwhisky.app.day24.service.InitService;
import me.strongwhisky.app.day24.service.SolutionService;
import me.strongwhisky.app.day24.service.TestService;
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

    @Autowired
    private SolutionService solutionService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initService.init();

        testService.testFindOneUser();
        testService.testFindOneUserByEntityManager();

        testService.testFindAllUsers();
        testService.testFindAllGroups();
        testService.testFindAllGroupsByEntityManager();


        solutionService.usingFetchJoin();
    }
}
