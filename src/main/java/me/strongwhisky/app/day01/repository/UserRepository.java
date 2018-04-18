package me.strongwhisky.app.day01.repository;

import me.strongwhisky.app.day01.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taesu on 2018-04-15.
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
