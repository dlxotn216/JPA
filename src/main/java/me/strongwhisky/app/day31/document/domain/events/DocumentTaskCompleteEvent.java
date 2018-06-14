package me.strongwhisky.app.day31.document.domain.events;

import org.springframework.context.ApplicationEvent;

/**
 * Created by taesu on 2018-06-14.
 */
public class DocumentTaskCompleteEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public DocumentTaskCompleteEvent(Object source) {
        super(source);
    }
}
