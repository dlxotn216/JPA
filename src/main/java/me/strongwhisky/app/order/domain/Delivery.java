package me.strongwhisky.app.order.domain;

import lombok.Getter;
import lombok.Setter;
import me.strongwhisky.app.common.domain.BaseEntity;
import me.strongwhisky.app.common.value.Address;

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

    @Embedded
    private Address address;

    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    public Delivery() {

    }

    public Delivery(Address address) {
        this.address = address;
    }
}
