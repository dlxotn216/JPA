package me.strongwhisky;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by taesu on 2018-04-15.
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
