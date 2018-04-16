package me.strongwhisky;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by taesu on 2018-04-15.
 */
@Component
@Slf4j
public class DefaultDataPopulator implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

        log.info("find all method return null? " + (userRepository.findAll() == null));

        User user = new User();
        user.setName("Lee Tae Su");
        user.setEmail("taesu@crcube.co.kr");
        user.setJoinedAt(new Date());
        userRepository.save(user);


        User u1 = new User();
        u1.setName("Lee Tae Su1");
        u1.setEmail("test 01 taesu@crcube.co.kr");
        u1.setJoinedAt(new Date());

        User u2 = new User();
        u2.setName("Lee Tae Su2");
        u2.setEmail("test 02 taesu@crcube.co.kr");
        u2.setJoinedAt(new Date());

        User u3 = new User();
        u3.setName("Lee Tae Su3");
        u3.setEmail("test03 taesu@crcube.co.kr");
        u3.setJoinedAt(new Date());

        userRepository.saveAll(Arrays.asList(u1, u2, u3));

        userRepository.findAll().forEach(item -> {
            log.info("[" + item.getId() + "] => " + item.getName() + "," + item.getEmail());
        });
    }
}
