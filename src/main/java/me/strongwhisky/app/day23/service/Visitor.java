package me.strongwhisky.app.day23.service;

import me.strongwhisky.app.day23.domain.model.Man;
import me.strongwhisky.app.day23.domain.model.Women;

/**
 * Created by taesu on 2018-05-23.
 */
public interface Visitor {
    void visit(Man man);
    void visit(Women women);
}
