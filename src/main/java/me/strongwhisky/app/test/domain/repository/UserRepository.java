package me.strongwhisky.app.test.domain.repository;

import me.strongwhisky.app.test.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taesu on 2018-05-19.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
