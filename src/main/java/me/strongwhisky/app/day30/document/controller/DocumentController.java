package me.strongwhisky.app.day30.document.controller;

import me.strongwhisky.app.day30.document.domain.model.Document;
import me.strongwhisky.app.day30.document.domain.model.specification.DocumentSpecificationBuilder;
import me.strongwhisky.app.day30.document.domain.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by taesu on 2018-06-13.
 */
@RestController
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;

    @GetMapping(value = "/documents")
    public List<Document> getDocuments(@RequestParam(value = "criteria") String criteria) {
        Specification<Document> specification = new DocumentSpecificationBuilder(criteria).build();
        return documentRepository.findAll(specification);
    }
}
