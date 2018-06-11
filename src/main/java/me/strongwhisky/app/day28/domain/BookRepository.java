package me.strongwhisky.app.day28.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project hibernate
 * @since 2018-06-11
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
