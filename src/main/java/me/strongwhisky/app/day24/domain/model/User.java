package me.strongwhisky.app.day24.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    //fetch = EAGER
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GROUP_KEY")
    private Group group;

    public void setGroup(Group group){
        if(this.group != null && !this.group.equals(group)){
            this.group.removeUser(this);
        }

        this.group = group;
        if(this.group != null){
            this.group.addUser(this);
        }
    }

}
