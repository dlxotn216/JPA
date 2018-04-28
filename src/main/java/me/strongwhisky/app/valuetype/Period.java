package me.strongwhisky.app.valuetype;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by taesu on 2018-04-28.
 *
 * 기간 타입의 값 타입
 *
 * 값 타입은 반드시 equals와 hashcode를 구현해야 한다
 */
@Builder
@Getter
@Embeddable
public class Period {

    @Temporal(TemporalType.DATE)
    private Date startAt;

    @Temporal(TemporalType.DATE)
    private Date endAt;

    public Period(){}

    public Period(Date startAt, Date endAt) {
        this.startAt = startAt;
        this.endAt = endAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Period period = (Period) o;

        if (startAt != null ? !startAt.equals(period.startAt) : period.startAt != null) return false;
        return endAt != null ? endAt.equals(period.endAt) : period.endAt == null;
    }

    @Override
    public int hashCode() {
        int result = startAt != null ? startAt.hashCode() : 0;
        result = 31 * result + (endAt != null ? endAt.hashCode() : 0);
        return result;
    }
}
