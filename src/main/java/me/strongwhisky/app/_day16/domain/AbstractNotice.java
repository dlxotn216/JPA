package me.strongwhisky.app._day16.domain;

import lombok.Getter;
import lombok.Setter;
import me.strongwhisky.app.common.domain.BaseEntity;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

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



    public void onPrePersist(){
        if(StringUtils.isEmpty(this.getReason())){
            this.setReason("initial input");
        }
        ZonedDateTime current = ZonedDateTime.now();
        if(this.getCreatedAt() == null){
            this.setCreatedAt(current);
        }
        if(this.getUpdatedAt() == null){
            this.setUpdatedAt(current);
        }
    }

    public void onPreUpdate(){
        ZonedDateTime current = ZonedDateTime.now();
        if(this.getUpdatedAt() == null){
            this.setCreatedAt(current);
        }
        if(this.getUpdatedAt() == null){
            this.setUpdatedAt(current);
        }
    }
}
