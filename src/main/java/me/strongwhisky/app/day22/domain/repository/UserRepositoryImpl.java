package me.strongwhisky.app.day22.domain.repository;

import me.strongwhisky.app.day22.domain.model.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

/**
 * Created by taesu on 2018-05-19.
 * <p>
 * Impl Postfix가 default임
 */
public class UserRepositoryImpl extends QuerydslRepositorySupport implements AdditionalUserRepository {
    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     */
    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public List<User> findAllWithUserAllGraph(Pageable pageable) {
        QUser user = new QUser("User");
        QUserRole userRole = new QUserRole("UserRole");
        QRole role = new QRole("role");
        QRolePermission rolePermission = new QRolePermission("RolePermission");
        QPermission permission = new QPermission("Permission");

        return from(user)
                .distinct()
                .leftJoin(user.userRoles, userRole).fetchJoin()
                .leftJoin(userRole.role, role).fetchJoin()
                .leftJoin(role.rolePermissions, rolePermission).fetchJoin()
                .leftJoin(rolePermission.permission, permission).fetchJoin()
                .fetch();
    }

    @Override
    public Optional<User> findAllWithUserAllGraphByUserKey(Long userKey) {
        return null;
    }
}

/*

 */
