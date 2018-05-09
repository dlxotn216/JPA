package me.strongwhisky.app._day15.service;

import me.strongwhisky.app._day15.domain.Notice;
import me.strongwhisky.app._day15.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by taesu on 2018-05-08.
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    private NoticeRepository noticeRepository;

    @Autowired
    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Override
	@Transactional
    public void testNotice(){
		Notice notice = new Notice();
        notice.setTitle("테ㅡ트");
        notice.setContent("내용이빈당");
        
        Notice notice1 = new Notice();
		notice1.setTitle("테111111ㅡ트11111");
		notice1.setContent("11111111111111내용이빈당1111111111111");
	
		Notice notice2 = new Notice();
		notice2.setTitle("테ㅡ트222");
		notice2.setContent("내용이빈2131313132123당");
		
		noticeRepository.save(notice);
		noticeRepository.save(notice2);
		noticeRepository.save(notice1);
//		if(noticeRepository.save(notice1) != null){
//			throw new RuntimeException("예외 발생");
//		}
	}
    
    @Transactional
    public Notice saveNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    public Notice getNoticeById(Long noticeKey) {
        return noticeRepository.findById(noticeKey).orElseThrow(RuntimeException::new);
    }
}
