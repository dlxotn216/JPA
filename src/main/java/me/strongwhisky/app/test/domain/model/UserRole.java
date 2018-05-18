package me.strongwhisky.app.test.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by taesu on 2018-05-19.
 */
@Entity
@Table
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserRoleKeyGen")
    @SequenceGenerator(name = "UserRoleKeyGen", sequenceName = "USER_ROLE_SEQ")
    private Long userRoleKey;

    @ManyToOne
    @JoinColumn(name = "USER_KEY")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ROLE_KEY")
    private Role role;
}
