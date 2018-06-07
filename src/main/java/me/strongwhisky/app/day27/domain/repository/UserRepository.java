package me.strongwhisky.app.day27.domain.repository;

import me.strongwhisky.app.day27.domain.model.Group;
import me.strongwhisky.app.day27.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by taesu on 2018-05-19.
 */
public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
    List<User> findAllByGroup(Group group);
}
