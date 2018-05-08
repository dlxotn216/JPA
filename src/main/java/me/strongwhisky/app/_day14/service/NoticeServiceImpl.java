package me.strongwhisky.app._day14.service;

import me.strongwhisky.app._day14.domain.Notice;
import me.strongwhisky.app._day14.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by taesu on 2018-05-08.
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    private NoticeRepository noticeRepository;

    @Autowired
    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public Notice saveNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    public Notice getNoticeById(Long noticeKey) {
        return noticeRepository.findById(noticeKey).orElseThrow(RuntimeException::new);
    }
}
