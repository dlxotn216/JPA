package me.strongwhisky.app._day17;

import me.strongwhisky.app._day17.domain.Notice;
import me.strongwhisky.app._day17.service.NoticeHistoryService;
import me.strongwhisky.app._day17.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by taesu on 2018-05-08.
 */
@Component
public class DayRunner implements ApplicationRunner {

    @Autowired
    private NoticeService noticeService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //Given
        Notice notice = new Notice();
        notice.setTitle("테ㅡ트");
        notice.setContent("내용이빈당");

        //When
        noticeService.saveNotice(notice);


        //Given
        Notice notice1 = new Notice();
        notice1.setTitle("테ㅡ트11111111111111");
        notice1.setContent("내용이빈당11111111111");
        noticeService.saveNotice(notice1);

        //When
        Notice savedForUpdate = noticeService.getNoticeById(notice.getNoticeKey());
        savedForUpdate.setTitle("테스트 111111111111변경");
        savedForUpdate.setContent("111111111변경 된 공지 내용");
        noticeService.saveNotice(savedForUpdate);

        //Given
        Notice notice2 = new Notice();
        notice2.setTitle("테ㅡ트22222222222222222");
        notice2.setContent("내용이빈당222222222222222");
        Notice saved = noticeService.saveNotice(notice2);

        //When
        Notice savedForUpdate1 = noticeService.getNoticeById(saved.getNoticeKey());
        savedForUpdate1.setTitle("테스트 변경22222222222222222-11111");
        savedForUpdate1.setContent("변경 된 공지 내용222222222222222-11111");

        //JPA 자체에서 save 전 select를 통해 동일 객체인지 확인
        //만약 존재하지 않거나 다르면 Update 처리 -> @PostUpdate callback 처리
        noticeService.saveNotice(savedForUpdate1);
        noticeService.saveNotice(savedForUpdate1);
        noticeService.saveNotice(savedForUpdate1);
        noticeService.saveNotice(savedForUpdate1);
    }
}
