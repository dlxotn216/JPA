package me.strongwhisky.app.test.domain.repository;

import me.strongwhisky.app.test.domain.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by taesu on 2018-05-19.
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
