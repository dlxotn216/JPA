package me.strongwhisky.app.day22.domain.repository;

import me.strongwhisky.app.day22.domain.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taesu on 2018-05-19.
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
