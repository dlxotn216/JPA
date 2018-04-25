package me.strongwhisky.app.day07.model.order;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by taesu on 2018-04-25.
 */
@Entity
@Table(name = "DELIVERY")
@Data
public class Delivery {

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
