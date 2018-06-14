package me.strongwhisky.app.day31.document.domain.model.specification;

import me.strongwhisky.app.day31.common.criteria.SearchCriteria;
import me.strongwhisky.app.day31.common.criteria.SearchOperation;
import me.strongwhisky.app.day31.document.domain.model.Document;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by taesu on 2018-06-13.
 */
public class DocumentSpecificationBuilder {
    private final List<SearchCriteria> params = new ArrayList<>();

    public DocumentSpecificationBuilder(String criteria) {
        Pattern pattern = Pattern.compile("(\\w+?)(!=|=|:|<|>|<=|>=)(\\w+?),", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(criteria + ",");
        while (matcher.find()) {
            with(matcher.group(1), SearchOperation.getOperationFrom(matcher.group(2)), matcher.group(3));
        }
    }

    private DocumentSpecificationBuilder with(String key, SearchOperation operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Document> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<Document>> specs = new ArrayList<>();
        for (SearchCriteria param : params) {
            specs.add(new DocumentSpecification(param));
        }

        Specification<Document> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }

}
