package me.strongwhisky.app.cascate;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taesu on 2018-04-27.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
