package me.strongwhisky.app._day21;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taesu on 2018-05-17.
 */
@Entity
@Table
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(generator = "CustomerSeq")
    @SequenceGenerator(name = "CustomerSeq", sequenceName = "CUSTOMER_SEQ")
    private Long customerId;

    private String userName;

    private String name;

    private String phone;

    @OneToMany(mappedBy = "writer")
    private List<Comment> comments = new ArrayList<>();
}
