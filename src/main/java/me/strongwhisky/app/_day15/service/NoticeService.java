package me.strongwhisky.app._day15.service;

import me.strongwhisky.app._day15.domain.Notice;

/**
 * Created by taesu on 2018-05-08.
 */
public interface NoticeService {
	
	void testNotice();
	
	Notice saveNotice(Notice notice);

    Notice getNoticeById(Long noticeKey);
}
