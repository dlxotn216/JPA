package me.strongwhisky.app.day31.document.infra.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import me.strongwhisky.app.day31.document.domain.model.Document;
import me.strongwhisky.app.day31.document.domain.model.QDocument;
import me.strongwhisky.app.day31.document.domain.model.QDocumentVersion;
import me.strongwhisky.app.day31.document.infra.DocumentCustomRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

/**
 * Created by taesu on 2018-06-13.
 */
public class DocumentCustomRepositoryImpl extends QuerydslRepositorySupport implements DocumentCustomRepository {

    public DocumentCustomRepositoryImpl() {
        super(Document.class);
    }

    @Override
    public List<Document> findAll(BooleanExpression expression) {
        QDocument qDocument = new QDocument("document");
        QDocumentVersion qDocumentVersion = new QDocumentVersion("version");

        return from(qDocument)
                .distinct()
                .where(expression)
                .fetch();
    }

    @Override
    public List<Document> findAll(BooleanExpression expression, Pageable pageable) {
        QDocument qDocument = new QDocument("document");
        QDocumentVersion qDocumentVersion = new QDocumentVersion("version");

        return from(qDocument)
                .distinct()
                .where(expression)
                .fetch();
    }
}
