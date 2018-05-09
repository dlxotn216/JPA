package me.strongwhisky.app._day17.service;

import me.strongwhisky.app._day17.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;

/**
 * Created by taesu on 2018-05-09.
 */
public interface NoticeHistoryService {
    //최근 리비전 조회
    Revision<Integer, Notice> findLastChangeRevision(Long noticeKey);

    //id를 사용하여 해당 id의 모든 리비전 조회
    Revisions<Integer, Notice> findAllRevisions(Long noticeKey);

    //리비전을 페이징 처리하여 조회
    Page<Revision<Integer, Notice>> findRevisions(Long noticeKey, Pageable pageable);

    //특정 리비전 조회
    Revision<Integer, Notice> findRevision(Long noticeKey, Integer revisionNumber);
}
