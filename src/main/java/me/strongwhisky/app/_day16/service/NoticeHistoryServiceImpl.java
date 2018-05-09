package me.strongwhisky.app._day16.service;

import me.strongwhisky.app._day16.domain.Notice;
import me.strongwhisky.app._day16.domain.NoticeHistory;
import me.strongwhisky.app._day16.repository.NoticeHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by taesu on 2018-05-08.
 */
@Service
public class NoticeHistoryServiceImpl implements NoticeHistoryService {
    @Autowired
    private NoticeHistoryRepository noticeHistoryRepository;

    /**
     * 부모 트랜잭션 진행 중 onPostUpdate 이벤트를 받아
     * Audit data를 저장해야 하는 경우를 위해 REQUIRES_NEW 트랜잭션을 사용
     *
     * @see Propagation
     * @see org.springframework.transaction.TransactionDefinition
     * (propagation = Propagation.REQUIRES_NEW) -> 부모의 영향 받고 자식이 부모에게도 영향을 미친다
     * (propagation = Propagation.NESTED) -> 부모의 영향을 받고 자식은 부모에게 영향을 미치지 않는다.
     * 단, jdbc 3.0 스펙의 저장포인트 지원 드라이버와 DataSourceTransactionManager를 이용해야
     *
     * Audit이 없는 Data는 유효하지 않다고 판단하여 REQUIRES_NEW 옵션 사용
     *
     * Default인 REQUIRED 옵션을 사용할 경우
     * JPA의 doCommit 실행 시 ConcurrentModificationException 발생
     *
     * 라이프 사이클 콜백 메소드에서 쿼리 작업 또는 다른 엔티티 인스턴스에 엑세스 하거나
     * 동일한 영속성 컨텍스트 내에서 관계 수정을 하면 안된다.
     * https://stackoverflow.com/questions/35535287/insert-in-entitylistener-cause-concurrentmodificationexception-in-hibernate
     *
     *
     * @param noticeHistory NoticeHistory
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void save(NoticeHistory noticeHistory) {
        noticeHistoryRepository.save(noticeHistory);
    }

    @Override
    public Page<NoticeHistory> findAllById(Long noticeKey, Pageable pageable) {
        return noticeHistoryRepository.findAllByNoticeKey(noticeKey, pageable);
    }

    /**
     * 부모 트랜잭션 진행 중에 <code>onPostUpdate</code>가 호출되어
     * History 저장을 위해 REQUIRES_NEW 트랜잭션을 통해 생성한 Audit data를
     * 부모 트랜잭션에서도 읽을 수 있도록 별개의 자식 트랜잭션을 생성한다
     *
     * @see #save(NoticeHistory)
     * @see me.strongwhisky.app._day16.domain.NoticeListener#onPostUpdate(Notice)
     *
     * @param noticeKey Notice Key
     * @return Latest Notice history
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public NoticeHistory findTopByNoticeKey(Long noticeKey){
        return noticeHistoryRepository.findTopByNoticeKey(noticeKey);
    }
}
