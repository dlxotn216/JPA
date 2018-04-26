package me.strongwhisky.app.day07.model.order;

import lombok.Getter;
import lombok.Setter;
import me.strongwhisky.app.day07.model.base.BaseEntity;
import me.strongwhisky.app.day07.model.member.Member;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taesu on 2018-04-25.
 */
@Entity
@Table(name = "ORDERS")
@Getter
@Setter
public class Order extends BaseEntity {

    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ")
    @SequenceGenerator(name = "ORDER_SEQ")
    private Long orderId;

    @Column(name = "ORDERED_AT")
    private ZonedDateTime orderedAt;

    @Column(name = "STATUS")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.removeOrder(this);
        }
        this.member = member;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        this.delivery.setOrder(this);
    }
}
