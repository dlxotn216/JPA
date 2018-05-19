package me.strongwhisky.app.day22.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by taesu on 2018-05-19.
 */
@Getter
@Setter
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;

    public SearchCriteria(String key, String operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }
}
