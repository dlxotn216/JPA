package me.strongwhisky;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by taesu on 2018-04-15.
 */
@Entity
@Data
public class User {

    @Id @GeneratedValue
    private Long id;

    private String email;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedAt;
}
