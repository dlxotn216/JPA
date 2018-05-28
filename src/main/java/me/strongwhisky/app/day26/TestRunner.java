package me.strongwhisky.app.day26;

import me.strongwhisky.app.day26.domain.model.User;
import me.strongwhisky.app.day26.domain.repository.UserRepository;
import me.strongwhisky.app.day26.service.InitService;
import me.strongwhisky.app.day26.service.LazyWriteTestService;
import me.strongwhisky.app.day26.service.TestService;
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
    private UserRepository userRepository;

    @Autowired
    private LazyWriteTestService lazyWriteTestService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initService.init();

        testService.readByDefault();print();
        testService.readByScalarType();print();
        testService.readByReadOnlyQueryHints();print();
        testService.readByReadOnlyTransaction();print();
        testService.readByNotSupportransaction();print();
        testService.readByReadOnlyTransactionAndRepository();print();
        testService.readByNotSupportedTransactionAndRepository();print();

        System.out.println("\n\n");

        lazyWriteTestService.writeByEntityManager();
        lazyWriteTestService.writeByRepository();
        lazyWriteTestService.writeUserAndGroupByEntityManger();
        lazyWriteTestService.writeUserAndGroupByRepository();
        lazyWriteTestService.writeUserAndGroupBySaveAll();
    }

    private void print(){
        System.out.println("----------------------End of transaction print----------------------");
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println(user.getUserId() + "::" + user.getName());
        System.out.println("\n");
    }
}
