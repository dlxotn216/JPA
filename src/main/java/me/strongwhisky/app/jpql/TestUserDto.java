package me.strongwhisky.app.jpql;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by taesu on 2018-05-01.
 */
@Getter
@Setter
public class TestUserDto {

    private String username;

    private int age;

    public TestUserDto() {
    }

    public TestUserDto(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
