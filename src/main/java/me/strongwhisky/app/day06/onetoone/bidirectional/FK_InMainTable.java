package me.strongwhisky.app.day06.onetoone.bidirectional;

import javax.persistence.*;

/**
 * Created by taesu on 2018-04-23.
 *
 * 주테이블에 외래키가 있는 일대일 양방향 관계
 *
 * 분리 되어야 하나 예제를 위해 작성 (실행 시 돌아가지 않을 것임)
 */
public class FK_InMainTable {

//    @Entity
//    public class Member {   //주 테이블
//        @Id
//        @GeneratedValue
//        private Long id;
//
//        @OneToOne
//        @JoinColumn(name = "LOCKER_ID")
//        private Locker locker;
//    }
//
//    @Entity
//    public class Locker {
//        @Id
//        @GeneratedValue
//        @Column(name = "LOCKER_ID")
//        private Long id;
//
//        private String name;
//
//        //반대방향 링크
//        @OneToOne(mappedBy = "locker")
//        private Member member;
//    }
}
