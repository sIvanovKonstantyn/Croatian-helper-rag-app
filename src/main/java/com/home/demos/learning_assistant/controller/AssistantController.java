package com.home.demos.learning_assistant.controller;

import com.home.demos.learning_assistant.service.AssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assistant")
public class AssistantController {
    private final AssistantService assistantService;

    @Autowired
    public AssistantController(AssistantService assistantService) {
        this.assistantService = assistantService;
    }

    @GetMapping(value = "/ask", produces = {MediaType.TEXT_HTML_VALUE})
    public ResponseEntity<String> saveDocument(@RequestParam("question") String question) {
        var answer = assistantService.getAnswer(question);

        return ResponseEntity.ok(String.format("""
                        <html>
                            <head>
                                <title>Answer</title>
                            </head>
                            <body>
                                <h2>%s</h2>
                                <p></p>
                                <p>%s</p>
                            </body>
                        </html>
                        """,
                question,
                answer
        ));
    }
}
