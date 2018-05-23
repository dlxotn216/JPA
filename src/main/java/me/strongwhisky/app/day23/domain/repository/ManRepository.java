package me.strongwhisky.app.day23.domain.repository;

import me.strongwhisky.app.day23.domain.model.Man;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taesu on 2018-05-23.
 */
public interface ManRepository extends JpaRepository<Man, Long> {
}
