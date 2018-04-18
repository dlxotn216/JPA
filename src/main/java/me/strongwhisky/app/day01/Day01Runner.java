package me.strongwhisky.app.day01;

import me.strongwhisky.app.day01.service.impl.DefaultDataPopulator;
import me.strongwhisky.app.day01.service.impl.TransactionalWriteBehindTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by taesu on 2018-04-17.
 */
@Component
public class Day01Runner {

    @Autowired
    private DefaultDataPopulator defaultDataPopulator;

    @Autowired
    private TransactionalWriteBehindTest transactionalWriteBehindTest;

    public void runDayApplication(){
        defaultDataPopulator.run();

        transactionalWriteBehindTest.run();
    }
}
