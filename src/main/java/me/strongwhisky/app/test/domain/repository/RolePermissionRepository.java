package me.strongwhisky.app.test.domain.repository;

import me.strongwhisky.app.test.domain.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taesu on 2018-05-19.
 */
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
}
