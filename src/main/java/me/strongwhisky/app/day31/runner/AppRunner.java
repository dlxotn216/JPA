package me.strongwhisky.app.day31.runner;

import me.strongwhisky.app.day31.document.domain.model.Document;
import me.strongwhisky.app.day31.document.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by taesu on 2018-06-14.
 */
@Component
public class AppRunner implements ApplicationRunner {
    @Autowired
    private DocumentService documentService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //Given
        Document document = new Document("문서1", "설명1");
        document.createDocumentTask("작성태스크1", "작성", 1, false, null);
        document.createDocumentTask("작성태스크2", "작성", 2, true, null);

        document = documentService.saveDocument(document);

        //When start
        document.startNextTask();
        document = documentService.saveDocument(document);

        //When complete
        documentService.completeCurrentTask(document.getDocumentKey());
    }
}
