package me.strongwhisky.day02.repository;

import me.strongwhisky.day02.model.UseSequenceUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taesu on 2018-04-17.
 */
public interface UseSequenceUserRepository extends JpaRepository<UseSequenceUser, Long> {
}
