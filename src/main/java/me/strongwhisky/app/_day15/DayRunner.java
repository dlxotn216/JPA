package me.strongwhisky.app._day15;

import me.strongwhisky.app._day15.repository.NoticeHistoryRepository;
import me.strongwhisky.app._day15.service.NoticeService;
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

    @Autowired
    private NoticeHistoryRepository noticeHistoryRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
    	try {
			noticeService.testNotice();
		}catch (Exception e){
    		
		}
//        Notice notice = new Notice();
//        notice.setTitle("테ㅡ트");
//        notice.setContent("내용이빈당");
//
//        noticeService.saveNotice(notice);
    }
}
