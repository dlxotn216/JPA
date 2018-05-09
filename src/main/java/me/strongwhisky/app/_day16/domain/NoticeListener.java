package me.strongwhisky.app._day16.domain;

import lombok.extern.slf4j.Slf4j;
import me.strongwhisky.app._day16.service.NoticeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by taesu on 2018-05-08.
 */
@Component
@Slf4j
public class NoticeListener {

    private static NoticeHistoryService noticeHistoryService;

    //Static inject 받아야 null이 아님
    @Autowired
    public void setNoticeHistoryRepository(NoticeHistoryService noticeHistoryService) {
        NoticeListener.noticeHistoryService = noticeHistoryService;
    }

    @PrePersist
    public void onPrePersist(Notice notice) {
        notice.onPrePersist();
        log.info("check onPrePersist : " + notice);
    }

    @PreUpdate
    public void onPreUpdate(Notice notice) {
        notice.onPreUpdate();
        log.info("check onPreUpdate : " + notice);
    }

    @PostPersist
    public void onPostPersist(Notice notice) {
        //audit(new NoticeHistory(notice));
        noticeHistoryService.save(new NoticeHistory(notice));
        log.info("check onPostPersist:  " + notice);
    }

    /**
     * 마지막 History와 다를 경우에만 Audit table에 삽입
     *
     * 이미 JPA에서 동일한 값을 가진 Object의 경우엔
     * 별도로 Update call을 하지 않지만
     *
     * @param notice NoticeForUpdate
     */
    @PostUpdate
    public void onPostUpdate(Notice notice) {
        NoticeHistory noticeHistory = new NoticeHistory(notice);
        if(!noticeHistory.equalsForAudit(noticeHistoryService.findTopByNoticeKey(notice.getNoticeKey()))){
            noticeHistoryService.save(noticeHistory);
            log.info("check onPostUpdate : " + notice);
        }
    }
}
