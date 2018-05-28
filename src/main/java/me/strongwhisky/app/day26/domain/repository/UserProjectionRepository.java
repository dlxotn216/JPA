package me.strongwhisky.app.day26.domain.repository;

import me.strongwhisky.app.day26.domain.model.User;
import me.strongwhisky.app.day26.domain.model.UserForDropdown;
import me.strongwhisky.app.day26.domain.model.UserForDropdownAsClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by taesu on 2018-05-28.
 * <p>
 * User의 프로젝션 전용 리파지토리
 */
public interface UserProjectionRepository extends JpaRepository<User, Long> {

    /**
     * <code>name</code>을 포함하는 이름을 가진
     * User의 Dropdown 구성 시 필요한 요소만 반환한다
     *
     * @param name User name
     * @return UserForDropdown list
     *
     * @see UserForDropdown
     */
    List<UserForDropdown> findAllByNameIsLike(String name);

    /**
     * <code>email</code>을 포함하는 이메일을 가진
     * User의 Dropdown 구성 시 필요한 요소만 반환한다
     *
     * @param email Email
     * @return UserForDropdown list
     *
     * @see UserForDropdownAsClass
     */
    List<UserForDropdownAsClass> findAllByEmailIsLike(String email);

    /**
     * <code>start</code> ~ <code>end</code> 사이의 나이에 있는
     * User의 필요 프로젝션을 구성 한 Class 또는 Interface를 반환한다
     *
     * 동적으로 프로젝션을 반환받을 수 있다
     *
     * @param start 시작 나이
     * @param end 끝 나이
     * @param clazz 반환 받을 프로젝션 타입
     * @param <T> Generics
     * @return UserForDropdown list
     */
    <T> List<T> findAllByAgeBetween(int start, int end, Class<T> clazz);
}
