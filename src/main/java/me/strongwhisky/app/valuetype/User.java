package me.strongwhisky.app.valuetype;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by taesu on 2018-04-28.
 *
 * 사용자는 집 주소와 직장 주소만을 가지지만
 * 만약 요구사항에 주소 정보가 계속 늘어 날 수 있다면
 * 값 타입컬렉션을 사용할 수 있다.
 * -> 반드시 eqquals, hashcode 오버라이딩 해야한다
 *
 * 하지만 값 타입 컬렉션일 경우 JPA 구현체의 동작은
 * 모든 로우를 삭제하고 값 타입 컬렉션 개수만큼 다시 로우를 생성하는 형태로 동작한다
 * -> 값 타입은 모든 필드가 PK 이며 변경이 발생한 경우 삭제 대상을 찾기 어렵기 때문(삭제하지 않으면 쓰레기 데이터)
 *
 *********************************************************************************************
 * 따라서 값 타입 컬렉션의 사용은 지양하고 일대다 매핑을 통해 별도의 엔티티로 뽑아낸 후
 * Cascade 및 고아객체 제거를 설정하여 값 타입 컬렉션 처럼 사용하는 것이 좋다
 *********************************************************************************************
 */
@Getter
@Setter
@Entity
public class User {

    @Id
    private String userId;

    private String userName;

    @Embedded
    private Address homeAddress;

    //여러개의 값 타입을 가지는 경우
    //Attribute override 가능
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "WORK_CITY"))
            , @AttributeOverride(name = "street", column = @Column(name = "WORK_STREET"))
            , @AttributeOverride(name = "zipcode", column = @Column(name = "WORK_ZIPCODE"))
    })
    private Address workAddress;


    //값 타입 컬렉션보다는 일대다 엔티티 매핑으로!
//    @ElementCollection
//    @CollectionTable(name = "USER_ADDRESS", joinColumns = {@JoinColumn(name = "MEMBER_ID")})
//    private List<Address> addresses;

    @Embedded
    private Period accessPeriod;
}
