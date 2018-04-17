package me.strongwhisky.day02.service;

import lombok.extern.slf4j.Slf4j;
import me.strongwhisky.day02.model.DirectAllocateIdentyUser;
import me.strongwhisky.day02.model.GeneratedValueUser;
import me.strongwhisky.day02.model.UseSequenceUser;
import me.strongwhisky.day02.repository.DirectAllocateIdentyUserRepository;
import me.strongwhisky.day02.repository.GeneratedValueUserRepository;
import me.strongwhisky.day02.repository.UseSequenceUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by taesu on 2018-04-17.
 */
@Service
@Slf4j
public class PrimaryKeyMappingStrategyTest {

    private DirectAllocateIdentyUserRepository directAllocateIdentyUserRepository;
    private GeneratedValueUserRepository generatedValueUserRepository;
    private UseSequenceUserRepository useSequenceUserRepository;

    @Autowired
    public PrimaryKeyMappingStrategyTest(DirectAllocateIdentyUserRepository directAllocateIdentyUserRepository, GeneratedValueUserRepository generatedValueUserRepository, UseSequenceUserRepository useSequenceUserRepository) {
        this.directAllocateIdentyUserRepository = directAllocateIdentyUserRepository;
        this.generatedValueUserRepository = generatedValueUserRepository;
        this.useSequenceUserRepository = useSequenceUserRepository;
    }

    public void run() {
        DirectAllocateIdentyUser directAllocateIdentyUser = new DirectAllocateIdentyUser();
        directAllocateIdentyUser.setId("Unique@UserId.com");
        directAllocateIdentyUser.setName("My Name");

        directAllocateIdentyUserRepository.save(directAllocateIdentyUser);
        directAllocateIdentyUserRepository.findById(directAllocateIdentyUser.getId())
                                          .ifPresent(item -> log.info("find direct allocated user ->" + item.getName() + ":" + item.getId()));

        GeneratedValueUser generatedValueUser = new GeneratedValueUser();
        generatedValueUser.setUserId("dlxotn1234@namver.com");
        generatedValueUser.setUserName("Lee Tae Su");

        generatedValueUserRepository.save(generatedValueUser);
        generatedValueUserRepository.findById(1L)
                                    .ifPresent((item -> log.info("find generated value user ->" + item.getUserKey() + ":" + item.getUserId() + ":" + item.getUserName())));

        UseSequenceUser useSequenceUser = new UseSequenceUser();
        useSequenceUser.setUserId("taesu@crscube.co.kr");
        useSequenceUser.setUserName("Lee Tae Su FUCK");

        useSequenceUserRepository.save(useSequenceUser);
        useSequenceUserRepository.findById(1L)
                                 .ifPresent((item -> log.info("find use sequence user ->" + item.getUserKey() + ":" + item.getUserId() + ":" + item.getUserName())));


    }
}
