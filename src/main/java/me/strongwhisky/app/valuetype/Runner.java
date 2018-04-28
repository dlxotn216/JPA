package me.strongwhisky.app.valuetype;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component(value = "MYRUNNER")
@Slf4j
public class Runner implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = new User();
        user.setUserId("test@test.com");
        user.setUserName("taesu");
        user.setHomeAddress(new Address("city", "strr1", "zip-code"));

        Address workAddress = Address.builder().city("workCity").street("strr1").zipcode("123123").build();
        user.setWorkAddress(workAddress);

        //값 타입은 영속성 전이와 고아 객체 제거가 자동으로 있다고 볼 수 있다
        userRepository.save(user);

        userRepository.findById(user.getUserId()).ifPresent(find->{
            System.out.println(find.getUserId());
            System.out.println(find.getHomeAddress().getCity());
            System.out.println(find.getHomeAddress().getStreet());
            System.out.println(find.getHomeAddress().getZipcode());

            System.out.println(find.getWorkAddress().getCity());
            System.out.println(find.getWorkAddress().getStreet());
            System.out.println(find.getWorkAddress().getZipcode());
        });


    }


}
