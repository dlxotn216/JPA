package me.strongwhisky.app._day14.service;

import me.strongwhisky.app._day14.domain.Notice;

/**
 * Created by taesu on 2018-05-08.
 */
public interface NoticeService {

    Notice saveNotice(Notice notice);

    Notice getNoticeById(Long noticeKey);
}
