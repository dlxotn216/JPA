package me.strongwhisky.app.day26.domain.model;

import lombok.Value;

/**
 * Created by taesu on 2018-05-28.
 *
 * Lombok의 @Value 어노테이션 사용
 * -> 필드와 All args constructor 와 Getter만 존재한다
 */
@Value
public class UserForDropdownAsClass {
    private Long userKey;

    private String name;
}
