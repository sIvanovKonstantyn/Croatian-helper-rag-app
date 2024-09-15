package com.home.demos.learning_assistant.controller;

import com.home.demos.learning_assistant.service.SourcesFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/source-files")
public class SourcesFilesController {
    private final SourcesFilesService sourcesFilesService;

    @Autowired
    public SourcesFilesController(SourcesFilesService sourcesFilesService) {
        this.sourcesFilesService = sourcesFilesService;
    }

    @PostMapping("/pdf")
    public ResponseEntity<Void> saveDocument(@RequestParam("filePath") String filePath) {
        sourcesFilesService.savePdf(filePath);

        return ResponseEntity.status(201).build();
    }
}
