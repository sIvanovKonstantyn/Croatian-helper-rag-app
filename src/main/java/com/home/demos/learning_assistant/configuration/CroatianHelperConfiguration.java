package com.home.demos.learning_assistant.configuration;

import lombok.SneakyThrows;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.ai.ResourceUtils;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

@Configuration
public class CroatianHelperConfiguration {

    @Bean
    public ChatClient chatClient(ChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }

    @Bean
    public QuestionAnswerAdvisor questionAnswerAdvisor(VectorStore vectorStore) {
        return new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults());
    }

    @Bean
    @SneakyThrows
    public Tesseract tesseract() {
        var tesseract = new Tesseract();
        tesseract.setLanguage("hrv");

        var path = new File(CroatianHelperConfiguration.class.getClassLoader()
                .getResource("tessdata")
                .toURI()).getAbsolutePath();

        tesseract.setDatapath(path);

        return tesseract;
    }
}
