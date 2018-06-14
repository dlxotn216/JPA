package me.strongwhisky.app.day31.document.service.impl;

import me.strongwhisky.app.day31.document.domain.model.Document;
import me.strongwhisky.app.day31.document.domain.repository.DocumentRepository;
import me.strongwhisky.app.day31.document.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by taesu on 2018-06-14.
 */
@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    @Override
    @Transactional
    public Document saveDocument(Document document){
        return documentRepository.save(document);
    }

    @Override
    @Transactional
    public Document completeCurrentTask(Long documentKey) {
        return documentRepository.save(
                documentRepository.findById(documentKey).orElseThrow(IllegalArgumentException::new).completeCurrentTask());
    }
}
