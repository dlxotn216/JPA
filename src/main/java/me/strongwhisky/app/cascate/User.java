package me.strongwhisky.app.cascate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by taesu on 2018-04-27.
 */
@Entity
@Getter
@Setter
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(name = "USER_SEQ")
    @Column(name = "USER_KEY")
    private Long userKey;

    @Column(name = "USER_ID", unique = true, nullable = false)
    private String userId;

    @Column(name = "USER_NAME")
    private String userName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRole> userRoles = new LinkedHashSet<>();

    public void addUserRole(Role role) {
        UserRole userRole = new UserRole(this, role);
        addUserRole(userRole);
    }

    public void addUserRole(UserRole userRole) {
        if (this.userRoles.contains(userRole)) {
            return;
        }
        this.userRoles.add(userRole);
        userRole.getRole().addUserRole(userRole);
    }

}
