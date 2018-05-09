package me.strongwhisky.app._day16.service;

import me.strongwhisky.app._day16.domain.NoticeHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by taesu on 2018-05-08.
 */
public interface NoticeHistoryService {

    void save(NoticeHistory noticeHistory);

    Page<NoticeHistory> findAllById(Long noticeKey, Pageable pageable);

    NoticeHistory findTopByNoticeKey(Long noticeKey);
}
