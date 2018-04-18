package me.strongwhisky.app.day02;

import me.strongwhisky.app.day02.service.PrimaryKeyMappingStrategyTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by taesu on 2018-04-17.
 */
@Component
public class Day02Runner {

    @Autowired
    private PrimaryKeyMappingStrategyTest primaryKeyMappingStrategyTest;

    public void runDayApplication() {
        primaryKeyMappingStrategyTest.run();
    }
}
