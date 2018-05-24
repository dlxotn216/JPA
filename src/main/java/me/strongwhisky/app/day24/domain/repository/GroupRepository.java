package me.strongwhisky.app.day24.domain.repository;

import me.strongwhisky.app.day24.domain.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taesu on 2018-05-19.
 */
public interface GroupRepository extends JpaRepository<Group, Long> {

}
