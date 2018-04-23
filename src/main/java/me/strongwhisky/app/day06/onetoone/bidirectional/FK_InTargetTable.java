package me.strongwhisky.app.day06.onetoone.bidirectional;

import javax.persistence.*;

/**
 * Created by taesu on 2018-04-23.
 *
 * 일대일 양방향, 대상 테이블에 외래키가 있을 때
 */
public class FK_InTargetTable {
//    @Entity
//    public class Member {   //주 테이블
//        @Id
//        @GeneratedValue
//        private Long id;
//
//        @OneToOne(mappedBy = "locker")
//        private Locker locker;
//    }
//
//    @Entity
//    public class Locker {   //대상 테이블
//        @Id
//        @GeneratedValue
//        @Column(name = "LOCKER_ID")
//        private Long id;
//
//        private String name;
//
//        //반대방향 링크
//        @OneToOne
//        @JoinColumn(name = "MEMBER_ID")
//        private Member member;
//    }
}
