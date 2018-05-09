package me.strongwhisky.app._day15.service;

import me.strongwhisky.app._day15.domain.NoticeHistory;
import me.strongwhisky.app._day15.repository.NoticeHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by taesu on 2018-05-08.
 */
@Service
public class NoticeHistoryServiceImpl implements NoticeHistoryService{
    @Autowired
    private NoticeHistoryRepository noticeHistoryRepository;

    
    //@Transactional(propagation = Propagation.NESTED)			//부모의 영향을 받고 자식은 부모에게 영향을 미치지 않는다 단, jdbc 3.0 스펙의 저장포인트 지원 드라이버와 DataSourceTransactionManager를 이용해야 함 
    @Transactional(propagation = Propagation.REQUIRES_NEW)		//부모의 영향 받고 자식이 부모에게도 영향을 미친다 (Audit은 중요한 데이터이고 실패시 부모도 실패애햐 하는 것이 맞는 듯)
    @Override
    public void save(NoticeHistory noticeHistory) {
        noticeHistoryRepository.save(noticeHistory);
    }
}
