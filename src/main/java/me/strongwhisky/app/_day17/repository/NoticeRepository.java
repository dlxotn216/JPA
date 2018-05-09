package me.strongwhisky.app._day17.repository;

import me.strongwhisky.app._day17.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

/**
 * Created by taesu on 2018-05-08.
 */
public interface NoticeRepository extends JpaRepository<Notice, Long> , RevisionRepository<Notice, Long, Integer>{
}
