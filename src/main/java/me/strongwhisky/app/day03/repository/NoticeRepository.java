package me.strongwhisky.app.day03.repository;

import me.strongwhisky.app.day03.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taesu on 2018-04-19.
 */
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
