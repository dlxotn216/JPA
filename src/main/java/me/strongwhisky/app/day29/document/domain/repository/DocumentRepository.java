package me.strongwhisky.app.day29.document.domain.repository;

import me.strongwhisky.app.day29.document.domain.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project hibernate
 * @since 2018-06-12
 */
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
