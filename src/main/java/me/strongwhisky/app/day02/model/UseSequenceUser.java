package me.strongwhisky.app.day02.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by taesu on 2018-04-17.
 */
@Entity(name = "SEQUENCED_USER")
@Data
//@SequenceGenerator(name = "USER_SEQ_GENERATOR", sequenceName = "USER_SEQ", initialValue = 1, allocationSize = 1) 또는 여기에 선언 가능
public class UseSequenceUser {

    /**
     * SEQUENCE 전략 DB의 Sequence 오브젝트 사용
     * persist 할 경우 DB에서 식별자를 조회한 후 엔티티에 할당하여 영속성 컨텍스트에 저장 함 (쓰기지연 발생)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GENERATOR")
    @SequenceGenerator(name = "USER_SEQ_GENERATOR", sequenceName = "USER_SEQ", initialValue = 1, allocationSize = 1)
    private Long userKey;

    private String userId;

    private String userName;
}
