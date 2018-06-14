package me.strongwhisky.app.day31.document.domain.model.specification;

import me.strongwhisky.app.day31.common.criteria.SearchCriteria;
import me.strongwhisky.app.day31.document.domain.model.Document;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by taesu on 2018-06-13.
 */
public class DocumentSpecification implements Specification<Document> {
    private SearchCriteria criteria;

    public DocumentSpecification(SearchCriteria searchCriteria) {
        this.criteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Document> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case EQUAL:
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case NOT_EQUAL:
                return builder.notEqual(root.get(criteria.getKey()), criteria.getValue().toString());
            case LIKE:
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue().toString() + "%");
            case GREATER_THAN:
                return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case GREATER_EQUAL_THAN:
                return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
            case LESS_THAN:
                return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case LESS_EQUAL_THAN:
                return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
            default:
                return null;
        }
    }
}
