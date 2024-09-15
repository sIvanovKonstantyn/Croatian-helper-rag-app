package com.home.demos.learning_assistant.service;

import com.home.demos.learning_assistant.parser.PDFBooksParser;
import com.home.demos.learning_assistant.repository.VectorStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SourcesFilesService {

    private final PDFBooksParser pdfBooksParser;
    private final VectorStoreRepository repository;

    @Autowired
    public SourcesFilesService(PDFBooksParser pdfBooksParser, VectorStoreRepository repository) {
        this.pdfBooksParser = pdfBooksParser;
        this.repository = repository;
    }

    public void savePdf(String filePath) {
        var pdfText = pdfBooksParser.parse(filePath);
        repository.save(pdfText);
    }
}
