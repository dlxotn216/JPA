package me.strongwhisky.app.day23.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by taesu on 2018-05-19.
 */
@Entity
@Table(name = "APP_USER")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserKeyGen")
    @SequenceGenerator(name = "UserKeyGen", sequenceName = "USER_SEQ")
    private Long userKey;

    @Column(unique = true)
    private String userId;

    private String name;

    private String email;

    private Integer age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        //getClass로 비교가 아닌 instanceof로 비교해야 함

        User user = (User) o;

        if (userKey != null ? !userKey.equals(user.userKey) : user.userKey != null) return false;
        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return age != null ? age.equals(user.age) : user.age == null;
    }

    //파라미터가 Proxy일 수 있으므로 반드시 getter를 통해 접근해야 한다.
    public boolean advancedEquals(Object o){
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getUserKey() != null ? !userKey.equals(user.getUserKey()) : user.getUserKey() != null) return false;
        if (getUserId() != null ? !userId.equals(user.getUserId()) : user.getUserId() != null) return false;
        if (getName() != null ? !name.equals(user.getName()) : user.getName() != null) return false;
        if (getEmail() != null ? !email.equals(user.getEmail()) : user.getEmail() != null) return false;
        return getAge() != null ? age.equals(user.getAge()) : user.getAge() == null;
    }

    @Override
    public int hashCode() {
        int result = userKey != null ? userKey.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }
}
