package me.strongwhisky.app.day30.document.domain.model.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import me.strongwhisky.app.day30.common.criteria.SearchCriteria;
import me.strongwhisky.app.day30.common.criteria.SearchOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by taesu on 2018-06-13.
 */
public class DocumentPredicatesBuilder {
    private List<SearchCriteria> params = new ArrayList<>();;

    public DocumentPredicatesBuilder(String criteria) {
        Pattern pattern = Pattern.compile("(\\w+?)(!=|=|:|<|>|<=|>=)(\\w+?),", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(criteria + ",");
        while (matcher.find()) {
            with(matcher.group(1), SearchOperation.getOperationFrom(matcher.group(2)), matcher.group(3));
        }
    }

    public DocumentPredicatesBuilder with(String key, SearchOperation operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public BooleanExpression build() {
        if (params.size() == 0) {
            return null;
        }

        List<BooleanExpression> predicates = new ArrayList<>();
        for (SearchCriteria param : params) {
            BooleanExpression exp = DocumentPredicate.getPredicate(param);
            if (exp != null) {
                predicates.add(exp);
            }
        }

        BooleanExpression result = predicates.get(0);
        for (int i = 1; i < predicates.size(); i++) {
            result = result.and(predicates.get(i));
        }
        return result;
    }
}
