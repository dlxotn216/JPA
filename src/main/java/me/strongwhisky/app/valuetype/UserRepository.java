package me.strongwhisky.app.valuetype;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taesu on 2018-04-28.
 */
public interface UserRepository extends JpaRepository<User, String> {
}
