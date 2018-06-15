package me.strongwhisky.app.day32.runner;

import me.strongwhisky.app.day32.document.domain.model.Document;
import me.strongwhisky.app.day32.document.service.DocumentService;
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

    /*
    2018-06-15 23:22:10.659  INFO 9988 --- [  restartedMain] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCreateEvent -> onBeforeCommit
    2018-06-15 23:22:10.659  INFO 9988 --- [  restartedMain] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCreateEvent -> onBeforeCommit
    2018-06-15 23:22:10.677 DEBUG 9988 --- [  restartedMain] org.hibernate.SQL                        : insert into document (description, name, document_key) values (?, ?, ?)
    2018-06-15 23:22:10.682 DEBUG 9988 --- [  restartedMain] org.hibernate.SQL                        : insert into document_task (auto_start, completed_at, description, document_key, due_at, name, sequence, started_at, task_status, document_task_key) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    2018-06-15 23:22:10.685 DEBUG 9988 --- [  restartedMain] org.hibernate.SQL                        : insert into document_task (auto_start, completed_at, description, document_key, due_at, name, sequence, started_at, task_status, document_task_key) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    2018-06-15 23:22:10.688  INFO 9988 --- [  restartedMain] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCreateEvent -> onAfterCompletion
    2018-06-15 23:22:10.690  INFO 9988 --- [  restartedMain] .s.a.AnnotationAsyncExecutionInterceptor : No task executor bean found for async processing: no bean of type TaskExecutor and no bean named 'taskExecutor' either
    2018-06-15 23:22:10.691  INFO 9988 --- [  restartedMain] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCreateEvent -> onAfterCommit
    2018-06-15 23:22:10.691  INFO 9988 --- [  restartedMain] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCreateEvent -> onAfterCompletion
    2018-06-15 23:22:10.691  INFO 9988 --- [  restartedMain] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCreateEvent -> onAfterCommit
    2018-06-15 23:22:10.693  INFO 9988 --- [cTaskExecutor-1] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCreateEvent -> onAfterCommitByAsync
    2018-06-15 23:22:10.694  INFO 9988 --- [cTaskExecutor-2] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCreateEvent -> onAfterCommitByAsync
    2018-06-15 23:22:10.707 DEBUG 9988 --- [  restartedMain] org.hibernate.SQL                        : select document0_.document_key as document1_0_1_, document0_.description as descript2_0_1_, document0_.name as name3_0_1_, documentta1_.document_key as documen10_1_3_, documentta1_.document_task_key as document1_1_3_, documentta1_.document_task_key as document1_1_0_, documentta1_.auto_start as auto_sta2_1_0_, documentta1_.completed_at as complete3_1_0_, documentta1_.description as descript4_1_0_, documentta1_.document_key as documen10_1_0_, documentta1_.due_at as due_at5_1_0_, documentta1_.name as name6_1_0_, documentta1_.sequence as sequence7_1_0_, documentta1_.started_at as started_8_1_0_, documentta1_.task_status as task_sta9_1_0_ from document document0_ left outer join document_task documentta1_ on document0_.document_key=documentta1_.document_key where document0_.document_key=?
    2018-06-15 23:22:10.729 DEBUG 9988 --- [  restartedMain] org.hibernate.SQL                        : select documentve0_.document_key as document4_2_0_, documentve0_.document_version_key as document1_2_0_, documentve0_.document_version_key as document1_2_1_, documentve0_.document_key as document4_2_1_, documentve0_.sequence as sequence2_2_1_, documentve0_.version as version3_2_1_ from document_version documentve0_ where documentve0_.document_key=? order by documentve0_.version desc, documentve0_.sequence desc
    2018-06-15 23:22:10.736 DEBUG 9988 --- [  restartedMain] org.hibernate.SQL                        : update document_task set auto_start=?, completed_at=?, description=?, document_key=?, due_at=?, name=?, sequence=?, started_at=?, task_status=? where document_task_key=?
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("======================================run==============================");
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
        documentService.anotherTest(document.getDocumentKey());
    }
}
