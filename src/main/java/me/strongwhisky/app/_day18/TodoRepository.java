package me.strongwhisky.app._day18;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taesu on 2018-05-10.
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
