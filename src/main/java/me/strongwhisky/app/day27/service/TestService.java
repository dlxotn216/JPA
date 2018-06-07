package me.strongwhisky.app.day27.service;

/**
 * Created by taesu on 2018-05-26.
 */
public interface TestService {
    void testForNotSaveCall_WhenNotChangeGroup();

    void testForNotSaveCall_WhenChangeGroup();

    void changeUsersTest();

    void clearAndChangeUsersTest();

    void changeBulk();
}
