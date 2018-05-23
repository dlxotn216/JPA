package me.strongwhisky.app.day23;

import me.strongwhisky.app.day23.service.EntityCompareService;
import me.strongwhisky.app.day23.service.EntityProxyCompareService;
import me.strongwhisky.app.day23.service.HierarchyProxyTest;
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
    private EntityCompareService entityCompareService;

    @Autowired
    private EntityProxyCompareService entityProxyCompareService;

    @Autowired
    private HierarchyProxyTest hierarchyProxyTest;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        entityCompareService.compareInSamePersistenceContext();
        entityCompareService.compareInNotSameContext();

        entityProxyCompareService.getRefAndFind();
        entityProxyCompareService.findAndGetRef();
        entityProxyCompareService.compareWithProxy();

        hierarchyProxyTest.saveManAndFindAsPerson();
        hierarchyProxyTest.saveManAndFindAsPersonByEntityManager();
    }
}
