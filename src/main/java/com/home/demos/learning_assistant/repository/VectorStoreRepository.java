package com.home.demos.learning_assistant.repository;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class VectorStoreRepository {

    private final VectorStore vectorStore;

    @Autowired
    public VectorStoreRepository(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public void save(String content) {
        var metadata = new HashMap<String, Object>();
        var document = new Document(content, metadata);
        var documents = new TokenTextSplitter().apply(List.of(document));

        vectorStore.add(documents);
    }
}
