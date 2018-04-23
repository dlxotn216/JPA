package me.strongwhisky.app.day06.manytomany.connectentity02;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private List<Order> orders = new ArrayList<>();

}
