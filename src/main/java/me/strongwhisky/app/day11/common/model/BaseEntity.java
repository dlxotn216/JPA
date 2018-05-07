package me.strongwhisky.app.day11.common.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

/**
 * Created by taesu on 2018-04-26.
 * <p>
 * 객체들이 주로 사용하는 공통 매핑정보의 정의
 * -> 현재 클래스는 Table로 매핑되지 않음
 * <p>
 * 생성일, 수정일, 생성 한 사람, 수정한 사람, deleted 등 필드를 보유하면 좋을 듯
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Column(name = "CREATED_AT")
    private ZonedDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private ZonedDateTime updatedAt;
}
