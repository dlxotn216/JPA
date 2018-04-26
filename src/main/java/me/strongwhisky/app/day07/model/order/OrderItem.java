package me.strongwhisky.app.day07.model.order;

import lombok.Getter;
import lombok.Setter;
import me.strongwhisky.app.day07.model.base.BaseEntity;
import me.strongwhisky.app.day07.model.item.Item;

import javax.persistence.*;

/**
 * Created by taesu on 2018-04-25.
 */
@Entity
@Table(name = "ORDER_ITEM")
@Getter
@Setter
public class OrderItem extends BaseEntity {

    @Id
    @Column(name = "ORDER_ITEM_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ITEM_SEQ")
    @SequenceGenerator(name = "ORDER_ITEM_SEQ")
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @Column(name = "ORDER_PRICE")
    private int orderPrice;

    private int count;
}
