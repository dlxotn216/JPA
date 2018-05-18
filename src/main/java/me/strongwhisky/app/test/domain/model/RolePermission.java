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
public class RolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RolePermissionKeyGen")
    @SequenceGenerator(name = "RolePermissionKeyGen", sequenceName = "ROLE_PERMISSION_SEQ")
    private Long rolePermissionKey;

    @ManyToOne
    @JoinColumn(name = "ROLE_KEY")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "PERMISSION_KEY")
    private Permission permission;
}
