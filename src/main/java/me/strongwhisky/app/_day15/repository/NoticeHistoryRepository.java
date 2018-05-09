package me.strongwhisky.app._day15.repository;

import me.strongwhisky.app._day15.domain.NoticeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taesu on 2018-05-08.
 */
public interface NoticeHistoryRepository extends JpaRepository<NoticeHistory, Long> {
}
