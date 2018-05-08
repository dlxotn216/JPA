package me.strongwhisky.app._day14.service;

import me.strongwhisky.app._day14.domain.Notice;
import me.strongwhisky.app._day14.repository.NoticeHistoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by taesu on 2018-05-08.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeServiceTest {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeHistoryRepository noticeHistoryRepository;

    @Test
    public void 생성일테스트() {
        //Given
        Notice notice = new Notice();
        notice.setTitle("테ㅡ트");
        notice.setContent("내용이빈당");

        //When
        Notice saved = noticeService.saveNotice(notice);

        //Then
        assertThat(saved.getCreatedAt()).isNotNull();

        noticeHistoryRepository.findAllById(Collections.singletonList(saved.getNoticeKey()))
                .forEach(System.out::println);
    }
}