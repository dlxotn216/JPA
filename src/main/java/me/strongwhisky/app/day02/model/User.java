package me.strongwhisky.app.day02.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by taesu on 2018-04-17.
 */
@Entity
@Data
@Table(name = "MEMBER",
        uniqueConstraints = {
                @UniqueConstraint(name = "NAME_AGE_UNIQUE", columnNames = {"USER_NAME", "AGE"})
        })
public class User {

    /**
     * Generated value에서 strategy는 기본 AUTO임 DBMS 벤더가 지언하는 기본값을 따름
     * ex) Oracle -> SEQUENCE, MySQL -> IDENTITY
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GENERATOR")
    @SequenceGenerator(name = "USER_SEQ_GENERATOR", sequenceName = "USER_SEQ", initialValue = 1, allocationSize = 1)
    private Long userKey;

    @Column(name = "ID")
    private String id;

    @Column(name = "USER_NAME", nullable = false, length = 32)
    private String userName;

    //AGE로 생성 됨
    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedAt;

    @Lob    //CLOB, BLOB
    private String description;
}
