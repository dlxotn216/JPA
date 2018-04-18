package me.strongwhisky.app.day03;

import lombok.extern.slf4j.Slf4j;
import me.strongwhisky.app.day03.model.Notice;
import me.strongwhisky.app.day03.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.TimeZone;

/**
 * Created by taesu on 2018-04-18.
 */
@Component
@Slf4j
public class Day03Runner {
    @Autowired
    private NoticeRepository noticeRepository;

    public void runDayApplication() {
        // 아래 설정을 해주어야 DB에도 UTC로 들어감
        //그렇지 않으면 생성은 UTC로 되나 DB에는 현재 TimeZone의 값으로 들어가고 로그도 현재 타임존으로 찍힘
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        Notice notice = new Notice();
        notice.setTitle("test");
        notice.setCreatedAt(ZonedDateTime.now(ZoneOffset.UTC));     //UTC로 시간 생성 됨

        noticeRepository.save(notice);
        noticeRepository.findAll().forEach(item -> {
            log.info(item.getTitle() + " :: " + item.getCreatedAt());   //test :: 2018-04-18T15:32:49.700Z[UTC]
        });
    }
}
