package me.strongwhisky.app.querydsl;

import com.mysema.query.annotations.QueryDelegate;
import com.mysema.query.types.expr.BooleanExpression;

/**
 * Created by taesu on 2018-05-01.
 *
 * 메소드 위임을 통해 BooleanBuilder에 사용할 수 있으며 maven compile 후
 * target 하위에 자동으로 생성 된 QMember 오브젝트에 isYounggerMember 라는 메소드가 추가 되어있도록 됨
 */
public class MemberBooleanExpression {

    @QueryDelegate(TestMember.class)
    public static BooleanExpression isYounggerMember(QTestMember member, Integer age){
        return member.age.lt(age);
    }
}
