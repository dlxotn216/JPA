package me.strongwhisky.app._day14.service;

import me.strongwhisky.app._day14.domain.NoticeHistory;
import me.strongwhisky.app._day14.repository.NoticeHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by taesu on 2018-05-08.
 */
@Service
public class NoticeHistoryServiceImpl implements NoticeHistoryService{
    @Autowired
    private NoticeHistoryRepository noticeHistoryRepository;

    @Override
    public void save(NoticeHistory noticeHistory) {
        noticeHistoryRepository.save(noticeHistory);
    }
}
