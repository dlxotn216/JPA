package me.strongwhisky.app._day20;

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
    private ByUsingEntityMangerTest byUsingEntityMangerTest;

    @Autowired
    private ByUsingJPQLTest byUsingJPQLTest;

    public void run(ApplicationArguments args) throws Exception {
       byUsingEntityMangerTest.run();
       byUsingEntityMangerTest.pritnByUsingEntityGraph();

       byUsingJPQLTest.pritnByUsingEntityGraph();
    }
}
