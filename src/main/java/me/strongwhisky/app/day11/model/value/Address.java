package me.strongwhisky.app.day11.model.value;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Embeddable;

/**
 * Created by taesu on 2018-04-28.
 *
 * Address 타입의 값 타입
 * 불변객체로 사용하는 것이 좋기 때문에 Sette를 사용하지 않아야 한다
 *
 * 값 타입은 반드시 equals와 hashcode를 구현해야 한다
 */
@Builder
@Getter
@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcode;

    public Address(){}

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        return zipcode != null ? zipcode.equals(address.zipcode) : address.zipcode == null;
    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (zipcode != null ? zipcode.hashCode() : 0);
        return result;
    }
}
