package me.strongwhisky.app.test.domain.model;

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
@Table
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RoleKeyGen")
    @SequenceGenerator(name = "RoleKeyGen", sequenceName = "ROLE_SEQ")
    private Long roleKey;

    private String name;

    private String description;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<UserRole> userRoles = new HashSet<>();

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<RolePermission> rolePermissions = new HashSet<>();

    public void addRolePermission(Permission permission) {
        this.rolePermissions.add(RolePermission.builder().role(this).permission(permission).build());
    }

    public void addRoleUser(User user) {
        this.userRoles.add(UserRole.builder().user(user).role(this).build());
    }
}
