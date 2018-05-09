package me.strongwhisky.app._day17.service;

import me.strongwhisky.app._day17.domain.Notice;

/**
 * Created by taesu on 2018-05-08.
 */
public interface NoticeService {
	
	void testNotice();
	
	Notice saveNotice(Notice notice);

    Notice getNoticeById(Long noticeKey);
}
