package me.strongwhisky.app._day19;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by taesu on 2018-05-08.
 */
@Component
public class DayRunner implements ApplicationRunner {

    @Autowired
    private TestService testService;

    public void run(ApplicationArguments args) throws Exception {
       testService.run();
    }
}
