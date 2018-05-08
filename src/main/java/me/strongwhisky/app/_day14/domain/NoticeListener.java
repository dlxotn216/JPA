package me.strongwhisky.app._day14.domain;

import lombok.extern.slf4j.Slf4j;
import me.strongwhisky.app._day14.repository.NoticeHistoryRepository;
import me.strongwhisky.app._day14.service.NoticeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.ZonedDateTime;

/**
 * Created by taesu on 2018-05-08.
 */
@Component
@Slf4j
public class NoticeListener {

    private static NoticeHistoryService noticeHistoryService;

    //Static inject 받아야 null이 아님
    @Autowired
    public void setNoticeHistoryRepository(NoticeHistoryService noticeHistoryService){
        NoticeListener.noticeHistoryService = noticeHistoryService;
    }

    @PrePersist
    public void onPrePersist(Object object) {
        if(object instanceof NoticeHistory){
            //NoticeHistory의 save 시에도 발동 함.
            return;
        }

        if (object instanceof Notice) {
            Notice notice = (Notice) object;
            notice.setCreatedAt(ZonedDateTime.now());
        }
        log.info("check onPrePersist : " + object);
    }

    @PreUpdate
    public void onPreUpdate(Object object) {
        log.info("check onPreUpdate : " + object);
    }

    //https://stackoverflow.com/questions/4895854/jpa-postpersist-postupdate-transaction
    @PostPersist
    public void onPostPersist(Object object) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            //onPostPersist이므로 before commit 콜백 없음
            @Override
            public void beforeCommit(boolean readonly) {
                if (object instanceof Notice) {
                    NoticeHistory noticeHistory = new NoticeHistory((Notice) object);
                    noticeHistory.setReason("initial input");
                    noticeHistoryService.save(noticeHistory);
                }

                log.info("check onPostPersist afterCompletion  beforeCommit:  " + object);
            }

            @Override
            public void afterCommit() {
                log.info("check onPostPersist afterCommit : " + object);
            }

            @Override
            public void afterCompletion(int status) {
                if (object instanceof Notice) {
                    NoticeHistory noticeHistory = new NoticeHistory((Notice) object);
                    noticeHistory.setReason("initial input");
                    noticeHistoryService.save(noticeHistory);       //영속화만 되고 실제 저장은 되지 않는다.
                }
                log.info("check onPostPersist afterCompletion : " + object);
            }

        });
    }

    @PostUpdate
    public void onPostUpdate(Object object) {
        if (object instanceof Notice) {
            NoticeHistory noticeHistory = new NoticeHistory((Notice) object);
            noticeHistoryService.save(noticeHistory);
        }
        log.info("check onPostUpdate : " + object);
    }
}
