package me.strongwhisky.app.day32.document.infra;

import com.querydsl.core.types.dsl.BooleanExpression;
import me.strongwhisky.app.day32.document.domain.model.Document;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by taesu on 2018-06-13.
 */
public interface DocumentCustomRepository {
    List<Document> findAll(BooleanExpression predicate);
    List<Document> findAll(BooleanExpression predicate, Pageable pageable);
}
