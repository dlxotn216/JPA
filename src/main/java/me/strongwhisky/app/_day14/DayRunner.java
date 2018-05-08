package me.strongwhisky.app._day14;

import me.strongwhisky.app._day14.domain.Notice;
import me.strongwhisky.app._day14.repository.NoticeHistoryRepository;
import me.strongwhisky.app._day14.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Created by taesu on 2018-05-08.
 */
@Component
public class DayRunner implements ApplicationRunner {
    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeHistoryRepository noticeHistoryRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Notice notice = new Notice();
        notice.setTitle("테ㅡ트");
        notice.setContent("내용이빈당");

        Notice saved = noticeService.saveNotice(notice);
        noticeHistoryRepository.findAllById(Collections.singletonList(saved.getNoticeKey()))
                .forEach(System.out::println);
    }
}
