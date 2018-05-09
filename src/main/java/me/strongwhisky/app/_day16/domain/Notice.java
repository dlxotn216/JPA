package me.strongwhisky.app._day16.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by taesu on 2018-05-08.
 */
@Entity
@Table
@Getter
@Setter
@EntityListeners(value = {NoticeListener.class})
public class Notice extends AbstractNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NoticeKeyGenerator")
    @SequenceGenerator(name = "NoticeKeyGenerator", sequenceName = "NOTICE_SEQ")
    private Long noticeKey;

}
