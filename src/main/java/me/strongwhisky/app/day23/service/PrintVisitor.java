package me.strongwhisky.app.day23.service;

import me.strongwhisky.app.day23.domain.model.Man;
import me.strongwhisky.app.day23.domain.model.Women;
import org.springframework.stereotype.Service;

/**
 * Created by taesu on 2018-05-23.
 */
@Service
public class PrintVisitor implements Visitor {
    @Override
    public void visit(Man man) {
        //프록시가 아닌 원본이다
        System.out.println(man.getClass());
    }

    @Override
    public void visit(Women women) {
        System.out.println(women.getClass());
    }
}
