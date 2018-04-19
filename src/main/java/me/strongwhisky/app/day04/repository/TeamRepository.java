package me.strongwhisky.app.day04.repository;

import me.strongwhisky.app.day04.mode.Team;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taesu on 2018-04-19.
 */
public interface TeamRepository extends JpaRepository<Team, String> {

}
