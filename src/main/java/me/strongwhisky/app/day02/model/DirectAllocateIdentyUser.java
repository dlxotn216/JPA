package me.strongwhisky.app.day02.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by taesu on 2018-04-17.
 */
@Entity(name = "DIRECT_ALLOCATE_USER")
@Data
public class DirectAllocateIdentyUser {

    /**
     * String, java.util.Date, java.sql.Date, BigDecimal, BigInteger 할당 가능
     * */
    @Id
    private String Id;

    private String name;
}
