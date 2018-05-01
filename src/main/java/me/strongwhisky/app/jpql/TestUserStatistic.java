package me.strongwhisky.app.jpql;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by taesu on 2018-05-01.
 */
@Getter
@Setter
public class TestUserStatistic {
    private long totalCount;

    private long sumAvg;

    private int maxAge;

    private int minAge;

    private double avgAge;

    public TestUserStatistic(){}

    public TestUserStatistic(long totalCount, long sumAvg, int maxAge, int minAge, double avgAge) {
        this.totalCount = totalCount;
        this.sumAvg = sumAvg;
        this.maxAge = maxAge;
        this.minAge = minAge;
        this.avgAge = avgAge;
    }
}
