package me.strongwhisky.app.test.domain.repository;

import me.strongwhisky.app.test.domain.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taesu on 2018-05-19.
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
