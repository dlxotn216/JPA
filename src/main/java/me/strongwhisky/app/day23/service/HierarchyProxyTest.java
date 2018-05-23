package me.strongwhisky.app.day23.service;

import me.strongwhisky.app.day23.domain.model.Man;
import me.strongwhisky.app.day23.domain.model.Person;
import me.strongwhisky.app.day23.domain.repository.ManRepository;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * Created by taesu on 2018-05-23.
 */
@Service
public class HierarchyProxyTest {

    @Autowired
    private Visitor printVisitor;

    @Autowired
    private ManRepository manRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public void saveManAndFindAsPerson() {
        Man man = new Man();
        man.setPersonKey(1L);
        man.setName("이태수");
        man.setAge(13);

        manRepository.save(man);

        Person personProxy = manRepository.findById(man.getPersonKey()).orElseThrow(RuntimeException::new);

        System.out.println(man);
        System.out.println(personProxy);
        System.out.println("compare by == " + (man == personProxy));
        System.out.println("compare by equals " + (man.equals(personProxy)));

        System.out.println(man.getClass());
        System.out.println(personProxy.getClass());
        System.out.println("instance of " + (personProxy instanceof Man));
        /*
        me.strongwhisky.app.day23.domain.model.Man@5dd2229e
        me.strongwhisky.app.day23.domain.model.Man@5dd2229e
        compare by == false
        compare by equals true
        class me.strongwhisky.app.day23.domain.model.Man
        class me.strongwhisky.app.day23.domain.model.Man
        instance of true
         */

    }

    public void saveManAndFindAsPersonByEntityManager() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Man man = new Man();
        man.setPersonKey(2L);
        man.setName("이태수");
        man.setAge(13);

        entityManager.persist(man);
        entityManager.flush();
        entityManager.clear();

        //Proxy 조회 시 주의 필요
        Person personProxy = entityManager.getReference(Person.class, man.getPersonKey());

        System.out.println(man);
        System.out.println(personProxy);
        System.out.println("compare by == " + (man == personProxy));
        System.out.println("compare by equals " + (man.equals(personProxy)));

        System.out.println(man.getClass());
        System.out.println(personProxy.getClass());
        System.out.println("instance of " + (personProxy instanceof Man));

        System.out.println("-------------------Proxy 벗기기-------------------");
        Person unProxyPerson = unproxy(personProxy);
        if(unProxyPerson instanceof Man){
            System.out.println(unProxyPerson.getClass());
            Man unProxyMan  = (Man) unProxyPerson;
            System.out.println(unProxyMan.getClass());
            //엔티티를 프록시로부터 직접 꺼내기 때문에 원본 엔티티와 프록시간의 동등성 비교는 실패하는 문제점
        }

        //Visitor 패턴을 통해 Proxy가 아닌 원본 접근 가능
        //accept는 실제 구현체(원본 엔티티)에 구현된 내용이므로
        //accept로 전달하는 Argument는 프록시이지만
        //accept에서 받는 Parameter는 원본 엔티티이다
        System.out.println("-------------------Visitor 사용-------------------");
        personProxy.accept(printVisitor);

        /*
        me.strongwhisky.app.day23.domain.model.Man@5dd2265f
        compare by == false
        compare by equals false
        class me.strongwhisky.app.day23.domain.model.Man
        class me.strongwhisky.app.day23.domain.model.Person_$$_jvstf2e_b
        instance of false
        -------------------Proxy 벗기기-------------------
        class me.strongwhisky.app.day23.domain.model.Man
        class me.strongwhisky.app.day23.domain.model.Man
        -------------------Visitor 사용-------------------
        class me.strongwhisky.app.day23.domain.model.Man
         */

        transaction.commit();
    }

    private <T> T unproxy(Object object) {
        if (object instanceof HibernateProxy) {
            object = ((HibernateProxy) object)
                    .getHibernateLazyInitializer()
                    .getImplementation();
        }
        return (T) object;
    }
}
