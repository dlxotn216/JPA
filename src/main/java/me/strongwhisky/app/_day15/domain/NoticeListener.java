package me.strongwhisky.app._day15.domain;

import lombok.extern.slf4j.Slf4j;
import me.strongwhisky.app._day15.BeanUtil;
import me.strongwhisky.app._day15.service.NoticeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * Created by taesu on 2018-05-08.
 */
@Component
@Slf4j
public class NoticeListener {
	
	private static NoticeHistoryService noticeHistoryService;
	
	//Static inject 받아야 null이 아님
	@Autowired
	public void setNoticeHistoryRepository(NoticeHistoryService noticeHistoryService) {
		NoticeListener.noticeHistoryService = noticeHistoryService;
	}
	
	@PrePersist
	public void onPrePersist(Object object) {
		if(object instanceof NoticeHistory) {
			//NoticeHistory의 save 시에도 발동 함.
			return;
		}
		
		if(object instanceof Notice) {
			Notice notice = (Notice) object;
			notice.setCreatedAt(ZonedDateTime.now());
		}
		log.info("check onPrePersist : " + object);
	}
	
	@PreUpdate
	public void onPreUpdate(Object object) {
		log.info("check onPreUpdate : " + object);
	}
	
	@PostPersist
	public void onPostPersist(Object object) {
		if(object instanceof Notice) {
			NoticeHistory noticeHistory = new NoticeHistory((Notice) object);
			noticeHistory.setReason("initial input");
//			audit((noticeHistory));
			noticeHistoryService.save(noticeHistory);
		}
		log.info("check onPostPersist:  " + object);
	}
	
	//아래 예제대로 Transaction require new propagation 옵션 주어도 동작하지 않음
	//https://dzone.com/articles/jpa-auditing-automatically-persisting-audit-logs
	
	//새로운 Transaction 만들어서 처리
	public void audit(NoticeHistory noticeHistory ){
		EntityManagerFactory entityManagerFactory = BeanUtil.getBean(EntityManagerFactory.class);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(noticeHistory);
		transaction.commit();
	}
	
	
	@PostUpdate
	public void onPostUpdate(Object object) {
		if(object instanceof Notice) {
			NoticeHistory noticeHistory = new NoticeHistory((Notice) object);
			noticeHistoryService.save(noticeHistory);
		}
		log.info("check onPostUpdate : " + object);
	}
}
