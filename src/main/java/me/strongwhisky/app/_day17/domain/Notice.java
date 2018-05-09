package me.strongwhisky.app._day17.domain;

import lombok.Getter;
import lombok.Setter;
import me.strongwhisky.app.common.domain.BaseEntity;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * Created by taesu on 2018-05-08.
 */
@Entity
@Table
@Getter
@Setter
@EntityListeners(value = {NoticeListener.class})
@Audited
//@AuditTable(value = "NOTICE_HISTORY") 또는 아래처럼 suffix 정책 가능
//spring.jpa.properties.org.hibernate.envers.audit_table_suffix=_HISTORY
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NoticeKeyGenerator")
    @SequenceGenerator(name = "NoticeKeyGenerator", sequenceName = "NOTICE_SEQ")
    private Long noticeKey;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

}
