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

    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void save(NoticeHistory noticeHistory) {
        noticeHistoryRepository.save(noticeHistory);
    }
}
