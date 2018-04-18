package me.strongwhisky.app.day02.repository;

import me.strongwhisky.app.day02.model.GeneratedValueUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taesu on 2018-04-17.
 */
public interface GeneratedValueUserRepository extends JpaRepository<GeneratedValueUser, Long> {
}
