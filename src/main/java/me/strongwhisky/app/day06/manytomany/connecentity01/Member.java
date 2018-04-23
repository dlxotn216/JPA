package me.strongwhisky.app.day06.manytomany.connecentity01;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taesu on 2018-04-23.
 *
 * 다대다 단방향 (연결 엔티티 사용)
 */
@Entity
@Data
public class Member {
    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String userName;

    @OneToMany(mappedBy = "members")
    private List<MemberProduct> memberProducts = new ArrayList<>();

}
