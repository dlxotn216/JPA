package me.strongwhisky.app.day05;

import lombok.extern.slf4j.Slf4j;
import me.strongwhisky.app.day05.service.PrintService;
import me.strongwhisky.app.day05.service.Test1Service;
import me.strongwhisky.app.day05.service.Test2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by taesu on 2018-04-21.
 */
@Component
@Slf4j
public class Day0Runner implements ApplicationRunner {

    @Autowired
    private PrintService printService;

    @Autowired
    private Test1Service test1Service;

    @Autowired
    private Test2Service test2Service;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        test1Service.addTaemAndSetToMemberAndAddMember();

        test2Service.addMemberAndSetToTeamAndAddTeam();
        test2Service.advancedTest();
        printService.printAll();
    }


}
