package me.strongwhisky.app.day32.document.service.impl;

import me.strongwhisky.app.day32.document.domain.model.Document;
import me.strongwhisky.app.day32.document.domain.repository.DocumentRepository;
import me.strongwhisky.app.day32.document.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Created by taesu on 2018-06-14.
 */
@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    @Override
    @Transactional
    public Document saveDocument(Document document) {
        return documentRepository.save(document);
    }

    /*
    2018-06-15 23:22:10.741 DEBUG 9988 --- [  restartedMain] org.hibernate.SQL                        : select document0_.document_key as document1_0_0_, document0_.description as descript2_0_0_, document0_.name as name3_0_0_ from document document0_ where document0_.document_key=?
    2018-06-15 23:22:10.745 DEBUG 9988 --- [  restartedMain] org.hibernate.SQL                        : select documentta0_.document_key as documen10_1_0_, documentta0_.document_task_key as document1_1_0_, documentta0_.document_task_key as document1_1_1_, documentta0_.auto_start as auto_sta2_1_1_, documentta0_.completed_at as complete3_1_1_, documentta0_.description as descript4_1_1_, documentta0_.document_key as documen10_1_1_, documentta0_.due_at as due_at5_1_1_, documentta0_.name as name6_1_1_, documentta0_.sequence as sequence7_1_1_, documentta0_.started_at as started_8_1_1_, documentta0_.task_status as task_sta9_1_1_ from document_task documentta0_ where documentta0_.document_key=?
    2018-06-15 23:22:10.753  INFO 9988 --- [  restartedMain] d.d.d.e.DocumentTaskCompleteEventHandler : DocumentTaskCompleteEvent handled [documentKey is : 1]
    2018-06-15 23:22:12.753  INFO 9988 --- [  restartedMain] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCompleteEvent -> onBeforeCommit
    2018-06-15 23:22:12.756 DEBUG 9988 --- [  restartedMain] org.hibernate.SQL                        : update document_task set auto_start=?, completed_at=?, description=?, document_key=?, due_at=?, name=?, sequence=?, started_at=?, task_status=? where document_task_key=?
    2018-06-15 23:22:12.758 DEBUG 9988 --- [  restartedMain] org.hibernate.SQL                        : update document_task set auto_start=?, completed_at=?, description=?, document_key=?, due_at=?, name=?, sequence=?, started_at=?, task_status=? where document_task_key=?
    2018-06-15 23:22:12.761  INFO 9988 --- [  restartedMain] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCompleteEvent -> onAfterCompletion
    2018-06-15 23:22:12.762  INFO 9988 --- [  restartedMain] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCompleteEvent -> onAfterCommit
    2018-06-15 23:22:12.763  INFO 9988 --- [cTaskExecutor-3] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCompleteEvent -> onAfterCommitByAsync
     */
    @Transactional
    @Override
    public Document completeCurrentTask(Long documentKey) {
        System.out.println("======================================completeCurrentTask==============================");
        Document saved = documentRepository.save(
                documentRepository.findById(documentKey).orElseThrow(IllegalArgumentException::new).completeCurrentTask());

        //onAfterRollback(onAfterCommit) -> onAfterCompletion 일 때가 있고
        //onAfterCompletion -> onAfterRollback(onAfterCommit) 일 때가 있다
        /*
            Rollback test
        */
//        if (documentKey.equals(1L))
//            throw new IllegalStateException();


        /*
            Async test
         */
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return saved;
    }

    /*
    Async의 thread 이름 주의
    flush 할 경우에 전체적으로 이벤트가 발행
    ======================================anotherTest==============================
    2018-06-15 23:22:12.765 DEBUG 9988 --- [  restartedMain] org.hibernate.SQL                        : select document0_.document_key as document1_0_0_, document0_.description as descript2_0_0_, document0_.name as name3_0_0_ from document document0_ where document0_.document_key=?
    2018-06-15 23:22:12.768 DEBUG 9988 --- [  restartedMain] org.hibernate.SQL                        : select documentta0_.document_key as documen10_1_0_, documentta0_.document_task_key as document1_1_0_, documentta0_.document_task_key as document1_1_1_, documentta0_.auto_start as auto_sta2_1_1_, documentta0_.completed_at as complete3_1_1_, documentta0_.description as descript4_1_1_, documentta0_.document_key as documen10_1_1_, documentta0_.due_at as due_at5_1_1_, documentta0_.name as name6_1_1_, documentta0_.sequence as sequence7_1_1_, documentta0_.started_at as started_8_1_1_, documentta0_.task_status as task_sta9_1_1_ from document_task documentta0_ where documentta0_.document_key=?
    2018-06-15 23:22:12.780  INFO 9988 --- [  restartedMain] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCreateEvent -> onBeforeCommit
    2018-06-15 23:22:12.780  INFO 9988 --- [  restartedMain] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCreateEvent -> onBeforeCommit
    2018-06-15 23:22:12.780  INFO 9988 --- [  restartedMain] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCreateEvent -> onBeforeCommit
    2018-06-15 23:22:12.783 DEBUG 9988 --- [  restartedMain] org.hibernate.SQL                        : insert into document_task (auto_start, completed_at, description, document_key, due_at, name, sequence, started_at, task_status, document_task_key) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    2018-06-15 23:22:12.784 DEBUG 9988 --- [  restartedMain] org.hibernate.SQL                        : insert into document_task (auto_start, completed_at, description, document_key, due_at, name, sequence, started_at, task_status, document_task_key) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    2018-06-15 23:22:12.786 DEBUG 9988 --- [  restartedMain] org.hibernate.SQL                        : insert into document_task (auto_start, completed_at, description, document_key, due_at, name, sequence, started_at, task_status, document_task_key) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    2018-06-15 23:22:12.788  INFO 9988 --- [  restartedMain] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCreateEvent -> onAfterCompletion
    2018-06-15 23:22:12.788  INFO 9988 --- [  restartedMain] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCreateEvent -> onAfterCommit
    2018-06-15 23:22:12.789  INFO 9988 --- [  restartedMain] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCreateEvent -> onAfterCompletion
    2018-06-15 23:22:12.789  INFO 9988 --- [cTaskExecutor-4] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCreateEvent -> onAfterCommitByAsync
    2018-06-15 23:22:12.789  INFO 9988 --- [  restartedMain] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCreateEvent -> onAfterCommit
    2018-06-15 23:22:12.791  INFO 9988 --- [cTaskExecutor-5] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCreateEvent -> onAfterCommitByAsync
    2018-06-15 23:22:12.791  INFO 9988 --- [  restartedMain] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCreateEvent -> onAfterCompletion
    2018-06-15 23:22:12.791  INFO 9988 --- [  restartedMain] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCreateEvent -> onAfterCommit
    2018-06-15 23:22:12.792  INFO 9988 --- [cTaskExecutor-6] .d.d.d.e.DocumentTransactionEventHandler : DocumentTaskCreateEvent -> onAfterCommitByAsync
     */
    @Transactional
    @Override
    public void anotherTest(Long documentKey){
        System.out.println("======================================anotherTest==============================");

        Document document = documentRepository.findById(documentKey).orElseThrow(IllegalArgumentException::new);

        document.createDocumentTask("three", "des", 3, false, null);
        documentRepository.save(document);

        document.createDocumentTask("four", "desawefawf", 4, false, null);
        documentRepository.save(document);

        document.createDocumentTask("five", "325325aefawf", 5, false, null);
        documentRepository.save(document);
    }
}
