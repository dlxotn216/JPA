package me.strongwhisky.app.querydsl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * Created by taesu on 2018-05-01.
 */
@Setter
@Getter
public class TeamStatistic {

    private TestTeam testTeam;

    private int maxMemberAge;
    
    private int minMemberAge;
    
    private double avgMemberAge;
    
    private int sumMemberAge;

    private long userCount;

    public TeamStatistic() {
    }

    public TeamStatistic(int maxMemberAge, int minMemberAge, double avgMemberAge, int sumMemberAge, long userCount) {
        this.maxMemberAge = maxMemberAge;
        this.minMemberAge = minMemberAge;
        this.avgMemberAge = avgMemberAge;
        this.sumMemberAge = sumMemberAge;
        this.userCount = userCount;
    }

    @Override
    public String toString() {
        return "TeamStatistic{" +
                "testTeam=" + testTeam +
                ", maxMemberAge=" + maxMemberAge +
                ", minMemberAge=" + minMemberAge +
                ", avgMemberAge=" + avgMemberAge +
                ", sumMemberAge=" + sumMemberAge +
                ", userCount=" + userCount +
                '}';
    }
}
