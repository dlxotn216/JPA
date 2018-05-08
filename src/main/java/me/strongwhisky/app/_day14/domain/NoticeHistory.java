package me.strongwhisky.app._day14.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by taesu on 2018-05-08.
 */
@Entity
@Table(name = "NOTICE_HISTORY")
@Getter
@Setter
public class NoticeHistory extends AbstractNotice {

    @Id
    private Long noticeKey;

    private NoticeHistory() {

    }

    public NoticeHistory(Notice notice) {
        this.noticeKey = notice.getNoticeKey();
        super.setTitle(notice.getTitle());
        super.setContent(notice.getContent());
        super.setCreatedAt(notice.getCreatedAt());
        super.setUpdatedAt(notice.getUpdatedAt());
    }

    @Override
    public String toString() {
        return "NoticeHistory{" +
                "noticeKey=" + noticeKey +
                "} " + super.toString();
    }
}
