package me.strongwhisky.app.day07.model.order;

import lombok.Getter;
import lombok.Setter;
import me.strongwhisky.app.day07.model.base.BaseEntity;

import javax.persistence.*;

/**
 * Created by taesu on 2018-04-25.
 */
@Entity
@Table(name = "DELIVERY")
@Getter
@Setter
public class Delivery extends BaseEntity {

    @Id
    @Column(name = "DELIVERY_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DELIVERY_SEQ")
    @SequenceGenerator(name = "DELIVERY_SEQ")
    private Long deliveryId;

    private String city;

    private String street;

    private String zipcode;

    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery")
    private Order order;
}
