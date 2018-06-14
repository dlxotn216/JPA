package me.strongwhisky.app.day31.common.criteria;

import lombok.Getter;

/**
 * Created by taesu on 2018-06-13.
 */
@Getter
public class SearchCriteria {
    private String key;
    private SearchOperation operation;
    private Object value;

    public SearchCriteria(String key, SearchOperation operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }
}
