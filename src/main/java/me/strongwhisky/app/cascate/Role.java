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
@Table(name = "ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ")
    @SequenceGenerator(name = "ROLE_SEQ")
    @Column(name = "ROLE_KEY")
    private Long roleKey;

    @Column(name = "ROLE_NAME", unique = true, nullable = false)
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<UserRole> userRoles = new LinkedHashSet<>();

    public void addUserRole(User user){
        UserRole userRole = new UserRole(user, this);
        addUserRole(userRole);
    }

    public void addUserRole(UserRole userRole){
        if(this.userRoles.contains(userRole)){
            return;
        }
        this.userRoles.add(userRole);
        userRole.getUser().addUserRole(userRole);
    }
}
