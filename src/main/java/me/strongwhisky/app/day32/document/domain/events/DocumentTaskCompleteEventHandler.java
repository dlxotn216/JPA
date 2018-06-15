package me.strongwhisky.app.day32.document.domain.events;

import lombok.extern.slf4j.Slf4j;
import me.strongwhisky.app.day32.document.domain.model.Document;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by taesu on 2018-06-14.
 */
@Slf4j
@Component
public class DocumentTaskCompleteEventHandler implements ApplicationListener<DocumentTaskCompleteEvent> {
    @Override
    public void onApplicationEvent(DocumentTaskCompleteEvent event) {
        if (event.getSource() == null) {
            return;
        }
        //TODO Notify to task was completed
        Document eventTarget = (Document) event.getSource();
        log.info("DocumentTaskCompleteEvent handled [documentKey is : " + eventTarget.getDocumentKey() + "]");
    }
}
