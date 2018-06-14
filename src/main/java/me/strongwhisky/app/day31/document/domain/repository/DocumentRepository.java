package me.strongwhisky.app.day31.document.domain.repository;

import me.strongwhisky.app.day31.document.domain.model.Document;
import me.strongwhisky.app.day31.document.infra.DocumentCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project hibernate
 * @since 2018-06-12
 */
public interface DocumentRepository extends JpaRepository<Document, Long>,
        JpaSpecificationExecutor<Document>,

        DocumentCustomRepository {
}
