package me.strongwhisky.app.day05.service;

import lombok.extern.slf4j.Slf4j;
import me.strongwhisky.app.day05.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by taesu on 2018-04-21.
 */
@Service
@Slf4j
public class PrintService {

    @Autowired
    private TeamRepository teamRepository;

    /**
     * Transactional이 걸려있지 않다면
     * 객체 그래프 탐색 시도 할 경우 failed to lazily initialize 발생
     *
     * 또다른 해결 방법으로 OneToMany에 FetchType.EAGER 를 주는 방법이 있으나
     * 해당 방법은 Team을 가져올 때 하위 멤버도 한꺼번에 가져오는 연산임
     */
    @Transactional(readOnly = true)
    public void printAll() {
        log.info("-----------------------------------------------------------------------");
        teamRepository.findAll().forEach(team ->
                team.getMembers().forEach(member -> {
                    log.info(team.getTeamName() + "'s member :" + member.getMemberName());
                })
        );
    }

    /*
    output

    2018-04-21 15:10:20.674  INFO 5208 --- [  restartedMain] m.s.app.day05.service.PrintService       : Development's member :Dl xo tn
    2018-04-21 15:10:20.674  INFO 5208 --- [  restartedMain] m.s.app.day05.service.PrintService       : Development's member :Lee Tae Su
    2018-04-21 15:10:20.675  INFO 5208 --- [  restartedMain] m.s.app.day05.service.PrintService       : Opeartion's member :OP[Owner]
    2018-04-21 15:10:20.675  INFO 5208 --- [  restartedMain] m.s.app.day05.service.PrintService       : Opeartion's member :OP[managere]
     */
}
