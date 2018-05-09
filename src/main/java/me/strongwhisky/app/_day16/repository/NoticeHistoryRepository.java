package me.strongwhisky.app._day16.repository;

import me.strongwhisky.app._day16.domain.NoticeHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taesu on 2018-05-08.
 */
public interface NoticeHistoryRepository extends JpaRepository<NoticeHistory, Long> {

    Page<NoticeHistory> findAllByNoticeKey(Long noticeKey, Pageable pageable);

    NoticeHistory findTopByNoticeKey(Long noticeKey);
}
