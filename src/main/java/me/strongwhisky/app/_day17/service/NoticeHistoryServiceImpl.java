package me.strongwhisky.app._day17.service;

import me.strongwhisky.app._day17.domain.Notice;
import me.strongwhisky.app._day17.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;

/**
 * Created by taesu on 2018-05-09.
 */
@Service
public class NoticeHistoryServiceImpl implements NoticeHistoryService {
    @Autowired
    private NoticeRepository noticeRepository;

    //최근 리비전 조회
    @Override
    public Revision<Integer, Notice> findLastChangeRevision(Long noticeKey) {
        return noticeRepository.findLastChangeRevision(noticeKey).orElseGet(null);
    }

    //id를 사용하여 해당 id의 모든 리비전 조회
    @Override
    public Revisions<Integer, Notice> findAllRevisions(Long noticeKey) {
        return noticeRepository.findRevisions(noticeKey);
    }

    //리비전을 페이징 처리하여 조회
    @Override
    public Page<Revision<Integer, Notice>> findRevisions(Long noticeKey, Pageable pageable) {
        return noticeRepository.findRevisions(noticeKey, pageable);
    }

    //특정 리비전 조회
    @Override
    public Revision<Integer, Notice> findRevision(Long noticeKey, Integer revisionNumber) {
        return noticeRepository.findRevision(noticeKey, revisionNumber).orElseGet(null);
    }

}
