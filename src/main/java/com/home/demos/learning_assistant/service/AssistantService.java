package com.home.demos.learning_assistant.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssistantService {
    private final ChatClient chatClient;
    private final QuestionAnswerAdvisor questionAnswerAdvisor;

    @Autowired
    public AssistantService(ChatClient chatClient, QuestionAnswerAdvisor questionAnswerAdvisor) {
        this.chatClient = chatClient;
        this.questionAnswerAdvisor = questionAnswerAdvisor;
    }

    public String getAnswer(String question) {
        return chatClient.prompt()
                .user(question)
                .advisors(questionAnswerAdvisor)
                .call()
                .content();
    }
}
