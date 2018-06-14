package me.strongwhisky.app.day31.document.service;

import me.strongwhisky.app.day31.document.domain.model.Document;

/**
 * Created by taesu on 2018-06-14.
 */
public interface DocumentService {

    Document saveDocument(Document document);

    Document completeCurrentTask(Long documentKey);
}
