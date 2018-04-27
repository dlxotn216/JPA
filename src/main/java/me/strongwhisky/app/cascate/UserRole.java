package me.strongwhisky.app.cascate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by taesu on 2018-04-27.
 */
@Entity
@Getter
@Setter
@Table(name = "USER_ROLE")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ROLE_SEQ")
    @SequenceGenerator(name = "USER_ROLE_SEQ")
    @Column(name = "USER_ROLE_KEY")
    private Long userRoleKey;

    @ManyToOne(cascade = CascadeType.PERSIST)       //UserRole 삭제시 다 삭제되면 위험하므로
    @JoinColumn(name = "USER_KEY")
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)       //UserRole 삭제시 다 삭제되면 위험하므로
    @JoinColumn(name = "ROLE_KEY")
    private Role role;

    public UserRole() {
    }

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (user != null ? !user.equals(userRole.user) : userRole.user != null) return false;
        return role != null ? role.equals(userRole.role) : userRole.role == null;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
