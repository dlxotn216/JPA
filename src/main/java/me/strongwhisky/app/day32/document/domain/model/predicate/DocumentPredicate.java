package me.strongwhisky.app.day32.document.domain.model.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import me.strongwhisky.app.day32.common.criteria.SearchCriteria;
import me.strongwhisky.app.day32.document.domain.model.Document;

/**
 * Created by taesu on 2018-06-13.
 */
public class DocumentPredicate {
    public static BooleanExpression getPredicate(SearchCriteria criteria) {
        PathBuilder<Document> builder = new PathBuilder<>(Document.class, "document");

        if (isNumeric(criteria.getValue().toString())) {
            NumberPath<Double> path = builder.getNumber(criteria.getKey(), Double.class);

            Double value = Double.valueOf(criteria.getValue().toString());
            switch (criteria.getOperation()) {
                case EQUAL:
                case LIKE:
                    return path.eq(value);
                case NOT_EQUAL:
                    return path.ne(value);
                case GREATER_THAN:
                    return path.gt(value);
                case GREATER_EQUAL_THAN:
                    return path.goe(value);
                case LESS_THAN:
                    return path.lt(value);
                case LESS_EQUAL_THAN:
                    return path.loe(value);
                default:
                    return null;
            }
        } else {
            StringPath path = builder.getString(criteria.getKey());

            switch (criteria.getOperation()) {
                case EQUAL:
                    return path.eq(criteria.getValue().toString());
                case NOT_EQUAL:
                    return path.ne(criteria.getValue().toString());
                case LIKE:
                    return path.containsIgnoreCase(criteria.getValue().toString());
            }
        }

        return null;
    }

    private static boolean isNumeric(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
