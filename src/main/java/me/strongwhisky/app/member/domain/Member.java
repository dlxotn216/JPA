package me.strongwhisky.app.member.domain;

import lombok.Getter;
import lombok.Setter;
import me.strongwhisky.app.common.domain.BaseEntity;
import me.strongwhisky.app.common.value.Address;
import me.strongwhisky.app.order.domain.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taesu on 2018-04-25.
 */
@Entity(name = "Member")
@Table(name = "MEMBER")
@Getter
@Setter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(generator = "MemberSequence")
    @SequenceGenerator(sequenceName = "MEMBER_SEQ", name = "MemberSequence")
    private Long memberId;

    @Column(name = "USER_ID", unique = true)
    private String userId;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

}
