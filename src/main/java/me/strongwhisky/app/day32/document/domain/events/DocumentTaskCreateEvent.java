package me.strongwhisky.app.day32.document.domain.events;

import org.springframework.context.ApplicationEvent;

/**
 * Created by taesu on 2018-06-14.
 */
public class DocumentTaskCreateEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public DocumentTaskCreateEvent(Object source) {
        super(source);
    }
}
