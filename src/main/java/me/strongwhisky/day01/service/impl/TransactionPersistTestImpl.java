package me.strongwhisky.day01.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.strongwhisky.day01.model.User;
import me.strongwhisky.day01.repository.UserRepository;
import me.strongwhisky.day01.service.TransactionPersistTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by taesu on 2018-04-16.
 */
@Service
@Slf4j
public class TransactionPersistTestImpl implements TransactionPersistTest {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public void test() {
        User user = new User();
        user.setName("Lee Tae Su for transaction persist test");
        user.setEmail("persis@transaction.com");
        user.setJoinedAt(new Date());
        userRepository.save(user);          //ID will be 7

        //영속 에티티의 동일성 보장 됨
        //기본적으로 Spring container의 기본 영속성 컨텍스트 전략은 트랜잭션 범위의 영속성 컨텍스트 전략을 사용
        //트랜잭션의 범위와 영속성 컨텍스트의 생존 범위가 같다 == 같은 트랜잭션이면 같은 영속성을 쓴다
        userRepository.findById(7L).ifPresent((userFromResponse) -> {
            log.info("is equals in @Transactional ? " + (user == userFromResponse));
        });
    }
}
