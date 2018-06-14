package me.strongwhisky.app.day31.document.domain.events;

import me.strongwhisky.app.day31.document.domain.model.Document;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by taesu on 2018-06-14.
 */
@Component
public class DocumentTaskCompleteEventHandler implements ApplicationListener<DocumentTaskCompleteEvent> {
    @Override
    public void onApplicationEvent(DocumentTaskCompleteEvent event) {
        if (event.getSource() == null) {
            return;
        }
        //TODO Notify to task was completed
        Document eventTarget = (Document) event.getSource();
        System.out.println("DocumentTaskCompleteEvent handled [documentKey is : " + eventTarget.getDocumentKey() + "]");
    }
}
