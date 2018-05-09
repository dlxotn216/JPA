package me.strongwhisky.app._day16.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;

/**
 * Created by taesu on 2018-05-08.
 */
@Entity
@Table(name = "NOTICE_HISTORY")
@Getter
@Setter
public class NoticeHistory extends AbstractNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NoticeHistorySeqGenerator")
    @SequenceGenerator(name = "NoticeHistorySeqGenerator", sequenceName = "NOTICE_HIS_SEQ")
    private Long noticeHistoryKey;

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

    public boolean equalsForAudit(NoticeHistory noticeHistory){
        if(!ObjectUtils.nullSafeEquals(super.getTitle(), noticeHistory.getTitle())){
            return false;
        }

        if(!ObjectUtils.nullSafeEquals(super.getContent(), noticeHistory.getContent())){
            return false;
        }

        return true;
    }
}
