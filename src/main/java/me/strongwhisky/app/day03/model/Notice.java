package me.strongwhisky.app.day03.model;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * Created by taesu on 2018-04-18.
 */
@Entity @Data
@Table(name = "DAY_03_NOTICE")
public class Notice {

    @Id
    @Column(name = "NOTICE_KEY")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_KEY_GENERATOR")
    @SequenceGenerator(name="USER_KEY_GENERATOR", sequenceName = "SEQ_USER")
    private Long noticeKey;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CREATED_AT")
    private ZonedDateTime createdAt;
}
