package me.strongwhisky.day02.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by taesu on 2018-04-17.
 */
@Entity(name = "GENERATED_USER")
@Data
public class GeneratedValueUser {

    /**
     * IDENTITY 기본키 생성을 DB로 위임 (MySQL, PostgresSQL, SQL Server, DB2)
     * <p>
     * persist 할 경우 DB에 식별자를 생성 위임하므로 쓰기지연 발생하지 않음(영속성 컨텍스트에 저장을 못하니까..)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userKey;

    private String userId;

    private String userName;

}
