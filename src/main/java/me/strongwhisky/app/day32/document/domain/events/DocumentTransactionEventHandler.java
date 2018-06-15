package me.strongwhisky.app.day32.document.domain.events;

import lombok.extern.slf4j.Slf4j;
import me.strongwhisky.app.day32.document.domain.model.Document;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * Created by taesu on 2018-06-15.
 *
 * 아래에서 발행한 이벤트가 Transaction의 phase에 따라 발행 됨
 * {@link Document#completeCurrentTask()}
 */
@Slf4j
@Component
public class DocumentTransactionEventHandler {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onAfterCommit(DocumentTaskCompleteEvent event){
        if(event.getSource() == null){
            return;
        }
        log.info("DocumentTaskCompleteEvent -> onAfterCommit");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void onAfterCompletion(DocumentTaskCompleteEvent event){
        if(event.getSource() == null){
            return;
        }
        log.info("DocumentTaskCompleteEvent -> onAfterCompletion");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void onAfterRollback(DocumentTaskCompleteEvent event){
        if(event.getSource() == null){
            return;
        }
        log.info("DocumentTaskCompleteEvent -> onAfterRollback");
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onBeforeCommit(DocumentTaskCompleteEvent event){
        if(event.getSource() == null){
            return;
        }
        log.info("DocumentTaskCompleteEvent -> onBeforeCommit");
    }

    @Async
    @TransactionalEventListener
    public void onAfterCommitByAsync(DocumentTaskCompleteEvent event){
        if(event.getSource() == null){
            return;
        }
        log.info("DocumentTaskCompleteEvent -> onAfterCommitByAsync");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onAfterCommit(DocumentTaskCreateEvent event){
        if(event.getSource() == null){
            return;
        }
        log.info("DocumentTaskCreateEvent -> onAfterCommit");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void onAfterCompletion(DocumentTaskCreateEvent event){
        if(event.getSource() == null){
            return;
        }
        log.info("DocumentTaskCreateEvent -> onAfterCompletion");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void onAfterRollback(DocumentTaskCreateEvent event){
        if(event.getSource() == null){
            return;
        }
        log.info("DocumentTaskCreateEvent -> onAfterRollback");
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onBeforeCommit(DocumentTaskCreateEvent event){
        if(event.getSource() == null){
            return;
        }
        log.info("DocumentTaskCreateEvent -> onBeforeCommit");
    }

    @Async
    @TransactionalEventListener
    public void onAfterCommitByAsync(DocumentTaskCreateEvent event){
        if(event.getSource() == null){
            return;
        }
        log.info("DocumentTaskCreateEvent -> onAfterCommitByAsync");
    }
}
