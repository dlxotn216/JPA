package me.strongwhisky.app.day23.service;

import me.strongwhisky.app.day23.domain.model.User;
import me.strongwhisky.app.day23.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by taesu on 2018-05-23.
 */
@Service
public class EntityCompareService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void compareInSamePersistenceContext(){
        User user1 = User.builder()
                .userId("abc01@test.com")
                .age(13)
                .email("abc01@naver.com")
                .name("abc01").build();

        User savedUser1 = userRepository.save(user1);

        System.out.println("Check with saved [same reference] : "+(user1 == savedUser1));   //true
        System.out.println("Check with saved [equals] : "+(user1.equals(savedUser1)));      //true

        User find = userRepository.findById(savedUser1.getUserKey()).orElseThrow(RuntimeException::new);
        System.out.println("Check with find [same reference] : "+(user1 == find));          //true
        System.out.println("Check with find [equals] : "+(user1.equals(find)));             //true
    }

    public void compareInNotSameContext(){
        User user1 = User.builder()
                .userId("abc02@test.com")
                .age(23)
                .email("abc02@naver.com")
                .name("abc02").build();

        User savedUser1 = userRepository.save(user1);

        System.out.println("Check with saved [same reference] : "+(user1 == savedUser1));   //true
        System.out.println("Check with saved [equals] : "+(user1.equals(savedUser1)));      //true

        User find = userRepository.findById(savedUser1.getUserKey()).orElseThrow(RuntimeException::new);
        System.out.println("Check with find [same reference] : "+(user1 == find));          //false
        System.out.println("Check with find [equals] : "+(user1.equals(find)));             //false
    }
}
