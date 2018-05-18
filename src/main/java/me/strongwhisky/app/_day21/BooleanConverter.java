package me.strongwhisky.app._day21;

import org.springframework.util.ObjectUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by taesu on 2018-05-16.
 * <p>
 * Boolean 값에 대해 저장 및 조회 시 Convert 처리
 * <p>
 * Mybatis의 TypeHandler와 동일하다
 */
@Converter(autoApply = true) //-> 모든 Boolean 타입에 대해 현재 Converter를 적용한다
public class BooleanConverter implements AttributeConverter<Boolean, String> {
    private static final String definedTrue = "TRUE";
    private static final String definedFalse = "FALSE";

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return ObjectUtils.nullSafeEquals(attribute, true)? definedTrue : definedFalse;
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return ObjectUtils.nullSafeEquals(dbData, definedTrue);
    }
}
