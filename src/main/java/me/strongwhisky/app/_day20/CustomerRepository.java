package me.strongwhisky.app._day20;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taesu on 2018-05-17.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
