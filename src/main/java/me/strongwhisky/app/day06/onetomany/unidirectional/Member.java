package me.strongwhisky.app.day06.onetomany.unidirectional;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by taesu on 2018-04-23.
 *
 * 일대다 단방향 관계에서 Member
 *
 * 아무런 매핑정보 없음
 */
@Entity @Data
public class Member {

    @Id
    private String memberId;

}
