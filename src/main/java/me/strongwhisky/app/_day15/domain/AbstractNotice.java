package me.strongwhisky.app._day15.domain;

import lombok.Getter;
import lombok.Setter;
import me.strongwhisky.app.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by taesu on 2018-05-08.
 */
@Getter
@Setter
@MappedSuperclass
public class AbstractNotice extends BaseEntity {
    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Override
    public String toString() {
        return "AbstractNotice{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                "} " + super.toString();
    }
}
