package me.strongwhisky.app.day27.domain.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by taesu on 2018-05-19.
 */
@Entity
@Table(name = "APP_USER")
@Getter
@Setter
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_KEY")
    private Group group;

    public void setGroup(Group group) {
        if (this.group != null && !this.group.equals(group)) {
            this.group.removeUser(this);
        }

        this.group = group;
        if (this.group != null) {
            this.group.addUser(this);
        }
    }

    public static User createUser(String userId, int age) {
        User user = new User();
        user.setUserId(userId);
        user.setName(userId);
        user.setEmail(userId + "@test.com");
        user.setAge(age);

        return user;
    }

}