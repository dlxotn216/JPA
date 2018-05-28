package me.strongwhisky.app.day26.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taesu on 2018-05-24.
 */
@Getter
@Setter
@Entity
@Table(name = "APP_GROUP")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GroupKeyGen")
    @SequenceGenerator(name = "GroupKeyGen", sequenceName = "GROUP_SEQ")
    private Long groupKey;

    private String groupName;

    private String description;

    /*
    지연 로딩이라면 요구하는 시점에 10개씩 미리 로딩한다
    즉시 로딩이라면 Group 조회 시 10건의 데이터를 로딩한다
    IN 쿼리로 실행 됨
     */
//    @BatchSize(size = 10)

    /*
     지연 로딩 이라면 요구하는 시점에 Sub Query 실행
     즉시 로딩이라면 Group 조회 시 Sub Query 실행
     */
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        if (user != null && !this.users.contains(user)) {
            this.users.add(user);
            user.setGroup(this);
        }
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }
}
