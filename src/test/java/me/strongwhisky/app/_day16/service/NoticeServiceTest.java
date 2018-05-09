package me.strongwhisky.app._day16.service;

import me.strongwhisky.app._day16.domain.Notice;
import me.strongwhisky.app._day16.domain.NoticeHistory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by taesu on 2018-05-08.
 *
 * 만약 모든 메소드에서 Rollback을 위해 Transactional 적용 되었다고 가정하면
 * 실제 NoticeHistoryService.save를 통해 히스토리는 저장 되지만
 * 같은 Transactional (영속성 컨텍스트)가 아니므로
 * History 정보를 읽을 수 없다
 * (NoticeHistory는 REQUIRES_NEW propagation으로 지정되어있으므로)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
public class NoticeServiceTest {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeHistoryService noticeHistoryService;

    @Test
    @Rollback   //개별 Rollback 지정
    public void 생성일테스트() {
        //Given
        Notice notice = new Notice();
        notice.setTitle("테ㅡ트");
        notice.setContent("내용이빈당");

        //When
        Notice saved = noticeService.saveNotice(notice);

        //Then
        NoticeHistory noticeHistory = noticeHistoryService.findTopByNoticeKey(saved.getNoticeKey());

        assertThat(saved.getCreatedAt()).isNotNull();
        assertThat(saved.getUpdatedAt()).isNotNull().describedAs("수정일{0}은 누락되지 않는다", saved.getUpdatedAt());
        assertThat(noticeHistory).isNotNull();
        assertThat(noticeHistory).isEqualToComparingOnlyGivenFields(saved
                , "title", "content", "createdAt");
    }

    @Test
    @Rollback   //개별 Rollback 지정
    public void 수정테스트(){
        //Given
        Notice notice = new Notice();
        notice.setTitle("테ㅡ트");
        notice.setContent("내용이빈당");
        Notice saved = noticeService.saveNotice(notice);

        //When
        Notice savedForUpdate = noticeService.getNoticeById(notice.getNoticeKey());
        savedForUpdate.setTitle("테스트 변경");
        savedForUpdate.setContent("변경 된 공지 내용");
        Notice updated = noticeService.saveNotice(savedForUpdate);

        //Then
        Page<NoticeHistory> noticeHistoryPage = noticeHistoryService.findAllById(updated.getNoticeKey(), null);
        assertThat(saved.getCreatedAt()).isNotNull();
        assertThat(updated.getUpdatedAt()).isNotNull().describedAs("수정일{0}은 누락되지 않는다", saved.getUpdatedAt());
        assertThat(noticeHistoryPage.getTotalElements()).isEqualTo(2).describedAs("생성, 수정 Audit 누적");

        assertThat(noticeHistoryPage.getContent().get(0)).isEqualToComparingOnlyGivenFields(saved
                , "title", "content", "createdAt", "updatedAt");

        assertThat(noticeHistoryPage.getContent().get(1)).isEqualToComparingOnlyGivenFields(updated
                , "title", "content", "createdAt", "updatedAt");

    }

    @Test
    @Rollback   //개별 Rollback 지정
    public void 동일값_수정테스트(){
        //Given
        Notice notice = new Notice();
        notice.setTitle("테ㅡ트");
        notice.setContent("내용이빈당");
        Notice saved = noticeService.saveNotice(notice);

        //When
        Notice savedForUpdate = noticeService.getNoticeById(notice.getNoticeKey());
        savedForUpdate.setTitle("테스트 변경");
        savedForUpdate.setContent("변경 된 공지 내용");

        //JPA 자체에서 save 전 select를 통해 동일 객체인지 확인
        //만약 존재하지 않거나 다르면 Update 처리 -> @PostUpdate callback 처리
        noticeService.saveNotice(savedForUpdate);
        noticeService.saveNotice(savedForUpdate);
        noticeService.saveNotice(savedForUpdate);
        noticeService.saveNotice(savedForUpdate);

        Notice updated = noticeService.saveNotice(savedForUpdate);

        //Then
        Page<NoticeHistory> noticeHistoryPage = noticeHistoryService.findAllById(updated.getNoticeKey(), null);
        assertThat(noticeHistoryPage.getTotalElements()).isEqualTo(2).describedAs("동일 값으로 수정 될 경우엔 Audit이 쌓이지 않음");

        assertThat(noticeHistoryPage.getContent().get(0)).isEqualToComparingOnlyGivenFields(saved
                , "title", "content", "createdAt", "updatedAt");

        assertThat(noticeHistoryPage.getContent().get(1)).isEqualToComparingOnlyGivenFields(updated
                , "title", "content", "createdAt", "updatedAt");

    }
}