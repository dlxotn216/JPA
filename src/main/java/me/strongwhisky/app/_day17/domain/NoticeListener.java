package me.strongwhisky.app._day17.domain;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.ZonedDateTime;

/**
 * Created by taesu on 2018-05-09.
 */
public class NoticeListener {

    @PrePersist
    @PreUpdate
    public void onPreSaved(Notice notice) {
        ZonedDateTime current = ZonedDateTime.now();

        if (notice.getCreatedAt() == null) {
            notice.setCreatedAt(current);
        }

        if (notice.getUpdatedAt() == null) {
            notice.setUpdatedAt(current);
        }
    }
}
